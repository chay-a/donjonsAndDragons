package character;

import equipment.Equipment;

import java.util.HashMap;
import java.util.Map;

public abstract class Hero extends Character{
    private int maxLife;
    private int maxStrength;
    private Equipment equipment;
    private static Map<String, String> allCharacters = new HashMap<String, String>() {{
        put("Guerrier", "character.hero.Warrior");
        put("Magicien", "character.hero.Wizard");
    }};

    public Hero(int life, int strength, int maxLife, int maxStrength) {
      this(life, strength, maxLife, maxStrength, null);
    }

    public Hero(int life, int strength, int maxLife, int maxStrength, Equipment equipment){
        super(life, strength);
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
        this.equipment = equipment;
    }

    /**
     * Set the strength of the character with the strength of the equipment
     */
    @Override
    public void setStrength() {
        this.strength += this.equipment.getStrength();
    }

    /**
     * Get the French name of the character type
     * @param className String
     * @return String
     */
    private static String getInternalNameFromClassName(String className) {
        for (String frName : allCharacters.keySet()) {
            String cName = (String) allCharacters.get(frName);
            if (className.equals(cName)) {
                return frName;
            }
        }
        return null;
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

    /**
     * Get translation of the Heroes
     * @return Map<String, String>
     */
    public static Map<String, String> getAllCharacters() {
        return allCharacters;
    }

    @Override
    public String toString() {
        return "Votre personnage est : " + Hero.getInternalNameFromClassName(this.getClass().getName()) + "\n" + "Le nom de votre personnage : " + super.getName() + "\n" +
                "La vie de votre personnage : " + super.getLife() + ", max : " + this.maxLife + "\n" +
                "La force de votre personnage : " + super.getStrength() + ", max : " + this.maxStrength + "\n";
    }

    /**
     * Get the equipment of the character
     * @param equipment Equipment
     */
    public void setEquipment(Equipment equipment) {
        this.strength -= this.equipment.getStrength();
        this.equipment = equipment;
        this.setStrength();
    }

    /**
     * Set the equipment of the character
     * @return Equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }
}
