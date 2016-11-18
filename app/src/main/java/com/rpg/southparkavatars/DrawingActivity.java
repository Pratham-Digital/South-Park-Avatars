package com.rpg.southparkavatars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rpg.southparkavatars.newcloth.PaintFacade;

import petrov.kristiyan.colorpicker.ColorPicker;

public class DrawingActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getLayoutInflater().inflate(R.layout.drawing_activity, null);
        setContentView(v);

        PaintFacade paintFacade = new PaintFacade(DrawingActivity.this, this, v);
        paintFacade.start();
    }
}
