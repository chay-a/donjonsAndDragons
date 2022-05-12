package character;

import Menu.Menu;
import equipment.Equipment;
import inventory.Inventory;
import List.CharacterInGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Hero extends Character{
    private int maxLife;
    private int maxStrength;
    private Equipment equipment;
    private Inventory inventory;
    private static Map<String, String> allCharacters = new HashMap<String, String>() {{
        put("Guerrier", "character.hero.Warrior");
        put("Magicien", "character.hero.Wizard");
        put("Elfe", "character.hero.Elf");
        put("Chopeur", "character.hero.Nicker");
    }};

    public Hero(int life, int strength, int maxLife, int maxStrength) {
      this(life, strength, maxLife, maxStrength, null, new Inventory());
    }

    public Hero(int life, int strength, int maxLife, int maxStrength, Equipment equipment, Inventory inventory){
        super(life, strength);
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
        this.equipment = equipment;
        this.inventory = inventory;
    }

    /**
     * Return the result of the fight
     * @return String
     */
    @Override
    public String throwBlow(Character opponent) {
        int enemyLife = opponent.getLife();
        int attack;
        if (this.equipment != null) {
            int strengthAttack = this.getStrength() + this.equipment.getEffect();
            attack = Math.min(strengthAttack, this.maxStrength);
        } else {
            attack = this.getStrength();
        }
        opponent.setLife(enemyLife - attack);
        return "Vous avez enlevé " + attack + " points de vie à l'ennemi \nL'ennemi a " + opponent.getLife() + " points de vie";
    }

    /**
     * Add new life of the character
     */
    public void addLife(int life) {
        int newLife = super.life + life;
        super.setLife(Math.min(newLife, this.maxLife));
    }

    /**
     * Set new equipment in the inventory
     * @param equipment Equipment
     */
    public void setInventory(Equipment equipment, Menu menu) {
        this.inventory.setEquipment(equipment , menu);
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
        String equipmentString;
        if (this.equipment == null) {
            equipmentString = "aucun";
        } else {
            equipmentString = this.equipment.getClass().getSimpleName();
        }
        return "Votre personnage est : " + Hero.getInternalNameFromClassName(this.getClass().getName()) + "\n" + "Le nom de votre personnage : " + super.getName() + "\n" +
                "La vie de votre personnage : " + super.getLife() + ", max : " + this.maxLife + "\n" +
                "La force de votre personnage : " + super.getStrength() + ", max : " + this.maxStrength + "\n" +
                "Votre équipement : " + equipmentString;
    }

    /**
     * Get the equipment of the character
     * @param equipment Equipment
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }


    /**
     * Set the equipment of the character
     * @return Equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    public abstract void reset();

    /**
     * Set the MaxLife of the character
     */
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Set the MaxStrength of the character
     */
    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    public List<Equipment> getInventory() {
        List<Equipment> inventory = new ArrayList<>();
        inventory.addAll(this.inventory.getPotions());
        inventory.addAll(this.inventory.getEquipments());
        return inventory;
    }

    public void inventoryActions(CharacterInGame characterInGame, Menu menu) {
        String userInput = menu.requestInventoryAction();
        List<Equipment> inventory = this.getInventory();
        if (userInput.equalsIgnoreCase("retour")) {
            return;
        }
        for (Equipment equipment1 : inventory) {
            if (userInput.equals(equipment1.toString())) {
                this.inventory.EquipmentAction(characterInGame,equipment1, menu);
            }
        }
    }
}
