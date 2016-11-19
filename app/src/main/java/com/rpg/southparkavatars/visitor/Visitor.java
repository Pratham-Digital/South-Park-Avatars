package com.rpg.southparkavatars.visitor;

import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.CompositeAbstractClothing;



public class Visitor  implements ClothingVisitor{
    private int coolness=0;
    @Override
    public void visit(CompositeAbstractClothing cloth) {
        this.coolness+=cloth.getCoolness();
    }

    public void visit(AbstractClothing cloth){
        this.coolness+=cloth.getCoolness();
    }

    public int getOverallCoolness(){
        return this.coolness;
    }

}
