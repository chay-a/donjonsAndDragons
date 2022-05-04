import character.Character;
import character.Warrior;
import character.Wizard;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {

        Game game = new Game();
        game.start();

        // Create board

        // Loop until end board
            // Throw dice
            // Check board
        // End loop
    }

    /**
     * Create a character from the user selection
     */
    private void createCharacter() {
        Scanner scanner = new Scanner(System.in);
        Character character;
        Boolean isInfoValid = false;
        while(!isInfoValid) {
            // Choose character type
            character = this.selectCharacterType(scanner);
            // Choose character name
            character.setName(this.chooseCharacterName(scanner));
            this.displayCharacterInfo(character);
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
    private Boolean isInformationValid(Scanner scanner, Boolean isInfoValid) {
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
     * Display all character information
     * @param character Character
     */
    private void displayCharacterInfo(Character character) {
        System.out.println("nom du personnage : " + character.getName());
        System.out.println("vie du personnage : " + character.getLife() + ", max : " + character.getMaxLife());
        System.out.println("force du personnage : " + character.getStrength() + ", max : " + character.getMaxStrength());
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
    }
}
