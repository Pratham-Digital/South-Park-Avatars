package com.rpg.southparkavatars.visitor;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeAbstractClothing;


public interface ClothingVisitor {
    void visit(CompositeAbstractClothing cloth);

    void visit(AbstractClothing cloth);
}
