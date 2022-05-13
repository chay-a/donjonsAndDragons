package equipment.potion;

import List.CharacterInGame;
import Menu.Menu;
import equipment.Potion;

public class ThunderClap extends Potion {
    public ThunderClap() {
        super(2, "Coup de tonnerre");
    }


    @Override
    public void use(CharacterInGame characterInGame) {
        // two times the strength of the character for the next fight only
    }
}
