package equipment.weapon;

import character.Hero;
import equipment.Weapon;

public class Sword extends Weapon {

    public Sword() {
        super(5, "Epée");
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
