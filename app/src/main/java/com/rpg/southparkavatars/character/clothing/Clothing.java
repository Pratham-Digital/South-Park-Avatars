package com.rpg.southparkavatars.character.clothing;

public abstract class Clothing {
    private int coolness;

    public Clothing(int coolness) {
        this.coolness = coolness;
    }

    public int getCoolness() {
        return coolness;
    }
}
