package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.head.concrete.Hand;

import java.util.List;

public class HandDecorator extends CharacterDecorator {
    public HandDecorator(AbstractCharacter character) {
        super(character, Hand.class);
    }

    @Override
    public List<DrawableItem> display() {
        List<DrawableItem> list = character.display();
        DrawableItem item = character.getHand();
        if (item != null) {
            list.add(item);
        }
        return list;
    }
}
