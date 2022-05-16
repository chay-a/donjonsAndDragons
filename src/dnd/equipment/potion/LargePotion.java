package dnd.equipment.potion;

import dnd.character.inGame.CharacterInGame;
import dnd.character.Hero;
import dnd.equipment.Potion;

public class LargePotion extends Potion {

    public LargePotion() {
        super(5, "Grande potion");
    }


    @Override
    public void use(CharacterInGame characterInGame) {
        Hero character = characterInGame.getCharacter();
        character.addLife(this.getEffect());
    }
}
