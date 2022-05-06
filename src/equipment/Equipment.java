package equipment;

import event.IEvent;

public abstract class Equipment implements IEvent {
    private int strength;

    public Equipment(int strength) {
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
        return "Vous avez trouvé : " + this.getClass().getSimpleName();
    }
}
