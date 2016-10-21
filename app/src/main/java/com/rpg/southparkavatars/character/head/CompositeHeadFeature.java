package com.rpg.southparkavatars.character.head;

import java.util.ArrayList;
import java.util.List;

public class CompositeHeadFeature extends HeadFeature {
    private List<HeadFeature> headFeatures = new ArrayList<>();

    public CompositeHeadFeature() {
    }

    public void add(HeadFeature headFeature) {
        headFeatures.add(headFeature);
    }

    public void remove(HeadFeature headFeature) {
        headFeatures.remove(headFeature);
    }
}
