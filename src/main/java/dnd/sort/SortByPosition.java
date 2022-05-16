package dnd.sort;

import dnd.character.inGame.CharacterInGame;

import java.util.Comparator;

public class SortByPosition implements Comparator<CharacterInGame> {

    /**
     * Compare two characterInGame's position
     * @param character1 the first object to be compared.
     * @param character2 the second object to be compared.
     * @return int
     */
    public int compare(CharacterInGame character1, CharacterInGame character2) {
        return Integer.compare(character1.getPosition(), character2.getPosition());
    }
}
