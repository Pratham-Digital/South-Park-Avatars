package com.rpg.southparkavatars.character.head;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompositeHeadFeature extends AbstractHeadFeature implements Iterable<AbstractHeadFeature> {
    @JsonProperty("headFeatures")
    private List<AbstractHeadFeature> headFeatures = new ArrayList<>();

    public CompositeHeadFeature() {
        super("");
    }

    public void add(AbstractHeadFeature headFeature) {
        headFeatures.add(headFeature);
    }

    public void remove(AbstractHeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }

    @Override
    public int getrId() {
        return 0;
    }

    @Override
    public Iterator<AbstractHeadFeature> iterator() {
        return headFeatures.iterator();
    }

    public List<AbstractHeadFeature> getHeadFeatures() {
        return headFeatures;
    }
}
