package board;

import java.util.Arrays;
import java.util.Random;

public class Cell {
    private String value;
    private final String[] values = {"empty", "enemy", "chest"};

    /**
     * Constructor that affect a value from the array values to the property value
     */
    public Cell () {
        // Get a random value from the array of values
        Random random = new Random();
        int index = random.nextInt((this.values.length));
        this.value = this.values[index];
    }

    /**
     * Get the value of the case
     * @return String
     */
    public String getValue() {
        return value;
    }


    /**
     * Set the value of the case
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Cell cell = new Cell();
        }
    }

    @Override
    public String toString() {
        return this.value;
    }
}
