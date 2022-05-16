package dnd.character.inGame;

import dnd.character.Hero;

public class CharacterInGame {
    private Hero character;
    private int position;
    private boolean isDead;

    public CharacterInGame(Hero character) {
        this(character, 0, false);
    }

    public CharacterInGame(Hero character, int position, boolean isDead) {
        this.character = character;
        this.position = position;
        this.isDead = isDead;
    }

    public Hero getCharacter() {
        return character;
    }

    public int getPosition() {
        return position;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

}
