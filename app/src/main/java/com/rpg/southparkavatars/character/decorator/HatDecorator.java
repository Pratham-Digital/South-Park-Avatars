package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Hat;

public class HatDecorator extends CharacterDecorator {
    public HatDecorator(AbstractCharacter character) {
        super(character, Hat.class);
    }
}
