package dnd.equipment.potion;

import dnd.character.inGame.CharacterInGame;
import dnd.character.Hero;
import dnd.equipment.Potion;

public class SmallPotion extends Potion {
    public SmallPotion() {
        super(2, "Petite potion");
    }


    @Override
    public void use(CharacterInGame characterInGame) {
        Hero character = characterInGame.getCharacter();
        character.addLife(this.getEffect());
    }
}
