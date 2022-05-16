package dnd.character.hero;

import dnd.character.Hero;
import dnd.equipment.Animal;

public class Nicker extends Hero {

    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Nicker() {
        super(5, 1, 10, 13);
    }

    /**
     * Set the dnd.equipment to the Character
     * @param animal Animal
     */
    public void setEquipment(Animal animal) {
        super.setEquipment(animal);
    }


    /**
     * Reset the values to their default values
     */
    @Override
    public void reset() {
        this.life = 5;
        this.strength = 1;
        this.setMaxLife(10);
        this.setMaxStrength(13);
        this.setEquipment(null);
        this.resetInventory();
    }


}
