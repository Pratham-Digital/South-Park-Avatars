package com.rpg.southparkavatars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;

import java.io.File;

public class LoadCharacterActivity extends AppCompatActivity {
    private LinearLayout characterNamesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_character);

        characterNamesLayout = (LinearLayout) findViewById(R.id.character_names_layout);

        loadPersistedCharacters();
    }

    private void loadPersistedCharacters() {
        File file = new File(getFilesDir() + File.separator + "characters.json");
        final ItemPersister<Character> persister = new CharacterPersister(file);
        Character[] characters = persister.loadAll();

        if (characters != null) {
            for (final Character character : characters) {
                TextView textView = new TextView(this);
                textView.setText(character.getName());
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoadCharacterActivity.this, PlayActivity.class);
                        String serialized = persister.serialize(character);

                        intent.putExtra("character", serialized);
                        startActivity(intent);
                        finish();
                    }
                });
                characterNamesLayout.addView(textView);
            }
        }
    }
}
