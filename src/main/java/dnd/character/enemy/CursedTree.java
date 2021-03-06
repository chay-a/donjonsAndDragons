package dnd.character.enemy;

import dnd.character.inGame.CharacterInGame;
import dnd.exceptions.CharacterFleeException;
import dnd.menu.Menu;
import dnd.character.Enemy;
import dnd.character.hero.Elf;

public class CursedTree extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public CursedTree() {
        super("Arbre maudit", 12, 3);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) throws CharacterFleeException {
        if (characterInGame.getCharacter() instanceof Elf) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyEvent(this.getName());
            menu.displayEnemyDoesntCare();
        }
    }
}
