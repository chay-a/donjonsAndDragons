package equipment;

import character.inGame.CharacterInGame;
import menu.Menu;
import character.Hero;
import character.hero.Elf;

import java.util.HashMap;
import java.util.Map;

public abstract class Distance extends Equipment{
    private static Map<String, String> translation = new HashMap<>() {{
        put("Arc", "equipment.distance.Bow");
        put("Arbalète", "equipment.distance.Arbalest");
    }};

    public Distance(int effect, String name) {
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
        Hero character = characterInGame.getCharacter();
        if (character instanceof Elf) {
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
