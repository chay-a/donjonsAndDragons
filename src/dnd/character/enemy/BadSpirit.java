package dnd.character.enemy;

import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;
import dnd.character.Enemy;
import dnd.character.hero.Wizard;

public class BadSpirit extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public BadSpirit() {
        super("Mauvais esprit", 15, 5);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        if (characterInGame.getCharacter() instanceof Wizard) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyDoesntCare();
        }
    }
}
