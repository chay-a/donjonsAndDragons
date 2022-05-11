package equipment.potion;

import character.Hero;
import equipment.Potion;

public class LargePotion extends Potion {

    public LargePotion() {
        super(5, "Grande potion");
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
