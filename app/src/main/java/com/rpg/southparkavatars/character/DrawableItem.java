package com.rpg.southparkavatars.character;

public abstract class DrawableItem {
    private transient int rId;
    private transient float scale;
    private transient float xPosDivider;
    private transient float yPosDivider;

    protected String path;

    public int getrId() {
        return rId;
    }

    protected void setrId(int rId) {
        this.rId = rId;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getXPosDivider() {
        return xPosDivider;
    }

    public void setXPosDivider(float xPosDivider) {
        this.xPosDivider = xPosDivider;
    }

    public float getYPosDivider() {
        return yPosDivider;
    }

    public void setYPosDivider(float yPosDivider) {
        this.yPosDivider = yPosDivider;
    }

    public String getPath() {
        return path;
    }
}
