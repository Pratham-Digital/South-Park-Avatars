package com.rpg.southparkavatars.observer;


public interface ObservableItem {
    void attach(ItemObserver observer);

    void notifyAllObservers();
}
