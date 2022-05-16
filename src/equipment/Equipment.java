package equipment;

import Menu.Menu;
import character.Hero;
import event.IEvent;
import inventory.IIventory;

import java.util.List;

public abstract class Equipment implements IEvent, IIventory {
    private int effect;
    private String name;

    public Equipment(int effect, String name) {
        this.name = name;
        this.effect = effect;
    }


    /**
     * Ask what to do with equipment
     * @param menu Menu
     * @param character Hero
     */
    public void takeEquipment(Menu menu, Hero character) {
        String userInput;
        boolean isEquipmentEventResolve = false;
        while (!isEquipmentEventResolve) {
            userInput = menu.requestTakeEquipment(this.getEffect()).toLowerCase();
            switch (userInput) {
                case "oui":
                    character.setEquipment(this);
                    menu.displayCharacterTakeEquipment();
                    isEquipmentEventResolve = true;
                    break;
                case "non":
                    menu.displayCharacterDidntTakeEquipment();
                    isEquipmentEventResolve = true;
                    break;
                case "inventaire" :
                    character.setInventory(this, menu);
                    isEquipmentEventResolve = true;
                    break;
                case "quitter":
                    menu.quitGame();
                    break;
                default:
                    menu.displayInvalidUserInput();
            }
        }
    }

    /**
     * get the effect of the equipment
     * @return int
     */
    public int getEffect() {
        return effect;
    }

    /**
     * Return message what type of event it is
     * @return String
     */
    @Override
    public String trigger() {
        return "Vous avez trouvé : " + this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void fullInventory(Menu menu, List<IIventory> list) {
        boolean isRequestTakeElementValid = false;
        while(!isRequestTakeElementValid) {
            menu.displayFullInventory();
            String userInput = menu.requestInventoryElementChange().toLowerCase();
            switch (userInput) {
                case "oui":
                    isRequestTakeElementValid = isRequestTakeElementValid(menu, list);
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

    private boolean isRequestTakeElementValid(Menu menu, List<IIventory> list) {
        boolean isRequestTakeElementValid;
        String userInput;
        isRequestTakeElementValid = true;
        boolean isRequestValid = false;
        while (!isRequestValid) {
            if (this instanceof Potion) {
                menu.displayPotionInventory(list);
            } else {
                menu.displayEquipmentInventory(list);
            }
            userInput = menu.requestElementToChangeInInventory();
            for (IIventory elementInArray : list) {
                if (userInput.equals(elementInArray.toString())) {
                    // Loop break
                    isRequestValid = true;
                    int index = list.indexOf(elementInArray);
                    list.set(index, this);
                    break;
                }
            }
        }
        return isRequestTakeElementValid;
    }
}
