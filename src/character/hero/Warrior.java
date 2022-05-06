package character.hero;

import character.Hero;
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


}
