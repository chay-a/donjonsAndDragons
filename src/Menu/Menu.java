package Menu;

import character.Hero;

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
    void displayCharacterTakeEquipment(String equipmentMessage);
    void displayCharacterDidntTakeEquipment();
    void displayInvalidUserInput();
    void displayCharacterFight(String characterFight);
    void displayEnemyDead();
    void displayEnemyAction(String eventMessage);
    void displayCharacterDead();
    String requestRestart();
    String requestCharacterName();
    String validateCharacter();
    void displayRank(String playerName, int playerRank);
    void quitGame();
}
