package equipment.spell;

import character.Hero;
import equipment.Spell;

public class Lightning extends Spell {
    public Lightning() {
        super(2, "Eclair");
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
