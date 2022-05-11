package character;

import List.CharacterInGame;
import Menu.Menu;
import event.IEvent;

public abstract class Enemy extends Character implements IEvent {

    /**
     * Constructor that hydrate value of the parent class Character
     */
    public Enemy(String name, int life, int strength) {
        super(name, life, strength);
    }

    /**
     * Return message what type of event it is
     * @return String
     */
    @Override
    public String trigger() {
        return "vous êtes tombé sur : " + this.name;
    }

    /**
     * Play the action of the event
     * @param characterInGame CharacterInGame
     */
    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        boolean isFight = true;
        while (isFight) {
            boolean isRequestAction = false;
            while (!isRequestAction) {
                switch (menu.requestFightAction()) {
                    case "attaque" :
                        isRequestAction = true;
                        isFight = this.isFight(characterInGame, isFight, menu);
                        break;
                    case "fuir":
                        isRequestAction = true;
                        isFight = false;
                        menu.displayFlee();
                        characterInGame.setPosition(characterInGame.getPosition() - 2);
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
    private boolean isFight(CharacterInGame characterInGame, boolean isFight, Menu menu) {
        Hero character = characterInGame.getCharacter();
        menu.displayCharacterFight(character.throwBlow(this));
        if (this.getLife() <= 0) {
            menu.displayEnemyDead();
            isFight = false;
        } else {
            menu.displayEnemyAction(this.throwBlow(character));
            if (character.getLife() <= 0) {
                isFight = false;
                menu.displayCharacterDead();
                characterInGame.setDead(true);
            }
        }
        return isFight;
    }

    @Override
    public String throwBlow(Character opponent) {
        int heroLife = opponent.getLife();
        opponent.setLife(heroLife - this.getStrength());
        return "L'ennemi vous a enlevé " + this.getStrength() + " points de vie \nIl vous en reste " + opponent.getLife();
    }
}
