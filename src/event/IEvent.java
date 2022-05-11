package event;

import List.CharacterInGame;
import Menu.Menu;
public interface IEvent {
    String trigger();

    void action(CharacterInGame characterInGame, Menu menu);
}
