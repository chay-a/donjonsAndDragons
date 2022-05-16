package equipment;

import character.inGame.CharacterInGame;
import menu.Menu;
import character.Hero;

public abstract class Potion extends Equipment {
    public Potion(int effect, String name) {
        super(effect, name);
    }


    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        Hero character = characterInGame.getCharacter();
        String userInput;
        boolean isEquipmentEventResolve = false;
        while (!isEquipmentEventResolve) {
            userInput = menu.requestTakePotion(this.getEffect()).toLowerCase();
            switch (userInput) {
                case "oui":
                    this.use(characterInGame);
                    menu.displayCharacterTakePotion();
                    isEquipmentEventResolve = true;
                    break;
                case "non":
                    menu.displayCharacterDidntTakePotion();
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

    public abstract void use(CharacterInGame characterInGame);
}
