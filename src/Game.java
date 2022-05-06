import board.Board;

import character.Hero;
import equipment.Equipment;
import exceptions.OutOfBoardCharacterException;

import java.util.Scanner;

public class Game {

    private Hero character;
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
        this.character = this.createCharacter();
        this.board = this.createBoard();
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
            this.askToRestart();
        }
    }

    /**
     * Play the game while user not at the end of the board
     */
    private void playGame() throws OutOfBoardCharacterException {
        int positionPlayer = 0;
        Dice dice = new Dice();
        while(positionPlayer< this.board.getBoardLength()) {
            positionPlayer += dice.throwDice();
            System.out.println(positionPlayer + "/" + this.board.getBoardLength());
        }
        if (positionPlayer >= this.board.getBoardLength()) {
            throw new OutOfBoardCharacterException("Vous avez fini la partie");
        }
        if (positionPlayer < 0) {
            throw new OutOfBoardCharacterException("Vous êtes parti un peu loin");
        }
    }

    /**
     * Ask the user if he/she wants to replay if yes restart the game else quit the game
     */
    private void askToRestart() {
        System.out.println("Voulez-vous recommencer (recommencer) ou quitter (quitter) ?");
        String userInput = userInput().toLowerCase();
        if ("recommencer".equalsIgnoreCase(userInput)) {
            this.start();
        } else if ("quitter".equalsIgnoreCase((userInput))) {
            this.quitGame();
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
