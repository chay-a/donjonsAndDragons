package dnd.dice;

public class DiceScripted implements IDice{
    private int turn;
    @Override
    public int throwDice() {
        int[] diceValueList = {2, 2, 4, 6, 1, 3, 2, 5, 1, 4, 6, 6, 1, 2, 3, 3, 1, 5, 4, 2, 2};
        int diceValue = diceValueList[turn];
        this.turn++;
        return diceValue;
    }

    public DiceScripted() {
        this.turn = 0;
    }
}
