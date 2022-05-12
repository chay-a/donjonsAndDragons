package board;

import character.enemy.Dragon;
import character.enemy.Goblin;
import character.enemy.Witch;
import equipment.animal.Cat;
import equipment.animal.Falcon;
import equipment.distance.Arbalest;
import equipment.distance.Bow;
import equipment.potion.LargePotion;
import equipment.potion.SmallPotion;
import equipment.spell.Fireball;
import equipment.spell.Lightning;
import equipment.weapon.Club;
import equipment.weapon.Sword;
import event.IEvent;
import event.VoidCell;

public class Cell {
    private IEvent value;

    /**
     * Constructor that affect to value a random IEvent
     */
    public Cell () {
        double randomBeforeType =  (Math.random()) * 100;
        int randomType = (int) randomBeforeType;
        double randomBefore =  (Math.random()) * 100;
        int random = (int) randomBefore;
       if (randomType < 45) {
           if (random <34) {
               this.value = new Goblin();
           } else if (random <67) {
               this.value = new Dragon();
           } else {
               this.value = new Witch();
           }
       } else if (randomType < 90) {
           if (random < 10) {
               this.value = new LargePotion();
           } else if (random < 20){
               this.value = new SmallPotion();
           } else if (random < 30) {
               this.value = new Club();
           } else if (random < 40) {
               this.value = new Sword();
           } else if (random < 50) {
               this.value= new Lightning();
           } else if (random < 60){
               this.value = new Fireball();
           } else if (random < 70) {
               this.value = new Bow();
           } else if (random < 80){
               this.value = new Arbalest();
           } else if (random < 90) {
               this.value = new Cat();
           } else {
               this.value = new Falcon();
           }
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
