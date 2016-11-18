package com.rpg.southparkavatars.tool;

public interface ItemPersister<T> {
    void save(T item);

    T[] loadAll();

    String serialize(T object);
}
