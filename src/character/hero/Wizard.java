package character.hero;

import character.Hero;
import equipment.Spell;

public class Wizard extends Hero {
    /**
     * Constructor that hydrate values of the parent class Character
     */
    public Wizard() {
        super(3, 8, 6, 15);
    }

    public void setEquipment(Spell spell) {
        super.setEquipment(spell);
    }
}
