package com.rpg.southparkavatars.newcloth;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.rpg.southparkavatars.DrawingActivity;

/**
 * Created by griga on 2016-11-17.
 */

public class PaintFacade {
    private CreateClothType myCloth;
    private Context context;
    private Activity activity;
    private View v;

    public PaintFacade(Context context, Activity activity, View v) {
        this.context = context;
        this.activity = activity;
        this.v = v;
        this.myCloth = new CreateClothType(context, activity, v);
    }

    public void start(){
        myCloth.onStart();
        myCloth.persistCloth();
    }
}
