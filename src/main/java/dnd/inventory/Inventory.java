package dnd.inventory;

import dnd.menu.Menu;
import dnd.character.Hero;
import dnd.equipment.Equipment;
import dnd.equipment.Potion;
import dnd.character.inGame.CharacterInGame;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<IIventory> equipments = new ArrayList<>();
    private List<IIventory> potions = new ArrayList<>();
    private int maxEquipments;
    private int maxPotions;

    public Inventory() {
        this.maxEquipments = 2;
        this.maxPotions = 2;
    }

    /**
     * Add new dnd.equipment in the good dnd.inventory and ask user if dnd.inventory full what to do
     * @param equipment Equipment
     * @param menu Menu
     */
    public void setEquipment(Equipment equipment, Menu menu) {
        if (equipment instanceof Potion) {
            Potion potion = (Potion) equipment;
            if (potions.size() < this.maxPotions) {
                potions.add(potion);
            } else {
                equipment.fullInventory(menu, this.potions);
            }
        } else {
            if (equipments.size() < this.maxEquipments) {
                equipments.add(equipment);
            } else {
                equipment.fullInventory(menu, this.equipments);
            }
        }
    }

    public List<IIventory> getEquipments() {
        return equipments;
    }

    public List<IIventory> getPotions() {
        return potions;
    }

    public void EquipmentAction(CharacterInGame characterInGame, IIventory equipment, Menu menu) {
       String userInput = menu.requestEquipmentAction();
        switch(userInput) {
            case "utilise" :
                if (equipment instanceof Potion) {
                    Potion potion = (Potion) equipment;
                    potion.use(characterInGame);
                    break;
                }
                this.changeEquipment(characterInGame.getCharacter(), equipment);
                break;
            case "supprime" :
                if (equipment instanceof Potion) {
                    this.potions.remove(equipment);
                    break;
                }
                this.equipments.remove(equipment);
                break;
            case "retour" :
                return;
            default:
                menu.displayInvalidUserInput();
                break;
        }
    }

    private void changeEquipment(Hero character, IIventory equipment) {
        this.equipments.add(character.getEquipment());
        this.equipments.remove(equipment);
        character.setEquipment((Equipment) equipment);
    }
}
