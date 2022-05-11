package equipment.potion;

import List.CharacterInGame;
import Menu.Menu;
import character.Hero;
import equipment.Potion;

public class LargePotion extends Potion {

    public LargePotion() {
        super(5, "Grande potion");
    }


    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        Hero character = characterInGame.getCharacter();
        character.addLife(this.getEffect());
    }
}
