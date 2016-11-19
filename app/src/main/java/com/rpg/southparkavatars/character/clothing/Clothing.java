package com.rpg.southparkavatars.character.clothing;

import java.io.File;

public enum Clothing {
    BACK("clothing" + File.separator + "back", 0),
    GLASSES("clothing" + File.separator + "glasses", 0),
    HAND("clothing" + File.separator + "hand", 0),
    HAT("clothing" + File.separator + "hat", 170),
    NECKLACE("clothing" + File.separator + "necklace", 0),
    PANTS("clothing" + File.separator + "pants", 80),
    SHIRT("clothing" + File.separator + "shirt", 190);

    private String path;
    private int typeDimensionValue;

    Clothing(String path, int typeDimensionValue) {
        this.path = path;
        this.typeDimensionValue = typeDimensionValue;
    }

    public String getPath() {
        return path;
    }

    public int getTypeDimensionValue() {
        return typeDimensionValue;
    }
}
