package dnd.equipment.potion;

import dnd.character.inGame.CharacterInGame;
import dnd.equipment.Potion;

public class SpeedPotion extends Potion {
    public SpeedPotion() {
        super(2, "Potion de vitesse");
    }

    @Override
    public void use(CharacterInGame characterInGame) {
        characterInGame.setPosition(characterInGame.getPosition() + 2);
    }
}
