package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.head.concrete.Head;

import java.util.List;

public class HeadDecorator extends CharacterDecorator {
    public HeadDecorator(AbstractCharacter character) {
        super(character, Head.class);
    }

    @Override
    public List<DrawableItem> display() {
        List<DrawableItem> list = character.display();
        DrawableItem item = character.getHead();
        if (item != null) {
            list.add(item);
        }
        return list;
    }
}
