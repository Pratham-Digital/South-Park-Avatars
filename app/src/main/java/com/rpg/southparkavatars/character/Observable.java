package com.rpg.southparkavatars.character;

import com.rpg.southparkavatars.Observer;

public interface Observable {
    void attach(Observer observer);

    void notifyAllObservers();
}
