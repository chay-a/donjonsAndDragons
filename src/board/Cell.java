package board;

import character.enemy.Dragon;
import character.enemy.Goblin;
import character.enemy.Witch;
import equipment.potion.LargePotion;
import equipment.potion.SmallPotion;
import equipment.spell.Fireball;
import equipment.spell.Lightning;
import equipment.weapon.Club;
import equipment.weapon.Sword;
import event.IEvent;

public class Cell {
    private IEvent value;

    /**
     * Constructor that affect to value an IEvent
     */
    public Cell () {
        double randomBefore =  (Math.random()) * 100;
        int random = (int) randomBefore;
       if (random < 30) {
           if (random <10) {
               this.value = new Goblin();
           } else if (random <20) {
               this.value = new Dragon();
           } else {
               this.value = new Witch();
           }
       } else if (random < 60) {
           if (random < 50) {
               this.value = new LargePotion();
           } else {
               this.value = new SmallPotion();
           }
       } else if (random < 80) {
           if (random < 65) {
               this.value = new Club();
           } else if (random < 70) {
               this.value = new Sword();
           } else if (random < 75) {
               this.value= new Lightning();
           } else {
               this.value = new Fireball();
           }
       } else {
           this.value = null;
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
