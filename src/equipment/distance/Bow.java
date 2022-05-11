package equipment.distance;

import character.Hero;
import equipment.Distance;

public class Bow extends Distance {
    public Bow() {
        super(5, "Arc");
    }

    @Override
    public String action(Hero hero) {
        return null;
    }
}
