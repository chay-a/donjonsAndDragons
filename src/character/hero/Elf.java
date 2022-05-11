package character.hero;

import character.Hero;
import equipment.Distance;
import equipment.Equipment;

public class Elf extends Hero {
    public Elf() {
        super(5, 6, 13, 13);
    }

    @Override
    public String takeEquipment(Equipment equipment) {
        if (equipment instanceof Distance) {
            this.setEquipment(equipment);
            return "Vous avez pris l'équipement";
        } else {
            return "Vous ne pouvez pas prendre cet équipement";
        }
    }

    @Override
    public void reset() {
        this.life = 5;
        this.strength = 6;
        this.setMaxLife(13);
        this.setMaxStrength(13);
        this.setEquipment(null);
    }
}
