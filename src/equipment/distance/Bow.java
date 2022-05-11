package equipment.distance;

import character.Hero;
import equipment.Distance;

public class Bow extends Distance {
    public Bow() {
        super(5, "Arc");
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
