package equipment;

import event.IEvent;

public abstract class Equipment implements IEvent {
    private int effect;
    private String name;

    public Equipment(int effect, String name) {
        this.name = name;
        this.effect = effect;
    }

    /**
     * get the effect of the equipment
     * @return int
     */
    public int getEffect() {
        return effect;
    }

    /**
     * Return message what type of event it is
     * @return String
     */
    @Override
    public String trigger() {
        return "Vous avez trouvé : " + this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
