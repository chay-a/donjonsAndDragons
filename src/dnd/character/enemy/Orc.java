package dnd.character.enemy;

import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;
import dnd.character.Enemy;
import dnd.character.hero.Warrior;

public class Orc extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Orc() {
        super("Orque", 10, 6);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        if (characterInGame.getCharacter() instanceof Warrior) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyDoesntCare();
        }
    }
}
