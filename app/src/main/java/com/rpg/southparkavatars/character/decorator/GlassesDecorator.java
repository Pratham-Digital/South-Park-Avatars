package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Glasses;

public class GlassesDecorator extends CharacterDecorator {
    public GlassesDecorator(AbstractCharacter character) {
        super(character, Glasses.class);
    }
}
