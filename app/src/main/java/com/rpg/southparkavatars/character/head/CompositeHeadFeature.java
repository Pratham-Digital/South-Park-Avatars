package com.rpg.southparkavatars.character.head;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeHeadFeature implements HeadFeature, Iterable<HeadFeature> {
    private List<HeadFeature> headFeatures = new ArrayList<>();

    public CompositeHeadFeature() {
    }

    public void add(HeadFeature headFeature) {
        headFeatures.add(headFeature);
    }

    public void remove(HeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    @Override
    public int getrId() {
        return 0;
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public Iterator<HeadFeature> iterator() {
        return headFeatures.iterator();
    }
}
