package equipment.potion;

import List.CharacterInGame;
import Menu.Menu;
import character.Hero;
import equipment.Potion;

public class SmallPotion extends Potion {
    public SmallPotion() {
        super(2, "Petite potion");
    }


    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        Hero character = characterInGame.getCharacter();
        character.addLife(this.getEffect());
    }
}
