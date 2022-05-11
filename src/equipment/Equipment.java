package equipment;

import event.IEvent;

public abstract class Equipment implements IEvent {
    private int strength;
    private String name;

    public Equipment(int strength, String name) {
        this.name = name;
        this.strength = strength;
    }

    /**
     * get the strength of the equipment
     * @return int
     */
    public int getStrength() {
        return strength;
    }

    @Override
    public String trigger() {
        return "Vous avez trouvé : " + this.name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
