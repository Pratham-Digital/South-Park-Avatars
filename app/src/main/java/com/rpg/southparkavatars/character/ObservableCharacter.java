package com.rpg.southparkavatars.character;

import com.rpg.southparkavatars.observer.CharacterChangedEvent;
import com.rpg.southparkavatars.observer.CharacterObserver;

public interface ObservableCharacter {
    void attach(CharacterObserver observer);

    void notifyAllObservers(CharacterChangedEvent event);
}
