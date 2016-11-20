package com.rpg.southparkavatars.memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> savedList = new ArrayList<Memento>();

    public void addMemento(Memento memento) {
        savedList.add(memento);
    }

    public Memento getMemento() {
        if (savedList.size() == 0) {
            return null;
        } else {
            return savedList.remove(savedList.size() - 1);
        }
    }
}
