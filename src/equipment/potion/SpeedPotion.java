package equipment.potion;

import List.CharacterInGame;
import Menu.Menu;
import character.Hero;
import equipment.Potion;

public class SpeedPotion extends Potion {
    public SpeedPotion() {
        super(2, "Potion de vitesse");
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        characterInGame.setPosition(characterInGame.getPosition() + 2);
    }
}