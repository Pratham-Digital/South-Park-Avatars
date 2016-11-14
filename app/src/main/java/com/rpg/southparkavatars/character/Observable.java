package com.rpg.southparkavatars.character;

import com.rpg.southparkavatars.observer.Observer;

public interface Observable {
    void attach(Observer observer);

    void notifyAllObservers();
}
