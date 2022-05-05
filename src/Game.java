import board.Board;
import board.Cell;
import character.Character;
import character.Warrior;
import character.Wizard;
import exceptions.OutOfBoardCharacterException;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    /**
     * Create a character from the user selection
     */
    private void createCharacter() {
        Scanner scanner = new Scanner(System.in);
        Character character;
        boolean isInfoValid = false;
        while(!isInfoValid) {
            // Choose character type
            character = this.selectCharacterType(scanner);
            // Choose character name
            character.setName(this.chooseCharacterName(scanner));
            System.out.println(character);
            // is information valid ?
            isInfoValid = isInformationValid(scanner, isInfoValid);
        }
    }

    /**
     * Ask the user if the information are valid
     * @param scanner Scanner
     * @param isInfoValid boolean
     * @return boolean
     */
    private Boolean isInformationValid(Scanner scanner, boolean isInfoValid) {
        System.out.println("Voulez-vous valider ce personnage ? (Oui/Non) ou quitter le jeu (quitter)");
        String userInput = scanner.nextLine();
        if ("quitter".equalsIgnoreCase(userInput)) {
            this.quitGame();
        } else if ("oui".equalsIgnoreCase(userInput)) {
            isInfoValid = true;

        }
        return isInfoValid;
    }

    /**
     * Ask the user for the character name
     * @param scanner Scanner
     * @return String
     */
    private String chooseCharacterName(Scanner scanner) {
        System.out.println("Entrez le nom de votre personnage ou quitter le jeu (quitter)");
        String userInput = scanner.nextLine();
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
     * @param scanner Scanner
     */
    private Character selectCharacterType(Scanner scanner) {
        Character character = null;
        while (character == null) {
            System.out.println("Entrez votre classe (Guerrier ou Magicien) ou quitter le jeu (quitter)");
            String userInput = scanner.nextLine().toLowerCase();
            switch (userInput) {
                case "guerrier":
                    character = new Warrior();
                    break;
                case "magicien":
                    character = new Wizard();
                    break;
                case "quitter":
                    this.quitGame();
                    break;
                default:
                    System.out.println("Personnage sélectionné non valide");
                    break;
            }
        }

        return character;
    }
    /**
     * start the game
     */
    public void start() {
        this.createCharacter();
        Board board = this.createBoard();
        try {
            this.playGame(board);
        } catch (OutOfBoardCharacterException e) {
            System.out.println(e.getMessage());
            this.askToRestart();
        }
    }

    /**
     * Play the game while user not at the end of the board
     * @param board Board
     */
    private void playGame(Board board) throws OutOfBoardCharacterException {
        int positionPlayer = 0;
        while(positionPlayer< board.getBoardLength()) {
            Dice dice = new Dice();
            positionPlayer += dice.throwDice();
            System.out.println(positionPlayer + "/" + board.getBoardLength());
        }
        if (positionPlayer >= board.getBoardLength()) {
            throw new OutOfBoardCharacterException("Vous avez finit la partie");
        }
        if (positionPlayer < 0) {
            throw new OutOfBoardCharacterException("Vous êtes parti un peu loin");
        }
    }

    /**
     * Ask the user if he/she wants to replay if yes restart the game else quit the game
     */
    private void askToRestart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous recommencer (recommencer) ou quitter (quitter) ?");
        String userInput = scanner.nextLine().toLowerCase();
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
}
