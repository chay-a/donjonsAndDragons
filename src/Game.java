import character.inGame.CharacterInGame;
import menu.Menu;
import menu.MenuTerminal;
import board.Board;

import character.Hero;
import database.Database;
import dice.Dice;
import dice.IDice;
import event.IEvent;
import exceptions.OutOfBoardCharacterException;

import java.util.*;

public class Game {

    private List<CharacterInGame> characters = new ArrayList<>();
    private Board board;
    private Menu menu;
    private IDice dice;
    private Database database;

    public static void main(String[] args) {
     //   System.out.println(System.getProperty("user.language")+"-"+System.getProperty("user.country"));
    }

    /**
     * Hydrate character and board with the creation of a new character and a new board
     */
    public Game() {
        this.menu = new MenuTerminal();
        this.database = new Database();
        this.dice = new Dice();
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
        String userInput = menu.requestCharactersSaved();
        switch(userInput) {
            case "oui":
                List<CharacterInGame> playersList = getCharactersFromDatabase(players, nbPlayers);
                if (playersList != null) return playersList;
            case "non":
                if (nbPlayers > 0) {
                    for (int i = 1; i < nbPlayers + 1; i++) {
                        players.add(new CharacterInGame(this.createCharacter()));
                    }
                    return players;
                }
                break;
            default:
                menu.displayInvalidUserInput();
                break;
        }
        menu.quitGame();
        return players;
    }

    private List<CharacterInGame> getCharactersFromDatabase(List<CharacterInGame> players, int nbPlayers) {
        String userInput;
        while(players.size()<=0) {
            List<Integer> listId = this.database.getCharacters(this.menu);
            userInput = menu.requestDatabaseCharactersAction();
            switch (userInput) {
                case "utilise":
                    List<CharacterInGame> playersList = createCharactersFromDatabase(players, nbPlayers, listId);
                    if (playersList != null) return playersList;
                case "supprime":
                    deleteDatabaseCharacter(listId);
                    break;
                default:
                    menu.displayWrongType();
                    break;
            }
        }
        return null;
    }

    private void deleteDatabaseCharacter(List<Integer> listId) {
        String userInput;
        userInput = menu.requestDatabaseCharacterNumber();
        int index = Integer.parseInt(userInput) -1;
        if (index <= listId.size()) {
            this.database.removeCharacter(listId.get(index), this.menu);
        }
    }

    private List<CharacterInGame> createCharactersFromDatabase(List<CharacterInGame> players, int nbPlayers, List<Integer> listId) {
        String userInput;
        if (nbPlayers > 0) {
            for (int i = 1; i < nbPlayers + 1; i++) {
                userInput = menu.requestDatabaseCharacterNumber();
                int index = Integer.parseInt(userInput) -1;
                if (Integer.parseInt(userInput) <= listId.size()) {
                    players.add(new CharacterInGame(this.database.getCharacter(listId.get(index), this.menu)));
                } else {
                    break;
                }
            }
            return players;
        }
        return null;
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
            menu.quitGame();
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
            menu.quitGame();
        }
        return userInput;
    }



    /**
     * Select character type
     */
    private Hero selectCharacterType() {
        Hero character = null;
        while (character == null) {
            String userInput = this.menu.displayCharacterTypeSelection(Hero.getAllCharacters().keySet().toString());
            if (userInput.equals("quitter")){
                menu.quitGame();
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
            // TODO: 16/05/2022 check sort characters
            Collections.sort(this.characters);
            for (CharacterInGame character : this.characters) {
                this.menu.displayRank(character.getCharacter().getName(), this.characters.indexOf(character) +1);
            }
            this.endGame();
        }
    }

    /**
     * Play the game while user not at the end of the board
     */
    private void playGame() throws OutOfBoardCharacterException {
        boolean isGamePlaying = true;
        while(isGamePlaying) {
            isGamePlaying = round(isGamePlaying);
        }
    }

    /**
     * Play one round of the game
     * @param isGamePlaying boolean
     * @return boolean
     * @throws OutOfBoardCharacterException Exception
     */
    private boolean round(boolean isGamePlaying) throws OutOfBoardCharacterException {
        for (CharacterInGame character : this.characters) {
            isGamePlaying = playerRound(isGamePlaying, character);
        }
        return isGamePlaying;
    }

    /**
     * Round of a player
     * @param isGamePlaying boolean
     * @param character CharacterInGame
     * @return boolean
     * @throws OutOfBoardCharacterException Exception
     */
    private boolean playerRound(boolean isGamePlaying, CharacterInGame character) throws OutOfBoardCharacterException {
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
                    case "inventaire" :
                        this.inventoryAction(character);
                        break;
                    case "quitter":
                        menu.quitGame();
                        break;
                    default:
                        break;
                }
            }
            isGamePlaying = diceThrew(isGamePlaying, character);
        }
        return isGamePlaying;
    }

    private boolean diceThrew(boolean isGamePlaying, CharacterInGame character) throws OutOfBoardCharacterException {
        int diceValue = this.dice.throwDice();
        this.menu.displayDiceValue(diceValue);
        character.setPosition(character.getPosition() + diceValue);
        this.menu.displayCharacterPositionOnBoard(character.getPosition() + 1, this.board.getBoardLength());
        if (character.getPosition() >= this.board.getBoardLength()) {
            isGamePlaying = false;
            throw new OutOfBoardCharacterException("Vous avez fini la partie");
        }
        playEvent(character);
        return isGamePlaying;
    }

    private void inventoryAction(CharacterInGame character) {
        this.menu.displayInventory(character.getCharacter().getInventory());
        character.getCharacter().inventoryActions(character, this.menu);
    }

    /**
     * Action according to Event
     * @param characterInGame CharacterInGame
     */
    private void playEvent(CharacterInGame characterInGame) {
        IEvent event = (IEvent) board.getBoard().get(characterInGame.getPosition()).getValue();
        this.menu.displayEvent(event.trigger());
        event.action(characterInGame, this.menu);
        checkCharactersDead();
    }

    private void checkCharactersDead() {
        int nbCharactersDead = 0;
        for (CharacterInGame character1 : this.characters) {
            if (character1.isDead()) {
                nbCharactersDead++;
            }
        }
        if (nbCharactersDead == this.characters.size()) {
            this.endGame();
        }
    }


    /**
     * Trigger the end of the game menus
     */
    private void endGame() {
        String userInput = menu.requestGameSaving();
        switch (userInput) {
            case "oui" :
                this.database.saveCharacters(this.characters, this.menu);
                break;
            case "non" :
                menu.displayGameNotSaved();
                break;
            default:
                break;
        }

        this.restart();
    }

    private void restart() {
        String userInput = this.menu.requestRestart().toLowerCase();
        if ("recommencer".equalsIgnoreCase(userInput)) {
            resetCharacters();
            this.start();
        } else if ("quitter".equalsIgnoreCase((userInput))) {
            menu.quitGame();
        } else if ("personnage".equalsIgnoreCase(userInput)) {
            Game game = new Game();
            game.start();
        }
    }

    private void resetCharacters() {
        for (CharacterInGame character : this.characters) {
            character.getCharacter().reset();
            character.setPosition(0);
            character.setDead(false);
        }
    }

    /**
     * Create the board for the game
     * @return Board
     */
    private Board createBoard() {
        return new Board();
    }

}
