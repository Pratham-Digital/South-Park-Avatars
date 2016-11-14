package com.rpg.southparkavatars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rpg.southparkavatars.task.LoadClothesAsyncTask;

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
        Intent intent = new Intent(this, ClothTypePickerActivity.class);
        startActivity(intent);
    }
}
