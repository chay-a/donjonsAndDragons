package equipment.potion;

import character.Hero;
import equipment.Potion;

public class LargePotion extends Potion {

    public LargePotion() {
        super(5, "Grande potion");
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
