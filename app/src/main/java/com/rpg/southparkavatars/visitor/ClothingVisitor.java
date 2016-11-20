package com.rpg.southparkavatars.visitor;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;

public interface ClothingVisitor {
    void visit(AbstractClothing cloth);
}
