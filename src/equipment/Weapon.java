package equipment;

import java.util.HashMap;
import java.util.Map;

public abstract class Weapon extends Equipment{
    private static Map<String, String> translation = new HashMap<>() {{
        put("Massue", "equipment.weapon.Club");
        put("Epée", "equipment.weapon.Sword");
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
