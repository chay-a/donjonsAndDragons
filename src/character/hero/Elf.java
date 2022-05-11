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
     * Take the equipment if it is a Distance
     * @param equipment Equipment
     * @return String
     */
    @Override
    public String takeEquipment(Equipment equipment) {
        if (equipment instanceof Distance) {
            this.setEquipment(equipment);
            return "Vous avez pris l'équipement";
        } else {
            return "Vous ne pouvez pas prendre cet équipement";
        }
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
