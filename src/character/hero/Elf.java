package character.hero;

import character.Hero;
import equipment.Distance;
import equipment.Equipment;

public class Elf extends Hero {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Elf() {
        super(5, 6, 13, 13);
    }

    /**
     * Set the equipment to the Character
     * @param distance Distance
     */
    public void setEquipment(Distance distance) {
        super.setEquipment(distance);
    }



    /**
     * Reset the values to their default values
     */
    @Override
    public void reset() {
        this.life = 5;
        this.strength = 6;
        this.setMaxLife(13);
        this.setMaxStrength(13);
        this.setEquipment(null);
    }
}
