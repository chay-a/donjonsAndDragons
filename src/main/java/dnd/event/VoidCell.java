package dnd.event;

import dnd.character.inGame.CharacterInGame;
import dnd.menu.Menu;

public class VoidCell implements IEvent {

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {
        menu.displayVoidCellEvent();
    }
}
