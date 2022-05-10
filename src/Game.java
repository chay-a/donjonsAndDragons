import List.CharacterInGame;
import Menu.Menu;
import Menu.MenuTerminal;
import board.Board;

import character.Enemy;
import character.Hero;
import equipment.Equipment;
import equipment.Potion;
import event.IEvent;
import exceptions.OutOfBoardCharacterException;

import java.util.*;

public class Game {

    private List<CharacterInGame> characters = new ArrayList<>();
    private Board board;
    private Menu menu;

    public static void main(String[] args) {
     //   System.out.println(System.getProperty("user.language")+"-"+System.getProperty("user.country"));
        Game game = new Game();
        game.start();
    }

    /**
     * Hydrate character and board with the creation of a new character and a new board
     */
    public Game() {
        this.menu = new MenuTerminal();
        this.characters = this.createCharacters();
        this.board = this.createBoard();
    }

    /**
     * Create the number of characters according to the user input
     * @return Map<Hero, Integer>
     */
    public List<CharacterInGame> createCharacters() {
        List<CharacterInGame> players = new ArrayList<CharacterInGame>();
        int nbPlayers = Integer.parseInt(this.menu.requestPlayersNb());
        for (int i = 1; i < nbPlayers +1; i++) {
            players.add(new CharacterInGame(this.createCharacter()));
        }
        return players;
    }

    /**
     * Create a character from the user selection
     * @return Character
     */
    private Hero createCharacter() {
        Hero character= null;
        boolean isInfoValid = false;
        while(!isInfoValid) {
            // Choose character type
            character = this.selectCharacterType();
            // Choose character name
            character.setName(this.chooseCharacterName());
            this.menu.displayCharacterStats(character);
            // is information valid ?
            isInfoValid = isInformationValid(isInfoValid);
        }
        return character;
    }

    /**
     * Ask the user if the information provided are valid
     * @param isInfoValid boolean
     * @return boolean
     */
    private Boolean isInformationValid(boolean isInfoValid) {
        String userInput = this.menu.validateCharacter();
        if ("quitter".equalsIgnoreCase(userInput)) {
            this.quitGame();
        } else if ("oui".equalsIgnoreCase(userInput)) {
            isInfoValid = true;

        }
        return isInfoValid;
    }

    /**
     * Ask the user for the character name
     * @return String
     */
    private String chooseCharacterName() {
        String userInput = this.menu.requestCharacterName();
        if ("quitter".equalsIgnoreCase(userInput)) {
            this.quitGame();
        }
        return userInput;
    }

    /**
     * Exit the program with a message
     */
    private void quitGame() {
        this.menu.quitGame();
        System.exit(0);
    }

    /**
     * Select character type
     */
    private Hero selectCharacterType() {
        Hero character = null;
        while (character == null) {
            String userInput = this.menu.displayCharacterTypeSelection(Hero.getAllCharacters().keySet().toString());
            if (userInput.equals("quitter")){
                this.quitGame();
            }
            // Introspection/Reflection
            String classType = Hero.getAllCharacters().get(userInput);
            try {
                Class<?> characterType = Class.forName(classType);
                character = (Hero) characterType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                this.menu.displayWrongType();
            }
        }

        return character;
    }
    /**
     * start the game
     */
    public void start() {
        try {
            this.playGame();
        } catch (OutOfBoardCharacterException e) {
            this.menu.displayException(e.getMessage());
            Collections.sort(this.characters);
            for (CharacterInGame character : this.characters) {
                this.menu.displayRank(character.getCharacter().getName(), this.characters.indexOf(character) +1);
            }
            this.askToRestart();
        }
    }

    /**
     * Play the game while user not at the end of the board
     */
    private void playGame() throws OutOfBoardCharacterException {
        Dice dice = new Dice();
        boolean isGamePlaying = true;
        while(isGamePlaying) {
            isGamePlaying = round(dice, isGamePlaying);
        }
    }

    /**
     * Play one round of the game
     * @param dice Dice
     * @param isGamePlaying boolean
     * @return boolean
     * @throws OutOfBoardCharacterException Exception
     */
    private boolean round(Dice dice, boolean isGamePlaying) throws OutOfBoardCharacterException {
        for (CharacterInGame character : this.characters) {
            if (!character.isDead()) {
                boolean isThrowDice = false;
                while (!isThrowDice) {
                    this.menu.displayCharacterTurn(character.getCharacter().getName());
                    String userInput = this.menu.requestActionOfATurn();
                    switch (userInput) {
                        case "dé":
                            isThrowDice = true;
                            break;
                        case "stats":
                            this.menu.displayCharacterStats(character.getCharacter());
                            break;
                        case "quitter":
                            this.quitGame();
                            break;
                        default:
                            break;
                    }
                }
                character.setPosition(character.getPosition() + dice.throwDice());
                this.menu.displayCharacterPositionOnBoard(character.getPosition() + 1, this.board.getBoardLength());
                if (character.getPosition() >= this.board.getBoardLength()) {
                    isGamePlaying = false;
                    throw new OutOfBoardCharacterException("Vous avez fini la partie");
                }
                playEvent(character);
            }
        }
        return isGamePlaying;
    }

    /**
     * Action according to Event
     * @param characterInGame CharacterInGame
     */
    private void playEvent(CharacterInGame characterInGame) {
        Hero character = characterInGame.getCharacter();
        IEvent event = (IEvent) board.getBoard()[characterInGame.getPosition()].getValue();
        if (event == null) {
            this.menu.displayEvent("Cette case est vide...");
        } else {
            this.menu.displayEvent(event.trigger());
            if (event instanceof Enemy) {
                EnemyEvent((Enemy) event, characterInGame);
            } else if (event instanceof Equipment) {
                EquipmentEvent((Equipment) event, character);
            } else if (event instanceof Potion) {
                PotionEvent((Potion) event, character);
            }
        }
    }

    /**
     * Potion Event
     * @param event Potion
     * @param character Hero
     */
    private void PotionEvent(Potion event, Hero character) {
        character.addLife(event.getLife());
        this.menu.displayLife(character.getLife());
    }

    /**
     * Equipment Event
     * @param event Equipment
     * @param character Hero
     */
    private void EquipmentEvent(Equipment event, Hero character) {
        String userInput;
        boolean isEquipmentEventResolve = false;
        while (!isEquipmentEventResolve) {
            userInput = this.menu.requestTakeEquipment().toLowerCase();
            switch (userInput) {
                case "oui":
                    this.menu.displayCharacterTakeEquipment(character.takeEquipment(event));
                    isEquipmentEventResolve = true;
                    break;
                case "non":
                    this.menu.displayCharacterDidntTakeEquipment();
                    isEquipmentEventResolve = true;
                    break;
                case "quitter":
                    this.quitGame();
                    break;
                default:
                    this.menu.displayInvalidUserInput();
            }
        }
    }

    /**
     * Enemy Event
     * @param event Enemy
     * @param characterInGame CharacterInGame
     */
    private void EnemyEvent(Enemy event, CharacterInGame characterInGame) {
        Hero character = characterInGame.getCharacter();
        boolean isFight = true;
        while (isFight) {
            this.menu.displayCharacterFight(character.fight(event));
            if (event.getLife() <= 0) {
                this.menu.displayEnemyDead();
                isFight = false;
            } else {
                this.menu.displayEnemyAction(event.action(character));
                if (character.getLife() <= 0) {
                    isFight = false;
                    this.menu.displayCharacterDead();
                    characterInGame.setDead(true);
                    int nbCharactersDead = 0;
                    for (CharacterInGame character1 : this.characters) {
                        if (character1.isDead()) {
                            nbCharactersDead++;
                        }
                    }
                    if (nbCharactersDead == this.characters.size()) {
                        this.askToRestart();
                    }
                }
            }
        }
    }

    /**
     * Ask the user if he/she wants to replay if yes restart the game else quit the game
     */
    private void askToRestart() {
        String userInput = this.menu.requestRestart().toLowerCase();
        if ("recommencer".equalsIgnoreCase(userInput)) {
            this.start();
        } else if ("quitter".equalsIgnoreCase((userInput))) {
            this.quitGame();
        } else if ("personnage".equalsIgnoreCase(userInput)) {
            Game game = new Game();
            game.start();
        }
    }

    /**
     * Create the board for the game
     * @return Board
     */
    private Board createBoard() {
        return new Board();
    }

    /**
     * Get the input of the player
     */
    private String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
