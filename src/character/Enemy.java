package character;

import event.IEvent;

public abstract class Enemy extends Character implements IEvent {

    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Enemy(String name, int life, int strength) {
        super(name, life, strength);
    }

    /**
     * Return message what type of event it is
     * @return String
     */
    @Override
    public String trigger() {
        return "vous êtes tombé sur : " + this.name;
    }

    /**
     * Play the action of the event
     * @param hero Hero
     * @return String
     */
    @Override
    public String action(Hero hero) {
        int heroLife = hero.getLife();
        hero.setLife(heroLife - this.getStrength());
        return "L'ennemi vous a enlevé " + this.getStrength() + " points de vie \nIl vous en reste " + hero.getLife();

    }
}
