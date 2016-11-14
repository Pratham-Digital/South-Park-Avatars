package com.rpg.southparkavatars.character.clothing;

import java.io.File;

public enum Clothes {
    BACK("clothing" + File.separator + "back"),
    GLASSES("clothing" + File.separator + "glasses"),
    HAND("clothing" + File.separator + "hand"),
    HAT("clothing" + File.separator + "hat"),
    NECKLACE("clothing" + File.separator + "necklace"),
    PANTS("clothing" + File.separator + "pants"),
    SHIRT("clothing" + File.separator + "shirt");

    private String path;

    Clothes(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
