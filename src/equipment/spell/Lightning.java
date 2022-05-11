package equipment.spell;

import character.Hero;
import equipment.Spell;

public class Lightning extends Spell {
    public Lightning() {
        super(2, "Eclair");
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
