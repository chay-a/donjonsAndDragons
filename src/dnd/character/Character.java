package dnd.character;

import dnd.menu.Menu;

public abstract class Character {
    protected String name;
    protected int life;
    protected int strength;

    /**
     * Hydrate the object with every value sent in parameters and name set to null
     * @param life null life of the object
     * @param strength strength of the object
     */
    public Character(int life, int strength) {
        this(null, life, strength);
    }

    /**
     * Set the name of the strength
     * @param strength int
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Hydtrate the object with every value define in parameters
     * @param name name of the object
     * @param life life of the object
     * @param strength strength of the object
     */
    public Character(String name, int life, int strength) {
        this.name = name;
        this.life = life;
        this.strength = strength;
    }


    /**
     * Get the name of the object
     * @return String name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the dnd.character
     * @param name name of the dnd.character
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the strength of the object
     * @return int strength of the object
     */
    public int getStrength() {
        return strength;
    }


    /**
     * Get the life of the object
     * @return int life of the object
     */
    public int getLife() {
        return life;
    }

    /**
     * Set the life of the object
     * @param life life of the object
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * Character throw a blow
     * @param opponent Character
     * @param menu Menu
     */
    public abstract void throwBlow(Character opponent, Menu menu);

}
