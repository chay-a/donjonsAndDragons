package equipment.spell;

import character.Hero;
import equipment.Spell;

public class Fireball extends Spell {
    public Fireball() {
        super(7, "Boule de feu");
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
