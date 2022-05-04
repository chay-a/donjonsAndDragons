package character;

public abstract class Character {
    private String name;
    private int life;
    private int strength;
    private int maxLife;
    private int maxStrength;


    public Character() {

    }

    /**
     * Hydrate the object with every value sent in parameters and name set to null
     * @param life null life of the object
     * @param strength strength of the object
     * @param maxLife max Life of the object
     * @param maxStrength max Strength of the object
     */
    public Character(int life, int strength, int maxLife, int maxStrength) {
        this(null, life, strength, maxLife, maxStrength);
    }

    /**
     * Hydtrate the object with every values define in parameters
     * @param name name of the object
     * @param life life of the object
     * @param strength strength of the object
     * @param maxLife max Life of the object
     * @param maxStrength max strength of the object
     */
    public Character(String name, int life, int strength, int maxLife, int maxStrength) {
        this.name = name;
        this.life = life;
        this.strength = strength;
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
    }


    /**
     * Get the name of the object
     * @return String name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the character
     * @param name name of the character
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
     * Set the strength of the object without being higher than the max strength
     * @param strength the new strength to add
     */
    public void setStrength(int strength) {
        int newStrength = this.strength + strength;
        if (newStrength >= this.maxStrength) {
            newStrength = this.maxStrength;
        }
        this.strength = newStrength;
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
     * Get the maximum life of the object
     * @return int life maximum of the object
     */
    public int getMaxLife() {
        return maxLife;
    }

    /**
     * Get the maximum strength of the object
     * @return int strength maximum of the object
     */
    public int getMaxStrength() {
        return maxStrength;
    }

    @Override
    public String toString() {
        return "Le nom de votre personnage : " + this.name + "\n" +
                "La vie de votre personnage : " + this.life + ", max : " + this.maxLife + "\n" +
                "La force de votre personnage : " + this.strength + ", max : " + this.maxStrength;
    }
}
