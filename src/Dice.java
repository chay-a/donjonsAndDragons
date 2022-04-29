import java.util.Random;

public class Dice {
    public static void main(String[] args) {
        Dice dice = new Dice();
        for (int i = 0; i < 10000; i++) {
            int value = dice.throwDice();
            if (value < 1 || value > 6) {
                System.out.println(value);
            }
        }
    }

    /**
     * Throw a dice
     * @return int number between 1 and 6
     */
    public int throwDice() {
        // Random number between 1 and 6
        Random random = new Random();
        int low = 1;
        int high = 7;
        return random.nextInt(high - low) + low; // number
    }
}
