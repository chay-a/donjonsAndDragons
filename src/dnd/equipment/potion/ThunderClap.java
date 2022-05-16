package dnd.equipment.potion;

import dnd.character.inGame.CharacterInGame;
import dnd.equipment.Potion;

public class ThunderClap extends Potion {
    public ThunderClap() {
        super(2, "Coup de tonnerre");
    }


    @Override
    public void use(CharacterInGame characterInGame) {
        // two times the strength of the dnd.character for the next fight only
    }
}
