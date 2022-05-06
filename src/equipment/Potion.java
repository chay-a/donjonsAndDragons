package equipment;

import event.IEvent;

public abstract class Potion implements IEvent {
    private int life;

    public Potion(int life) {
        this.life = life;
    }

    /**
     * Get the life the potion give
     * @return int
     */
    public int getLife() {
        return life;
    }

    @Override
    public String trigger() {
        return "Vous avez trouvé : " + this.getClass().getSimpleName();
    }
}
