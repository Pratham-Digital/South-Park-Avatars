package com.rpg.southparkavatars.observer;

import com.rpg.southparkavatars.new_cloth.DrawState;

public class CustomClothingEventArgs {
    private int globalColor;
    private DrawState drawState;

    public CustomClothingEventArgs(int globalColor, DrawState drawState) {
        this.globalColor = globalColor;
        this.drawState = drawState;
    }

    public int getGlobalColor() {
        return globalColor;
    }

    public DrawState getDrawState() {
        return drawState;
    }
}
