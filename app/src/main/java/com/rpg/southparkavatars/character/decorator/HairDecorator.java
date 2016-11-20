package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.head.concrete.Hair;

public class HairDecorator extends CharacterDecorator {
    public HairDecorator(AbstractCharacter character) {
        super(character, Hair.class);
    }
}
