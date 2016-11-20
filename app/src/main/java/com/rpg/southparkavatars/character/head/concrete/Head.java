package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;

public class Head extends AbstractHeadFeature {
    public Head(@JsonProperty("path") String path) {
        super(path);

        setScalingFeatures();
    }

    public Head(Head head) {
        super(head);

        setScalingFeatures();
    }

    private void setScalingFeatures() {
        setScale(0.9f);
        setXPosDivider(2);
        setYPosDivider(2.95f);
    }
}
