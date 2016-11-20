package com.rpg.southparkavatars.character.decorator;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.character.Skin;

import java.util.List;

public class SkinDecorator extends CharacterDecorator {
    public SkinDecorator(AbstractCharacter character) {
        super(character, Skin.class);
    }

    @Override
    public DrawableItem find(Class<?> classToBeFound) {
        return character.find(classToBeFound);
    }

    @Override
    public List<DrawableItem> display() {
        List<DrawableItem> list = character.display();
        DrawableItem item = character.getSkin();
        if (item != null) {
            list.add(item);
        }
        return list;
    }
}
