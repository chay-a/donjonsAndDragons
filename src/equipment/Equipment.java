package equipment;

import Menu.Menu;
import character.Hero;
import event.IEvent;

public abstract class Equipment implements IEvent {
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
}
