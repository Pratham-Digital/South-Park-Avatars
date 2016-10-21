package com.rpg.southparkavatars.character;

public interface CharacterChangedDelegate<T> {
    void invoke(T object);
}
