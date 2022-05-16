package event;

import character.inGame.CharacterInGame;
import menu.Menu;

public class VoidCell implements IEvent {

    @Override
    public String trigger() {
        return "Cette case est vide...";
    }

    @Override
    public void action(CharacterInGame characterInGame, Menu menu) {

    }
}
