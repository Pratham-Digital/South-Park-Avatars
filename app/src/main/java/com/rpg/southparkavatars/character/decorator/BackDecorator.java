package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Back;

public class BackDecorator extends CharacterDecorator {
    public BackDecorator(AbstractCharacter character) {
        super(character, Back.class);
    }
}
