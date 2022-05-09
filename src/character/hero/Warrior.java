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

    public void setEquipment(Weapon weapon) {
        super.setEquipment(weapon);
    }

    /**
     * Take the equipment if it is a Weapon
     * @param equipment Equipment
     * @return String
     */
    @Override
    public String takeEquipment(Equipment equipment) {
        if (equipment instanceof Weapon) {
            this.setEquipment(equipment);
            return "Vous avez pris l'équipement";
        } else {
            return "Vous ne pouvez pas prendre cet équipement";
        }
    }
}
