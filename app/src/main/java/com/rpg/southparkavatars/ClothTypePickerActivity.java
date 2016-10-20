package com.rpg.southparkavatars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClothTypePickerActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cloth_picker_activity);
    }

    public void ButtonOnClick(View v) {
        Intent intent = new Intent(getApplicationContext(), DrawingActivity.class);

        switch (v.getId()) {
            case R.id.createShirt:
                intent.putExtra("cloth-type", "Shirt");
                break;

            case R.id.createPants:
                intent.putExtra("cloth-type", "Pants");
                break;

            case R.id.createHat:
                intent.putExtra("cloth-type", "Hat");
                break;
        }

        startActivity(intent);
    }
}

