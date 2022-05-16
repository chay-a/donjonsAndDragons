package dnd.character;

import dnd.character.inGame.CharacterInGame;
import dnd.exceptions.CharacterFleeException;
import dnd.exceptions.EnemyAlreadyDeadException;
import dnd.menu.Menu;
import dnd.event.IEvent;

public abstract class Enemy extends Character implements IEvent {

    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Enemy(String name, int life, int strength) {
        super(name, life, strength);
    }

    /**
     * Play the action of the dnd.event
     * @param characterInGame CharacterInGame
     */
    @Override
    public void action(CharacterInGame characterInGame, Menu menu) throws CharacterFleeException {
        menu.displayEnemyEvent(this.getName());
        boolean isFight = true;
        while (isFight) {
            boolean isRequestAction = false;
            while (!isRequestAction) {
                switch (menu.requestFightAction()) {
                    case "attaque" :
                        isRequestAction = true;
                        try {
                            isFight = this.isFight(characterInGame, isFight, menu);
                        } catch (EnemyAlreadyDeadException e) {
                            menu.displayEnemyAlreadyDead();
                        }
                        break;
                    case "fuir":
                        throw new CharacterFleeException();
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Play a round of a fight
     * @param characterInGame characterInGame
     * @param isFight boolean
     * @return boolean
     */
    private boolean isFight(CharacterInGame characterInGame, boolean isFight, Menu menu) throws EnemyAlreadyDeadException {
        Hero character = characterInGame.getCharacter();
        if (this.getLife() <=0) {
            throw new EnemyAlreadyDeadException();
        }
        character.throwBlow(this, menu);
        if (this.getLife() <= 0) {
            menu.displayEnemyDead();
            isFight = false;
        } else {
            this.throwBlow(character, menu);
            if (character.getLife() <= 0) {
                isFight = false;
                menu.displayCharacterDead();
                characterInGame.setDead(true);
            }
        }
        return isFight;
    }

    @Override
    public void throwBlow(Character opponent, Menu menu) {
        int heroLife = opponent.getLife();
        opponent.setLife(heroLife - this.getStrength());
        menu.displayEnemyAction(this.getStrength(), opponent.getLife());
    }
}
