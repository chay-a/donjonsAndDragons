package event;

import character.Hero;

public interface IEvent {
    String trigger();

    String action(Hero hero);
}
