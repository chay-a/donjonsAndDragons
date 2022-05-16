package dnd.event;

import dnd.character.inGame.CharacterInGame;
import dnd.exceptions.CharacterFleeException;
import dnd.menu.Menu;
public interface IEvent {
    String trigger();

    void action(CharacterInGame characterInGame, Menu menu) throws CharacterFleeException;
}
