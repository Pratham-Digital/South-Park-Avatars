package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.clothing.concrete.Necklace;

public class NecklaceDecorator extends CharacterDecorator {
    public NecklaceDecorator(AbstractCharacter character) {
        super(character, Necklace.class);
    }
}
