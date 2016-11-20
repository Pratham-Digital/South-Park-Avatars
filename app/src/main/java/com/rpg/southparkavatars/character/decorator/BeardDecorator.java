package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.head.concrete.Beard;

public class BeardDecorator extends CharacterDecorator {
    public BeardDecorator(AbstractCharacter character) {
        super(character, Beard.class);
    }
}
