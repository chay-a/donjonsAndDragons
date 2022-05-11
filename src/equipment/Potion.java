package equipment;

import event.IEvent;

public abstract class Potion extends Equipment {
    public Potion(int effect, String name) {
        super(effect, name);
    }
}
