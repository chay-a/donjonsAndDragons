package character.enemy;

import List.CharacterInGame;
import Menu.Menu;
import character.Enemy;
import character.hero.Elf;

public class CursedTree extends Enemy {
    /**
     * Constructor that hydrate value of the parent class Character
     */
    public CursedTree() {
        super("Arbre maudit", 12, 3);
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        if (characterInGame.getCharacter() instanceof Elf) {
            super.action(characterInGame, menu);
        } else {
            menu.displayEnemyDoesntCare();
        }
    }
}
