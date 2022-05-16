package dnd.character;

import dnd.menu.Menu;
import dnd.equipment.Equipment;
import dnd.inventory.IIventory;
import dnd.inventory.Inventory;
import dnd.character.inGame.CharacterInGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Hero extends Character{
    private Long id;
    private int maxLife;
    private int maxStrength;
    private Equipment equipment;
    private Inventory inventory;
    private static Map<String, String> allCharacters = new HashMap<String, String>() {{
        put("Guerrier", "dnd.character.hero.Warrior");
        put("Magicien", "dnd.character.hero.Wizard");
        put("Elfe", "dnd.character.hero.Elf");
        put("Chopeur", "dnd.character.hero.Nicker");
    }};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hero(int life, int strength, Long id, int maxLife, int maxStrength, Equipment equipment, Inventory inventory) {
        super(life, strength);
        this.id = id;
        this.maxLife = maxLife;
        this.maxStrength = maxStrength;
        this.equipment = equipment;
        this.inventory = inventory;
    }

    public Hero(int life, int strength, int maxLife, int maxStrength) {
      this(life, strength, maxLife, maxStrength, null, new Inventory());
    }

    public Hero(int life, int strength, int maxLife, int maxStrength, Equipment equipment, Inventory inventory){
        this(life, strength, null, maxLife, maxStrength, equipment, inventory);
    }

    /**
     * Take the opponent and subtract to its life the strength of the object
     * @param opponent Character
     * @param menu Menu
     */
    @Override
    public void throwBlow(Character opponent, Menu menu) {
        int enemyLife = opponent.getLife();
        int attack;
        if (this.equipment != null) {
            int strengthAttack = this.getStrength() + this.equipment.getEffect();
            attack = Math.min(strengthAttack, this.maxStrength);
        } else {
            attack = this.getStrength();
        }
        opponent.setLife(enemyLife - attack);
        menu.displayCharacterFight(attack, opponent.getLife());
    }

    /**
     * Add new life of the dnd.character
     */
    public void addLife(int life) {
        int newLife = super.life + life;
        super.setLife(Math.min(newLife, this.maxLife));
    }

    /**
     * Set new dnd.equipment in the dnd.inventory
     * @param equipment Equipment
     */
    public void setInventory(Equipment equipment, Menu menu) {
        this.inventory.setEquipment(equipment , menu);
    }

    public void resetInventory() {
        this.inventory = null;
    }

    /**
     * Get the French name of the dnd.character type
     * @param className String
     * @return String
     */
    public static String getInternalNameFromClassName(String className) {
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
     * Get the dnd.equipment of the dnd.character
     * @param equipment Equipment
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }


    /**
     * Set the dnd.equipment of the dnd.character
     * @return Equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    public abstract void reset();

    /**
     * Set the MaxLife of the dnd.character
     */
    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    /**
     * Set the MaxStrength of the dnd.character
     */
    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    public List<Equipment> getInventory() {
        List<Equipment> inventory = new ArrayList<>();
        for (IIventory potion : this.inventory.getPotions()) {
            int index = this.inventory.getPotions().indexOf(potion);
            inventory.add((Equipment) potion);
        }
        for (IIventory equipment : this.inventory.getEquipments()) {
            int index = this.inventory.getEquipments().indexOf(equipment);
            inventory.add((Equipment) equipment);
        }
        return inventory;
    }

    /**
     * Ask the user the wanted action and call method  according to action
     * @param characterInGame CharacterInGame
     * @param menu Menu
     */
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
