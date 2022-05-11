package character.hero;

import character.Hero;
import equipment.Equipment;
import equipment.Weapon;

public class Warrior extends Hero {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Warrior() {
        super(5, 5, 10, 10);
    }

    /**
     * Set the equipment to the Character
     * @param weapon Weapon
     */
    public void setEquipment(Weapon weapon) {
        super.setEquipment(weapon);
    }



    /**
     * Reset the values to their default values
     */
    @Override
    public void reset() {
        this.life = 5;
        this.strength = 5;
        this.setMaxLife(10);
        this.setMaxStrength(10);
        this.setEquipment(null);
    }
}
