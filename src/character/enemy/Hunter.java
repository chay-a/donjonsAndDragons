package character.enemy;

import List.CharacterInGame;
import Menu.Menu;
import character.Enemy;
import character.hero.Nicker;

public class Hunter extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Hunter() {
        super("Chasseur", 16, 11);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        if (characterInGame.getCharacter() instanceof Nicker) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyDoesntCare();
        }
    }
}
