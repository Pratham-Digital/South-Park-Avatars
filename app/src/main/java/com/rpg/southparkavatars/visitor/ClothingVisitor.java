package com.rpg.southparkavatars.visitor;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.CompositeClothing;


public interface ClothingVisitor {
    void visit(CompositeClothing cloth);

    void visit(AbstractClothing cloth);
}
