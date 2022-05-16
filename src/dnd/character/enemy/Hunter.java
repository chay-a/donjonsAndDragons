package dnd.character.enemy;

import dnd.character.inGame.CharacterInGame;
import dnd.exceptions.CharacterFleeException;
import dnd.menu.Menu;
import dnd.character.Enemy;
import dnd.character.hero.Nicker;

public class Hunter extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Hunter() {
        super("Chasseur", 16, 11);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) throws CharacterFleeException {
        if (characterInGame.getCharacter() instanceof Nicker) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyDoesntCare();
        }
    }
}
