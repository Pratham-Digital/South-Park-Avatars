package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.head.concrete.Mouth;

public class MouthDecorator extends CharacterDecorator {
    public MouthDecorator(AbstractCharacter character) {
        super(character, Mouth.class);
    }
}
