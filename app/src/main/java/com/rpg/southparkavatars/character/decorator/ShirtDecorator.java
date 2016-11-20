package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Shirt;

public class ShirtDecorator extends CharacterDecorator {
    public ShirtDecorator(AbstractCharacter character) {
        super(character, Shirt.class);
    }
}
