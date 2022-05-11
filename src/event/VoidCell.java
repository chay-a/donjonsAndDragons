package event;

import List.CharacterInGame;
import Menu.Menu;

public class VoidCell implements IEvent {

    @Override
    public String trigger() {
        return "Cette case est vide...";
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {

    }
}
