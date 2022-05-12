package inventory;

import Menu.Menu;
import equipment.Equipment;
import equipment.Potion;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Equipment> equipments = new ArrayList<>();
    private List<Potion> potions = new ArrayList<>();
    private int maxEquipments;
    private int maxPotions;

    public Inventory() {
        this.maxEquipments = 2;
        this.maxPotions = 2;
    }

    /**
     * Add new equipment in the good inventory and ask user if inventory full what to do
     * @param equipment Equipment
     * @param menu Menu
     */
    public void setEquipment(Equipment equipment, Menu menu) {
        if (equipment instanceof Potion) {
            Potion potion = (Potion) equipment;
            if (potions.size() < this.maxPotions) {
                potions.add(potion);
            } else {
                boolean isRequestTakeElementValid = false;
                while(!isRequestTakeElementValid) {
                    // You can't take this potion cause your Inventory is full
                    menu.displayFullInventory();
                    // Ask to change with another element
                    String userInput = menu.requestInventoryElementChange().toLowerCase();
                    switch (userInput) {
                        case "oui":
                            isRequestTakeElementValid = true;
                            boolean isRequestValid = false;
                            while (!isRequestValid) {
                                menu.displayPotionInventory(this.potions);
                                userInput = menu.requestElementToChangeInInventory();
                                for (Potion potionInArray : potions) {
                                    if (userInput.equals(potionInArray.toString())) {
                                        // Loop break
                                        isRequestValid = true;
                                        int index = potions.indexOf(potionInArray);
                                        potions.set(index, potion);
                                        break;
                                    }
                                }
                            }
                            break;
                        case "non":
                            menu.displayCharacterDidntTakeEquipment();
                            isRequestTakeElementValid = true;
                            break;
                        default:
                            menu.displayInvalidUserInput();
                            break;
                    }
                }
            }
        } else {
            if (equipments.size() < this.maxEquipments) {
                equipments.add(equipment);
            } else {
                boolean isRequestTakeElementValid = false;
                while(!isRequestTakeElementValid) {
                    // You can't take this potion cause your Inventory is full
                    menu.displayFullInventory();
                    // Ask to change with another element
                    String userInput = menu.requestInventoryElementChange().toLowerCase();
                    switch (userInput) {
                        case "oui":
                            isRequestTakeElementValid = true;
                            boolean isRequestValid = false;
                            while (!isRequestValid) {
                                menu.displayEquipmentInventory(this.equipments);
                                userInput = menu.requestElementToChangeInInventory();
                                for (Equipment equipmentInArray : equipments) {
                                    if (userInput.equals(equipmentInArray.toString())) {
                                        // Loop break
                                        isRequestValid = true;
                                        int index = equipments.indexOf(equipmentInArray);
                                        equipments.set(index, equipment);
                                        break;
                                    }
                                }
                            }
                            break;
                        case "non":
                            menu.displayCharacterDidntTakeEquipment();
                            isRequestTakeElementValid = true;
                            break;
                        default:
                            menu.displayInvalidUserInput();
                            break;
                    }
                }
            }
        }
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public List<Potion> getPotions() {
        return potions;
    }
}
