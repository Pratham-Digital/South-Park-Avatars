package com.rpg.southparkavatars.character.clothing;

import java.util.ArrayList;
import java.util.List;

public class CompositeClothing extends Clothing {
    private List<Clothing> clothes = new ArrayList<>();

    public CompositeClothing() {
        super(0);
    }

    public void add(Clothing clothing) {
        clothes.add(clothing);
    }

    public void remove(Clothing clothing) {
        clothes.remove(clothing);
    }

    public Clothing getChild(int index) {
        if (index >= 0 && index < clothes.size()) {
            return clothes.get(index);
        } else {
            return null;
        }
    }
}
