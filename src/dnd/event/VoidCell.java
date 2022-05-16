package dnd.event;

import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;

public class VoidCell implements IEvent {

    @Override
    public String trigger() {
        return "Cette case est vide...";
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {

    }
}
