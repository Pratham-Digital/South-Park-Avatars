package com.rpg.southparkavatars.new_cloth;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public class PaintFacade {
    private CreatedClothing myCloth;

    public PaintFacade(Context context, Activity activity, View v) {
        this.myCloth = new CreatedClothing(context, activity, v);
    }

    public void start(){
        myCloth.onStart();
        myCloth.persistCloth();
    }
}
