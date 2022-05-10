import List.CharacterInGame;
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

    public static void main(String[] args) {
     //   System.out.println(System.getProperty("user.language")+"-"+System.getProperty("user.country"));
        Game game = new Game();
        game.start();
    }

    /**
     * Hydrate character and board with the creation of a new character and a new board
     */
    public Game() {
        this.characters = this.createCharacters();
        this.board = this.createBoard();
    }

    /**
     * Create the number of characters according to the user input
     * @return Map<Hero, Integer>
     */
    public List<CharacterInGame> createCharacters() {
        List<CharacterInGame> players = new ArrayList<CharacterInGame>();
        System.out.println("Nombre de joueurs : ");
        int nbPlayers = Integer.parseInt(userInput());
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
            System.out.println(character);
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
        System.out.println("Voulez-vous valider ce personnage ? (Oui/Non) ou quitter le jeu (quitter)");
        String userInput = userInput();
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
        System.out.println("Entrez le nom de votre personnage ou quitter le jeu (quitter)");
        String userInput = userInput();
        if ("quitter".equalsIgnoreCase(userInput)) {
            this.quitGame();
        }
        return userInput;
    }

    /**
     * Exit the program with a message
     */
    private void quitGame() {
        System.out.println("Vous avez quitté le jeu");
        System.exit(0);
    }

    /**
     * Select character type
     */
    private Hero selectCharacterType() {
        Hero character = null;
        while (character == null) {
            System.out.println("Entrez votre classe ("+ Hero.getAllCharacters().keySet().toString() +") ou quitter le jeu (quitter)");
            String userInput = userInput();
            if (userInput.equals("quitter")){
                this.quitGame();
            }
            // Introspection/Reflection
            String classType = Hero.getAllCharacters().get(userInput);
            try {
                Class<?> characterType = Class.forName(classType);
                character = (Hero) characterType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                System.out.println("Personnage sélectionné non valide");
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
            System.out.println(e.getMessage());
            Collections.sort(this.characters);
            for (CharacterInGame character : this.characters) {
                System.out.println(character.getCharacter().getName() + " est en position : " + this.characters.indexOf(character) +1);
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
                    System.out.println("\n" + character.getCharacter().getName() + " est en train de jouer ----------------------------------------");
                    System.out.println("Lancer le dé (dé), voir les stats (stats), quitter (quitter)");
                    String userInput = this.userInput();
                    switch (userInput) {
                        case "dé":
                            isThrowDice = true;
                            break;
                        case "stats":
                            System.out.println(character);
                            break;
                        case "quitter":
                            this.quitGame();
                            break;
                        default:
                            break;
                    }
                }
                character.setPosition(character.getPosition() + dice.throwDice());
                System.out.println((character.getPosition() + 1) + "/" + this.board.getBoardLength());
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
            System.out.println("Cette case est vide...");
        } else {
            System.out.println(event.trigger());
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
        System.out.println("Vous avez maintenant " + character.getLife() + " points de vie");
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
            System.out.println("Voulez-vous prendre cet équipement ? (Oui/non) ou quitter (quitter)");
            userInput = this.userInput().toLowerCase();
            switch (userInput) {
                case "oui":
                    System.out.println(character.takeEquipment(event));
                    isEquipmentEventResolve = true;
                    break;
                case "non":
                    System.out.println("Vous n'avez pas pris l'équipement");
                    isEquipmentEventResolve = true;
                    break;
                case "quitter":
                    this.quitGame();
                    break;
                default:
                    System.out.println("Votre demande n'a pas été comprise");
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
            System.out.println(character.fight(event));
            if (event.getLife() <= 0) {
                System.out.println("Vous avez tué l'ennemi");
                isFight = false;
            } else {
                System.out.println(event.action(character));
                if (character.getLife() <= 0) {
                    isFight = false;
                    System.out.println("Vous êtes mort");
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
        System.out.println("Voulez-vous recommencer avec ce personnage (recommencer) ou recréer un personnage (personnage) ou quitter (quitter) ?");
        String userInput = userInput().toLowerCase();
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
