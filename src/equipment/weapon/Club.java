package equipment.weapon;

import character.Hero;
import equipment.Weapon;

public class Club extends Weapon {

    public Club() {
        super(3, "Massue");
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
