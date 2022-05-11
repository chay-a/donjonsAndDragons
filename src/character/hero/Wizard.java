package character.hero;

import character.Hero;
import equipment.Equipment;
import equipment.Spell;

public class Wizard extends Hero {
    /**
     * Constructor that hydrate values of the parent class Character
     */
    public Wizard() {
        super(3, 8, 6, 15);
    }

    /**
     * Set the equipment to the Character
     * @param spell Spell
     */
    public void setEquipment(Spell spell) {
        super.setEquipment(spell);
    }


    /**
     * Reset the values to their default values
     */
    @Override
    public void reset() {
        this.life = 3;
        this.strength = 8;
        this.setMaxLife(6);
        this.setMaxStrength(15);
        this.setEquipment(null);
    }


}
