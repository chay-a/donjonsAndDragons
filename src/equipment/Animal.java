package equipment;

import character.inGame.CharacterInGame;
import menu.Menu;
import character.Hero;
import character.hero.Nicker;

import java.util.HashMap;
import java.util.Map;

public abstract class Animal extends Equipment {
    private static Map<String, String> translation = new HashMap<>() {{
        put("Chat", "equipment.animal.Cat");
        put("Faucon", "equipment.animal.Falcon");
    }};

    public Animal(int effect, String name) {
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
    public void action(CharacterInGame characterInGame, Menu menu) {
        Hero character = characterInGame.getCharacter();
        if (character instanceof Nicker) {
            super.takeEquipment(menu, character);
        } else {
            menu.displayCharacterCantTakeEquipment();
        }
    }

    @Override
    public String toString() {
        return this.getTranslationFromClassName();
    }
}
