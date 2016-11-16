package com.rpg.southparkavatars.character.head;

import android.graphics.Bitmap;
import android.net.Uri;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeHeadFeature extends HeadFeature implements Iterable<HeadFeature> {
    private List<HeadFeature> headFeatures = new ArrayList<>();

    public CompositeHeadFeature() {
        super("");
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
    public Iterator<HeadFeature> iterator() {
        return headFeatures.iterator();
    }
}
