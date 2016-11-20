package com.rpg.southparkavatars.tool;

import com.rpg.southparkavatars.character.Character;

public interface ItemPersister<T> {
    void save(T item);

    T[] loadAll();

    String serialize(T object);

    void remove(Character character);
}
