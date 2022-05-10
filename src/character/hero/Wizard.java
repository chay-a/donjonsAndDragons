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

    public void setEquipment(Spell spell) {
        super.setEquipment(spell);
    }

    @Override
    public String takeEquipment(Equipment equipment) {
        if (equipment instanceof Spell) {
            this.setEquipment(equipment);
            return "Vous avez pris l'équipement";
        } else {
            return "Vous ne pouvez pas prendre cet équipement";
        }
    }

    @Override
    public void reset() {
        this.life = 3;
        this.strength = 8;
        this.setMaxLife(6);
        this.setMaxStrength(15);
        this.setEquipment(null);
    }


}
