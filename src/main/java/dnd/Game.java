package dnd;

import dnd.character.inGame.CharacterInGame;
import dnd.exceptions.CharacterFleeException;
import dnd.menu.Menu;
import dnd.menu.MenuTerminal;
import dnd.board.Board;

import dnd.character.Hero;
import dnd.database.Database;
import dnd.dice.Dice;
import dnd.dice.IDice;
import dnd.event.IEvent;
import dnd.exceptions.OutOfBoardCharacterException;
import dnd.sort.SortByPosition;

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
     * Hydrate dnd.character and dnd.board with the creation of a new dnd.character and a new dnd.board
     */
    public Game() {
        this.menu = new MenuTerminal();
        this.database = new Database();
        this.dice = new Dice();
        this.characters = this.createCharacters();
        this.board = new Board();
    }

    /**
     * Return a list of characters created from the database or from scratch. The number of players is chosen by the user
     * @return List<CharacterInGame>
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

    /**
     * Return list of characters from database according to user choice, characters can be restored or deleted
     * @param players List<CharacterInGame>
     * @param nbPlayers int
     * @return List<CharacterInGame>
     */
    private List<CharacterInGame> getCharactersFromDatabase(List<CharacterInGame> players, int nbPlayers) {
        String userInput;
        while(players.size()<=0) {
            List<Long> listId = this.database.getCharacters(this.menu);
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

    /**
     * Delete a character in the database
     * @param listId List<Integer>
     */
    private void deleteDatabaseCharacter(List<Long> listId) {
        String userInput;
        userInput = menu.requestDatabaseCharacterNumber();
        int index = Integer.parseInt(userInput) -1;
        if (index <= listId.size()) {
            this.database.removeCharacter(listId.get(index), this.menu);
        }
    }

    /**
     * Return a list of characters saved from the database according to the number of players
     * @param players List<CharacterInGame>
     * @param nbPlayers int
     * @param listId list<Long>
     * @return List<CharacterInGame>
     */
    private List<CharacterInGame> createCharactersFromDatabase(List<CharacterInGame> players, int nbPlayers, List<Long> listId) {
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
     * Return a character created from scratch
     * @return Hero
     */
    private Hero createCharacter() {
        Hero character= null;
        boolean isInfoValid = false;
        while(!isInfoValid) {
            // Choose dnd.character type
            character = this.selectCharacterType();
            // Choose dnd.character name
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
    private boolean isInformationValid(boolean isInfoValid) {
        String userInput = this.menu.validateCharacter();
        if ("quitter".equalsIgnoreCase(userInput)) {
            menu.quitGame();
        } else if ("oui".equalsIgnoreCase(userInput)) {
            isInfoValid = true;

        }
        return isInfoValid;
    }

    /**
     * Return the name of the character provided by the user
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
     * Return an instance of class that extends the class Hero chosen by the user
     * @return Hero
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
     * Start the game and rank player at the end
     */
    public void start() {
        try {
            this.playGame();
        } catch (OutOfBoardCharacterException e) {
            this.menu.displayException(e.getMessage());
            Collections.sort(this.characters, Collections.reverseOrder(new SortByPosition()));
            for (CharacterInGame character : this.characters) {
                this.menu.displayRank(character.getCharacter().getName(), this.characters.indexOf(character) +1);
            }
            this.endGame();
        }
    }

    /**
     * Play the game while user not at the end of the dnd.board
     */
    private void playGame() throws OutOfBoardCharacterException {
        boolean isGamePlaying = true;
        while(isGamePlaying) {
            isGamePlaying = round(isGamePlaying);
        }
    }

    /**
     * Play one round of the game. A round correspond to all characters playing once
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

    /**
     * Return a boolean that change or not the state of the game after event played on the cell once dice threw
     * @param isGamePlaying boolean
     * @param character CharacterInGame
     * @return boolean
     * @throws OutOfBoardCharacterException OutOfBoardException
     */
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

    /**
     * Play the actions link to the character inventory
     * @param character CharacterInGame
     */
    private void inventoryAction(CharacterInGame character) {
        this.menu.displayInventory(character.getCharacter().getInventory());
        character.getCharacter().inventoryActions(character, this.menu);
    }

    /**
     * Play the action linked to the board's cell. Possibility to catch a CharacterFleeException that make the character go back
     * @param characterInGame CharacterInGame
     */
    private void playEvent(CharacterInGame characterInGame) {
        IEvent event = (IEvent) board.getBoard().get(characterInGame.getPosition()).getValue();
        try {
            event.action(characterInGame, this.menu);
        } catch (CharacterFleeException e) {
            this.menu.displayFlee();
            characterInGame.setPosition(characterInGame.getPosition() - 2);
        }
        checkCharactersDead();
    }

    /**
     * Check the number of characters that are dead. If all characters are dead then call endGame method.
     */
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
     * Ask if the user want to save their characters, and call restart method
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

    /**
     * Ask if the user want to restart the game from scratch, with already played with characters or quit game
     */
    private void restart() {
        String userInput = this.menu.requestRestart().toLowerCase();
        if ("recommencer".equalsIgnoreCase(userInput)) {
            resetCharacters();
            this.board = new Board();
            this.start();
        } else if ("quitter".equalsIgnoreCase((userInput))) {
            menu.quitGame();
        } else if ("personnage".equalsIgnoreCase(userInput)) {
            Game game = new Game();
            game.start();
        }
    }

    /**
     * Reset the characters value before restarting
     */
    private void resetCharacters() {
        for (CharacterInGame character : this.characters) {
            character.getCharacter().reset();
            character.setPosition(0);
            character.setDead(false);
        }
    }


}
