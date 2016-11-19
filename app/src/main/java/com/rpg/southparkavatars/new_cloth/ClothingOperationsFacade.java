package com.rpg.southparkavatars.new_cloth;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.rpg.southparkavatars.view.PaintingView;

public class ClothingOperationsFacade {
    private CustomClothing customClothing;
    private ClothingSaver clothingSaver;
    private PaintingView paintingView;

    public ClothingOperationsFacade(Context context, Activity activity, View view) {
        customClothing = new CustomClothing(context, activity, view);
        paintingView = new PaintingView(customClothing.getClothingType(), context, activity, view);
        clothingSaver = new ClothingSaver(customClothing, paintingView, context, activity, view);

        customClothing.attach(paintingView);
        customClothing.onStart();
    }

    public void persistClothing() {
        clothingSaver.saveClothing();
    }
}
