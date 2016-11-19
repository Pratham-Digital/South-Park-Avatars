package com.rpg.southparkavatars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClothingTypePickerActivity extends AppCompatActivity {
    public final static String ClothingTypeExtra = "clothingType";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.clothing_picker_activity);
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);
        String resName = getResources().getResourceName(v.getId());

        resName = resName.substring(resName.indexOf('/') + 1, resName.length());
        intent.putExtra(ClothingTypeExtra, resName);
        startActivity(intent);
    }
}

