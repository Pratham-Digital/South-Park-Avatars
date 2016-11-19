package com.rpg.southparkavatars;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.AbstractClothing;
import com.rpg.southparkavatars.character.clothing.concrete.Back;
import com.rpg.southparkavatars.character.clothing.concrete.Glasses;
import com.rpg.southparkavatars.character.clothing.concrete.Hand;
import com.rpg.southparkavatars.character.clothing.concrete.Hat;
import com.rpg.southparkavatars.character.clothing.concrete.Necklace;
import com.rpg.southparkavatars.character.clothing.concrete.Pants;
import com.rpg.southparkavatars.character.clothing.concrete.Shirt;
import com.rpg.southparkavatars.character.head.AbstractHeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Beard;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Hair;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.observer.CharacterObserver;
import com.rpg.southparkavatars.task.AsyncTaskFactory;
import com.rpg.southparkavatars.task.AsyncTaskListener;
import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;
import com.rpg.southparkavatars.view.CharacterView;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements AsyncTaskListener, CharacterObserver {
    private Character character;
    private AsyncTaskFactory asyncTaskFactory;
    private BitmapLoader bitmapLoader;

    private CharacterView characterView;
    private LinearLayout itemListLayout;
    private LinearLayout tabButtonLayout;

    private EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        character = new Character();
        character.attach(this);

        bitmapLoader = new BitmapLoader(getAssets(), getFilesDir());
        asyncTaskFactory = new AsyncTaskFactory(this, getAssets(), getFilesDir());

        characterView = (CharacterView) findViewById(R.id.character_view);

        itemListLayout = (LinearLayout) findViewById(R.id.item_list_layout);
        tabButtonLayout = (LinearLayout) findViewById(R.id.tab_button_layout);

        nameEditText = (EditText) findViewById(R.id.name_edit_text);

        asyncTaskFactory.createClothingLoadingTask(Shirt.class)
                .execute();

        initButtons();
        loadPersistedCharacter();

        characterView.draw(character);
    }

    private void loadPersistedCharacter() {
        Intent starterIntent = getIntent();
        String serialized;
        if (starterIntent != null && (serialized = starterIntent.getStringExtra("character")) != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Character savedCharacter = mapper.readValue(serialized, Character.class);
                nameEditText.setText(savedCharacter.getName());
                character.copy(savedCharacter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClothesAsyncTaskFinished(List<AbstractClothing> clothes) {
        itemListLayout.removeAllViews();
        fillItemListWithClothes(clothes);
    }

    @Override
    public void onHeadFeaturesAsyncTaskFinished(List<AbstractHeadFeature> headFeatures) {
        itemListLayout.removeAllViews();
        fillItemListWithHeadFeatures(headFeatures);
    }

    @Override
    public void onSkinAsyncTaskFinished(List<Skin> skins) {
        itemListLayout.removeAllViews();
        fillItemListWithSkinColors(skins);
    }

    private void fillItemListWithClothes(List<AbstractClothing> clothes) {
        for (final AbstractClothing clothing : clothes) {
            ImageView imageView = new ImageView(this);
            Bitmap bitmap = bitmapLoader.load(clothing.getPath());

            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.addClothing(clothing);
                }
            });

            itemListLayout.addView(imageView);
        }
    }

    private void fillItemListWithHeadFeatures(List<AbstractHeadFeature> features) {
        for (final AbstractHeadFeature feature : features) {
            ImageView imageView = new ImageView(this);
            Bitmap bitmap = bitmapLoader.load(feature.getPath());

            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.addHeadFeature(feature);
                }
            });
            itemListLayout.addView(imageView);
        }
    }

    private void fillItemListWithSkinColors(List<Skin> skins) {
        for (final Skin skin : skins) {
            ImageView imageView = new ImageView(this);
            Bitmap bitmap = bitmapLoader.load(skin.getPath());

            imageView.setImageBitmap(bitmap);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.setSkin(skin);
                }
            });

            itemListLayout.addView(imageView);
        }
    }

    public void initButtons() {
        generateHeadFeatureButtons();
        generateClothingButtons();
    }

    private void generateClothingButtons() {
        List<Class<? extends AbstractClothing>> clothingClasses = Arrays.asList(Back.class, Glasses.class, Hand.class,
                Hat.class, Necklace.class, Pants.class, Shirt.class);
        for (final Class<? extends AbstractClothing> clothingClass : clothingClasses) {
            Button button = new Button(this);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asyncTaskFactory.createClothingLoadingTask(clothingClass)
                            .execute();
                }
            });
            button.setText(clothingClass.getSimpleName());
            tabButtonLayout.addView(button);
        }
    }

    private void generateHeadFeatureButtons() {
        List<Class<? extends AbstractHeadFeature>> featureClasses = Arrays.asList(Beard.class, Eyes.class, Hair.class, Mouth.class);
        for (final Class<? extends AbstractHeadFeature> featureClass : featureClasses) {
            Button button = new Button(this);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    asyncTaskFactory.createHeadFeaturesLoadingTask(featureClass)
                            .execute();
                }
            });
            button.setText(featureClass.getSimpleName());
            tabButtonLayout.addView(button);
        }
    }

    public void onSkinButtonClick(View view) {
        asyncTaskFactory.createSkinLoadingTask()
                .execute();
    }

    public void onSaveButtonClick(View view) {
        String name = String.valueOf(nameEditText.getText());
        if (StringUtils.isEmpty(name)) {
            Snackbar.make(findViewById(R.id.activity_play),
                    "ERROR: Name cannot be empty!", Snackbar.LENGTH_SHORT)
                    .show();
            return;
        }
        File file = new File(getFilesDir() + File.separator + "characters.json");
        ItemPersister<Character> persister = new CharacterPersister(file);

        character.setName(name);
        persister.save(character);
        characterView.saveAsPNG(character);
//        Character[] characters = persister.loadAll();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void update() {
        characterView.draw(character);
    }
}
