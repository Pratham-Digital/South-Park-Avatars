package com.rpg.southparkavatars.character.clothing;

import java.io.File;

public enum Clothing {
    BACK("clothing" + File.separator + "back", 0, 0, 0),
    GLASSES("clothing" + File.separator + "glasses", 0, 0, 0),
    HAND("clothing" + File.separator + "hand", 0, 0, 0),
    HAT("clothing" + File.separator + "hat", 170, 179, 99),
    NECKLACE("clothing" + File.separator + "necklace", 0, 0, 0),
    PANTS("clothing" + File.separator + "pants", 80, 185, 49),
    SHIRT("clothing" + File.separator + "shirt", 190, 185, 81);

    private String path;
    private int typeDimensionValue;
    private int width;
    private int height;

    Clothing(String path, int typeDimensionValue, int width, int height) {
        this.path = path;
        this.typeDimensionValue = typeDimensionValue;
        this.width = width;
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public int getTypeDimensionValue() {
        return typeDimensionValue;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
