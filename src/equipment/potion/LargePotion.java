package equipment.potion;

import character.inGame.CharacterInGame;
import character.Hero;
import equipment.Potion;

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
