package equipment.potion;

import character.Hero;
import equipment.Potion;

public class SmallPotion extends Potion {
    public SmallPotion() {
        super(2, "Petite potion");
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
