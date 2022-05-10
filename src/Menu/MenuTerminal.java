package Menu;

import character.Hero;

import java.util.Scanner;

public class MenuTerminal implements Menu{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String requestPlayersNb() {
        return this.getMessage("Nombre de joueur : ");
    }

    @Override
    public void displayCharacterStats(Hero character) {
        this.displayMessage(character.toString());
    }

    @Override
    public String displayCharacterTypeSelection(String types) {
        return this.getMessage("Entrez votre classe (" + types + ") ou quitter le jeu (quitter)");
    }

    @Override
    public void displayWrongType() {
        this.displayMessage("Personnage sélectionné non valide");
    }

    @Override
    public void displayException(String exception) {
        this.displayMessage(exception);
    }

    @Override
    public void displayCharacterTurn(String characterName) {
        this.displayMessage("\n" + characterName + " est en train de jouer ______________________________");
    }

    @Override
    public String requestActionOfATurn() {
        return this.getMessage("Lancer le dé (dé), voir les stats (stats), quitter (quitter)");
    }

    @Override
    public void displayCharacterPositionOnBoard(int positionCharacter, int boardLength) {
        this.displayMessage(positionCharacter + "/" + boardLength);
    }

    @Override
    public void displayEvent(String eventMessage) {
        this.displayMessage(eventMessage);
    }

    @Override
    public void displayLife(int life) {
        this.displayMessage("Vous avez maintenant " + life + " points de vie");
    }

    @Override
    public String requestTakeEquipment() {
        return this.getMessage("Voulez-vous prendre cet équipement ? (Oui/non) ou quitter (quitter)");
    }

    @Override
    public void displayCharacterTakeEquipment(String equipmentMessage) {
        this.displayMessage(equipmentMessage);
    }

    @Override
    public void displayCharacterDidntTakeEquipment() {
        this.displayMessage("Vous n'avez pas pris l'équipement");
    }

    @Override
    public void displayInvalidUserInput() {
        this.displayMessage("Votre demande n'a pas été comprise");
    }

    @Override
    public void displayCharacterFight(String characterFight) {
        this.displayMessage(characterFight);
    }

    @Override
    public void displayEnemyDead() {
        this.displayMessage("Vous avez tué l'ennemi");
    }

    @Override
    public void displayEnemyAction(String eventMessage) {
        this.displayMessage(eventMessage);
    }

    @Override
    public void displayCharacterDead() {
        this.displayMessage("Vous êtes mort");
    }

    @Override
    public String requestRestart() {
        return this.getMessage("Voulez-vous recommencer avec ce personnage (recommencer) ou recréer un personnage (personnage) ou quitter (quitter) ?");
    }

    @Override
    public String requestCharacterName() {
        return this.getMessage("Entrez le nom de votre personnage ou quitter le jeu (quitter)");
    }

    @Override
    public String validateCharacter() {
        return this.getMessage("Voulez-vous valider ce personnage ? (Oui/Non) ou quitter le jeu (quitter)");
    }

    @Override
    public void displayRank(String playerName, int playerRank) {
        this.displayMessage(playerName+" est en position : "+playerRank);
    }

    @Override
    public void quitGame() {
        this.displayMessage("Vous avez quitté le jeu");
    }

    public String getMessage(String message) {
        this.displayMessage(message);
        return this.scanner.nextLine();
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
