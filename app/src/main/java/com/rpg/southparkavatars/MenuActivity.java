package com.rpg.southparkavatars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;

import java.io.File;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        onBackPressed();
    }

    @Override
    public void onBackPressed(){
        return;
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
        File file = new File(getFilesDir() + File.separator + "characters.json");
        final ItemPersister<Character> persister = new CharacterPersister(file);
        if(persister.loadAll() == null)
            Snackbar.make(findViewById(R.id.activity_menu),"Error: There is no saved character!",Snackbar.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, LoadCharacterActivity.class);
            startActivity(intent);
        }
    }

    public void onStatisticsButtonClick(View view){
        File file = new File(getFilesDir() + File.separator + "characters.json");
        final ItemPersister<Character> persister = new CharacterPersister(file);
        if(persister.loadAll() == null)
            Snackbar.make(findViewById(R.id.activity_menu),"Error: There is nothing to get statistics about!",Snackbar.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(this, StatisticActivity.class);
            startActivity(intent);
        }
    }

}
