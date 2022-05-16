package event;

import character.inGame.CharacterInGame;
import menu.Menu;
public interface IEvent {
    String trigger();

    void action(CharacterInGame characterInGame, Menu menu);
}
