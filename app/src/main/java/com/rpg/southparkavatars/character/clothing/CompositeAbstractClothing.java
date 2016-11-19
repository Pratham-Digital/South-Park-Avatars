package com.rpg.southparkavatars.character.clothing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.visitor.ClothingVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeAbstractClothing extends AbstractClothing implements Iterable<AbstractClothing> {
    @JsonProperty("coolness")
    private int coolness = 0;
    @JsonProperty("clothes")
    private List<AbstractClothing> clothes = new ArrayList<>();

    public CompositeAbstractClothing() {
        super(0, "");
    }

    public void add(AbstractClothing clothing) {
        clothes.add(clothing);
    }

    public void remove(AbstractClothing clothing) {
        clothes.remove(clothing);
    }

    public AbstractClothing getChild(int index) {
        if (index >= 0 && index < clothes.size()) {
            return clothes.get(index);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<AbstractClothing> iterator() {
        return clothes.iterator();
    }

    @Override
    public int getCoolness() {
        return coolness;
    }

    public List<AbstractClothing> getClothes() {
        return clothes;
    }

    @Override
    public void accept(ClothingVisitor visitor) {
        visitor.visit(this);
        for(AbstractClothing cloth : this.getClothes()){
            cloth.accept(visitor);
        }
    }
}
