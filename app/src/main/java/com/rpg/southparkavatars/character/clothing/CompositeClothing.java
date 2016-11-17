package com.rpg.southparkavatars.character.clothing;

import android.graphics.Bitmap;
import android.net.Uri;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeClothing extends Clothing implements Iterable<Clothing> {
    @JsonProperty("coolness")
    private int coolness = 0;
    @JsonProperty("clothes")
    private List<Clothing> clothes = new ArrayList<>();

    public CompositeClothing() {
        super(0, "");
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

    public List<Clothing> getClothes() {
        return clothes;
    }
}
