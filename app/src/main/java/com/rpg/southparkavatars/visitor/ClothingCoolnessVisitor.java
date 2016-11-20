package com.rpg.southparkavatars.visitor;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;

public class ClothingCoolnessVisitor implements ClothingVisitor {
    private int coolness = 0;

    @Override
    public void visit(AbstractClothing clothing) {
        this.coolness += clothing.getCoolness();
    }

    public int getOverallCoolness() {
        return this.coolness;
    }
}
