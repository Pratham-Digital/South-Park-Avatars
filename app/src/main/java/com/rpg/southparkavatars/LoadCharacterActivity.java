package com.rpg.southparkavatars;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.rpg.southparkavatars.character.AbstractCharacter;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;

import java.io.File;

public class LoadCharacterActivity extends AppCompatActivity {
    private LinearLayout characterNamesLayout;
    private ImageView previewImage;

    private BitmapLoader bitmapLoader;
    private AbstractCharacter currentCharacter;
    private ItemPersister<Character> persister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_character);

        characterNamesLayout = (LinearLayout) findViewById(R.id.character_names_layout);
        previewImage = (ImageView) findViewById(R.id.character_preview_image);

        bitmapLoader = new BitmapLoader(getAssets(), getFilesDir());

        File file = new File(getFilesDir() + File.separator + "characters.json");
        persister = new CharacterPersister(file);

        loadPersistedCharacters();

//        File file = new File(getFilesDir().getPath() + File.separator + "previews");

//        String[] files = file.list();
//        if (files != null) {
//            Log.i("NUMBER:", files.length + "");
//        }
    }

    private void loadPersistedCharacters() {
        AbstractCharacter[] characters = persister.loadAll();

        if (characters != null) {
            for (final AbstractCharacter character : characters) {
                Button characterButton = new Button(this);
                characterButton.setText(character.getName());
                characterButton.setTextColor(Color.WHITE);
                characterButton.setBackgroundResource(R.drawable.button_list_change);
                characterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File file = new File(getFilesDir().getPath() + File.separator
                                + "previews" + File.separator + character.getUuid() + ".png");

                        if (file.exists()) {
                            Bitmap bitmap = bitmapLoader.load(file.getPath());
                            previewImage.setImageBitmap(bitmap);
                        }

                        currentCharacter = character;
                    }
                });
                characterNamesLayout.addView(characterButton);
            }
        }
    }

    public void onEditButtonClicked(View view) {
        Intent intent = new Intent(LoadCharacterActivity.this, PlayActivity.class);
        String serialized = persister.serialize(currentCharacter.saveable());

        intent.putExtra("character", serialized);
        startActivity(intent);
        finish();
    }
}


