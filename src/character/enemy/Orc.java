package character.enemy;

import List.CharacterInGame;
import Menu.Menu;
import character.Enemy;
import character.hero.Warrior;

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
