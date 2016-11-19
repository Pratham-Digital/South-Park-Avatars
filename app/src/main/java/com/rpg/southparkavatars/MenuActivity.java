package com.rpg.southparkavatars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onPlayButtonClick(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void onCreateClothingButtonClick(View view) {
        Intent intent = new Intent(this, ClothingTypePickerActivity.class);
        startActivity(intent);
    }

    public void onLoadCharacterButtonClick(View view) {
        Intent intent = new Intent(this, LoadCharacterActivity.class);
        startActivity(intent);
    }
}
