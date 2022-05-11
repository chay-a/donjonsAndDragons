package equipment.spell;

import character.Hero;
import equipment.Spell;

public class Fireball extends Spell {
    public Fireball() {
        super(7, "Boule de feu");
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
