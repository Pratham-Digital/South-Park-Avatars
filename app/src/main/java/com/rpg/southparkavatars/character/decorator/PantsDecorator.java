package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Pants;

public class PantsDecorator extends CharacterDecorator {
    public PantsDecorator(AbstractCharacter character) {
        super(character, Pants.class);
    }
}
