package equipment.potion;

import character.Hero;
import equipment.Potion;

public class SmallPotion extends Potion {
    public SmallPotion() {
        super(2);
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
