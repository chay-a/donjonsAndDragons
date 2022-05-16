package dnd.menu;

import dnd.character.Hero;
import dnd.equipment.Equipment;
import dnd.inventory.IIventory;

import java.util.List;

public interface Menu {
    String requestPlayersNb();
    void displayCharacterStats(Hero character);
    String displayCharacterTypeSelection(String types);
    void displayWrongType();
    void displayException(String exception);
    void displayCharacterTurn(String characterName);
    String requestActionOfATurn();
    void displayCharacterPositionOnBoard(int positionCharacter, int boardLength);
    void displayEvent(String eventMessage);
    void displayLife(int life);
    String requestTakeEquipment(int strength);
    void displayDiceValue(int dice);
    String requestFightAction();
    void displayFlee();
    void displayCharacterTakeEquipment();
    void displayCharacterCantTakeEquipment();
    void displayCharacterDidntTakeEquipment();
    void displayInvalidUserInput();
    void displayCharacterFight(int attack, int opponentLife);
    void displayEnemyDead();
    void displayEnemyAction(int attack, int opponentLife);
    void displayCharacterDead();
    String requestRestart();
    String requestCharacterName();
    String validateCharacter();
    void displayRank(String playerName, int playerRank);
    void quitGame();

    void displayEnemyDoesntCare();

    void displayFullInventory();

    String requestInventoryElementChange();

    void displayPotionInventory(List<IIventory> potions);

    String requestElementToChangeInInventory();

    void displayEquipmentInventory(List<IIventory> equipments);

    String requestTakePotion(int effect);

    void displayCharacterTakePotion();

    void displayCharacterDidntTakePotion();

    void displayInventory(List<Equipment> inventory);

    String requestEquipmentAction();

    String requestInventoryAction();

    String requestGameSaving();

    void displayGameNotSaved();

    void displayDatabaseError();

    void displayCharacterDatabase(String name, String life, String strength, int i);

    String requestCharactersSaved();

    String requestDatabaseCharactersAction();

    String requestDatabaseCharacterNumber();
}
