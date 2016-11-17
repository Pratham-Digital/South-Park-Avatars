package com.rpg.southparkavatars.tool;

import java.io.File;

public interface ItemPersister<T> {
    void save(T item);

    T[] loadAll();

    String serialize(T object);
}
