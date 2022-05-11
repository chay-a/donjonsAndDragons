package equipment.distance;

import character.Hero;
import equipment.Distance;

public class Arbalest extends Distance {

    public Arbalest() {
        super(7, "Arbalète");
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
