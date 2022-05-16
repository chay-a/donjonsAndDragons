package dnd.menu;

import dnd.character.Hero;
import dnd.equipment.Equipment;
import dnd.inventory.IIventory;

import java.util.List;
import java.util.Scanner;

public class MenuTerminal implements Menu{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String requestPlayersNb() {
        return this.getMessage("Nombre de joueur : ");
    }

    @Override
    public void displayCharacterStats(Hero character) {
        String equipmentString;
        if (character.getEquipment() == null) {
            equipmentString = "aucun";
        } else {
            equipmentString = character.getEquipment().getClass().getSimpleName();
        }
        this.displayMessage("Votre personnage est : " + Hero.getInternalNameFromClassName(character.getClass().getName()) + "\n" +
                "Le nom de votre personnage : " + character.getName() + "\n" +
                "La vie de votre personnage : " + character.getLife() + ", max : " + character.getMaxLife() + "\n" +
                "La force de votre personnage : " + character.getStrength() + ", max : " + character.getMaxStrength() + "\n" +
                "Votre équipement : " + equipmentString);
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
        return this.getMessage("Lancer le dé (dé), voir les stats (stats), voir l'inventaire (inventaire), quitter (quitter)");
    }

    @Override
    public void displayCharacterPositionOnBoard(int positionCharacter, int boardLength) {
        this.displayMessage(positionCharacter + "/" + boardLength);
    }

    @Override
    public void displayLife(int life) {
        this.displayMessage("Vous avez maintenant " + life + " points de vie");
    }

    @Override
    public String requestTakeEquipment(int strength) {
        return this.getMessage("Voulez-vous prendre cet équipement pour un effet de " + strength +" ? (Oui/Non/Inventaire) ou quitter (quitter)");
    }

    @Override
    public void displayDiceValue(int dice) {
        this.displayMessage("Vous avancez de " + dice + " case(s)");
    }

    @Override
    public String requestFightAction() {
        return this.getMessage("Que voulez-vous faire ? Attaquer (attaque), Fuir (fuir)");
    }

    @Override
    public void displayFlee() {
        this.displayMessage("Vous fuiyez en revenant sur vos pas");
    }

    @Override
    public void displayCharacterTakeEquipment() {
        this.displayMessage("Vous avez pris l'équipement");
    }

    @Override
    public void displayCharacterCantTakeEquipment() {
        this.displayMessage("Vous ne pouvez pas prendre cet équipement");
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
    public void displayCharacterFight(int attack, int opponentLife) {
        this.displayMessage("Vous avez enlevé " + attack + " points de vie à l'ennemi \nL'ennemi a " + opponentLife + " points de vie");
    }

    @Override
    public void displayEnemyDead() {
        this.displayMessage("Vous avez tué l'ennemi");
    }

    @Override
    public void displayEnemyAction(int attack, int opponentLife) {
        this.displayMessage("L'ennemi vous a enlevé " + attack + " points de vie \nIl vous en reste " + opponentLife);
    }

    @Override
    public void displayCharacterDead() {
        this.displayMessage("Vous êtes mort");
    }

    @Override
    public String requestRestart() {
        return this.getMessage("Voulez-vous recommencer avec ce(s) personnage(s) (recommencer) ou recréer un/des personnage(s) (personnage) ou quitter (quitter) ?");
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
        System.exit(0);

    }

    @Override
    public void displayEnemyDoesntCare() {
        this.displayMessage("L'ennemi ne semble pas s'intéresser à vous...");
    }

    @Override
    public void displayFullInventory() {
        this.displayMessage("Votre inventaire est plein...");
    }

    @Override
    public String requestInventoryElementChange() {
        return this.getMessage("Voulez-vous l'échanger avec autre chose ? (oui/non)");
    }

    @Override
    public void displayPotionInventory(List<IIventory> potions) {
        StringBuilder message = new StringBuilder("Inventaire potions : \n");
        for (IIventory potion : potions) {
            message.append(potion.toString()).append("\n");
        }
        this.displayMessage(message.toString());
    }

    @Override
    public String requestElementToChangeInInventory() {
        return getMessage("Avec quel élément voulez-vous changer ?");
    }

    @Override
    public void displayEquipmentInventory(List<IIventory> equipments) {
        StringBuilder message = new StringBuilder("Inventaire équipements : \n");
        for (IIventory equipment : equipments) {
            message.append(equipment.toString()).append("\n");
        }
        this.displayMessage(message.toString());
    }

    @Override
    public String requestTakePotion(int effect) {
        return this.getMessage("Voulez-vous prendre cette potion pour un effet de " + effect +" ? (Oui/Non/Inventaire) ou quitter (quitter)");    }

    @Override
    public void displayCharacterTakePotion() {
        this.displayMessage("Vous avez pris la potion");
    }

    @Override
    public void displayCharacterDidntTakePotion() {
        this.displayMessage("Vous n'avez pas pris la potion");
    }

    @Override
    public void displayInventory(List<Equipment> inventory) {
        StringBuilder message = new StringBuilder("Votre inventaire : \n");
        for (Equipment equipment : inventory) {
            message.append(equipment.toString()).append("\n");
        }
        this.displayMessage(message.toString());
    }

    @Override
    public String requestEquipmentAction() {
        return getMessage("Que voulez-vous faire ? L'utiliser (utilise), le supprimer (supprime), revenir en arrière (retour)");
    }

    @Override
    public String requestInventoryAction() {
        return getMessage("Selectionnez un élément de votre inventaire pour avoir plus d'action ou revenir en arrière (retour)");
    }

    @Override
    public String requestGameSaving() {
        return getMessage("Voulez-vous sauvegarder votre partie ? (Oui/Non)");
    }

    @Override
    public void displayGameNotSaved() {
        displayMessage("Votre partie n'a pas été sauvegardé");
    }

    @Override
    public void displayDatabaseError() {
        displayMessage("Un erreur est survenue");
    }

    @Override
    public void displayCharacterDatabase(String name, String life, String strength, int i) {
        displayMessage("------------------------\nPersonnage ("+i+") : " + name + "\n" + "vie : " + life + "\n" + "force : " + strength);
    }

    @Override
    public String requestCharactersSaved() {
        return getMessage("Voulez-vous utiliser des personnages sauvegardés");
    }

    @Override
    public String requestDatabaseCharactersAction() {
        return getMessage("Voulez-vous utiliser (utilise) ou supprimer(supprime)");
    }

    @Override
    public String requestDatabaseCharacterNumber() {
        return getMessage("Choississez le nombre correspondant au personnage");
    }

    @Override
    public void displayEnemyAlreadyDead() {
        this.displayMessage("Cet ennemi est déjà mort...");
    }

    @Override
    public void displayEnemyEvent(String name) {
        getMessage("vous êtes tombé sur : " + name);
    }

    @Override
    public void displayEquipmentEvent(String name) {
        getMessage("Vous avez trouvé : " + name);
    }

    @Override
    public void displayVoidCellEvent() {
        getMessage("Cette case est vide...");
    }

    public String getMessage(String message) {
        this.displayMessage(message);
        return this.scanner.nextLine();
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
