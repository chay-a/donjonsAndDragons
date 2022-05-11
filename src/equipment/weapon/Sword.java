package equipment.weapon;

import character.Hero;
import equipment.Weapon;

public class Sword extends Weapon {

    public Sword() {
        super(5, "Epée");
    }

    /**
     * Play the action of the event
     * @param hero Hero
     * @return String
     */
    @Override
    public String action(Hero hero) {
        return null;
    }
}
