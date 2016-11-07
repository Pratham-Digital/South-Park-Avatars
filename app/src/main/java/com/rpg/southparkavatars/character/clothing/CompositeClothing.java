package com.rpg.southparkavatars.character.clothing;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeClothing implements Clothing, Iterable<Clothing> {
    private int coolness = 0;
    private List<Clothing> clothes = new ArrayList<>();

    public CompositeClothing() {
        super();
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

    @Override
    public Iterator<Clothing> iterator() {
        return clothes.iterator();
    }

    @Override
    public int getCoolness() {
        return coolness;
    }

    @Override
    public int getrId() {
        return 0;
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }
}
