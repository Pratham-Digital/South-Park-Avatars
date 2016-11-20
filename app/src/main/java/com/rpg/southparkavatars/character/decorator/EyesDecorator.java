package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.head.concrete.Beard;
import com.rpg.southparkavatars.character.head.concrete.Eyes;

public class EyesDecorator extends CharacterDecorator {
    public EyesDecorator(AbstractCharacter character) {
        super(character, Eyes.class);
    }
}
