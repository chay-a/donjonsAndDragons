package board;

import event.IEvent;
import event.VoidCell;

public class Cell {
    private IEvent value;

    /**
     * Constructor that affect to value a random IEvent
     */
    public Cell () {
        double randomType =  (Math.random()) * 100;
       if (randomType < 40) {
           this.value = BoardGenerator.createEnemy();
       } else if (randomType < 90) {
           this.value = BoardGenerator.createEquipment();
       } else {
           this.value = new VoidCell();
       }
    }

    /**
     * Get the value of the case
     * @return Object
     */
    public Object getValue() {
        return value;
    }


    /**
     * Set the value of the case
     * @param value IEvent
     */
    public void setValue(IEvent value) {
        this.value = value;
    }



    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Cell cell = new Cell();
            System.out.println(cell.getValue());
        }
    }

    @Override
    public String toString() {
        return this.value.getClass().getSimpleName();
    }
}
