package dnd.sort;

import dnd.character.inGame.CharacterInGame;

import java.util.Comparator;

public class SortByPosition implements Comparator<CharacterInGame> {

    public int compare(CharacterInGame character1, CharacterInGame character2) {
        return Integer.compare(character1.getPosition(), character2.getPosition());
    }
}
