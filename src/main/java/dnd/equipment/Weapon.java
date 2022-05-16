package dnd.equipment;

import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;
import dnd.character.Hero;
import dnd.character.hero.Warrior;

import java.util.HashMap;
import java.util.Map;

public abstract class Weapon extends Equipment{
    private static Map<String, String> translation = new HashMap<>() {{
        put("Massue", "dnd.equipment.weapon.Club");
        put("Epée", "dnd.equipment.weapon.Sword");
    }};

    public Weapon(int effect, String name) {
        super(effect, name);
    }

    /**
     * Get the translation according to class names
     * @return Map<String,String>
     */
    public static Map<String, String> getTranslation() {
        return translation;
    }

    /**
     *
     * @param characterInGame CharacterInGame
     * @param menu Menu
     */
    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        menu.displayEquipmentEvent(this.getName());
        Hero character = characterInGame.getCharacter();
        if (character instanceof Warrior) {
            super.takeEquipment(menu, character);
        } else {
            menu.displayCharacterCantTakeEquipment();
        }
    }

    /**
     * Get the translation value from the name of the class
     * @return String
     */
    private String getTranslationFromClassName() {
        String equipmentClass = this.getClass().getName();
        for (String frName : translation.keySet()) {
            String translationEquipmentClass = translation.get(frName);
            if (translationEquipmentClass.equals(equipmentClass)) {
                return frName;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getTranslationFromClassName();
    }
}
