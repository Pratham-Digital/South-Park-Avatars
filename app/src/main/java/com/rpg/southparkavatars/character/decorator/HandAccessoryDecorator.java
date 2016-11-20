package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Hand;

public class HandAccessoryDecorator extends CharacterDecorator {
    public HandAccessoryDecorator(AbstractCharacter character) {
        super(character, Hand.class);
    }
}
