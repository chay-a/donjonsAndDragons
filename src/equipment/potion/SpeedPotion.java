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
    public void use(CharacterInGame characterInGame) {
        characterInGame.setPosition(characterInGame.getPosition() + 2);
    }
}
