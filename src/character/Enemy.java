package character;

import event.IEvent;

public abstract class Enemy extends Character implements IEvent {

    public Enemy(String name, int life, int strength) {
        super(name, life, strength);
    }

    @Override
    public void setStrength() {

    }

    @Override
    public String trigger() {
        return "vous êtes tombé sur : " + this.getClass().getSimpleName();
    }

    @Override
    public String action(Hero hero) {
        int heroLife = hero.getLife();
        hero.setLife(heroLife - this.getStrength());
        return "Il vous a enlevé " + this.getStrength() + " points de vie \n Il vous en reste " + hero.getLife();

    }
}
