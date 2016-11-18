package com.rpg.southparkavatars.character.head.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.head.HeadFeature;

public class Mouth extends HeadFeature {
    public Mouth(@JsonProperty("path") String path) {
        super(path);

        setScale(1f);
        setXPosDivider(2);
        setYPosDivider(1.9f);
    }
}
