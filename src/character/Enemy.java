package character;

import event.IEvent;

public abstract class Enemy extends Character implements IEvent {

    public Enemy(String name, int life, int strength) {
        super(name, life, strength);
    }

    @Override
    public String trigger() {
        return "vous êtes tombé sur : " + this.name;
    }

    @Override
    public String action(Hero hero) {
        int heroLife = hero.getLife();
        hero.setLife(heroLife - this.getStrength());
        return "L'ennemi vous a enlevé " + this.getStrength() + " points de vie \nIl vous en reste " + hero.getLife();

    }
}
