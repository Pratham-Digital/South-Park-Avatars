package com.rpg.southparkavatars;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.Skin;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.concrete.Back;
import com.rpg.southparkavatars.character.clothing.concrete.Glasses;
import com.rpg.southparkavatars.character.clothing.concrete.Hand;
import com.rpg.southparkavatars.character.clothing.concrete.Hat;
import com.rpg.southparkavatars.character.clothing.concrete.Necklace;
import com.rpg.southparkavatars.character.clothing.concrete.Pants;
import com.rpg.southparkavatars.character.clothing.concrete.Shirt;
import com.rpg.southparkavatars.character.head.HeadFeature;
import com.rpg.southparkavatars.character.head.concrete.Beard;
import com.rpg.southparkavatars.character.head.concrete.Eyes;
import com.rpg.southparkavatars.character.head.concrete.Hair;
import com.rpg.southparkavatars.character.head.concrete.Mouth;
import com.rpg.southparkavatars.observer.ClothingChangedObserver;
import com.rpg.southparkavatars.observer.HeadFeatureChangedObserver;
import com.rpg.southparkavatars.observer.SkinColorChangedObserver;
import com.rpg.southparkavatars.task.AsyncTaskFactory;
import com.rpg.southparkavatars.task.AsyncTaskListener;
import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PlayActivity extends AppCompatActivity implements AsyncTaskListener {
    private Character character = Character.getInstance();
    private AsyncTaskFactory asyncTaskFactory;
    private AssetManager assetManager;

    private LinearLayout itemListLayout;
    private LinearLayout tabButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        assetManager = getAssets();
        BitmapLoader.setAssetManager(assetManager);

        asyncTaskFactory = new AsyncTaskFactory(this, getAssets());

        itemListLayout = (LinearLayout) findViewById(R.id.item_list_layout);
        tabButtonLayout = (LinearLayout) findViewById(R.id.tab_button_layout);

        asyncTaskFactory.createClothingLoadingTask(Hat.class)
                .execute();

        initButtons();
        initCharacterObservers();
    }

    private void initCharacterObservers() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);

        character.attach(new ClothingChangedObserver(root));
        character.attach(new HeadFeatureChangedObserver(root));
        character.attach(new SkinColorChangedObserver(root));
    }

    @Override
    public void onClothesAsyncTaskFinished(List<Clothing> clothes) {
        itemListLayout.removeAllViews();
        fillItemListWithClothes(clothes);
    }

    @Override
    public void onHeadFeaturesAsyncTaskFinished(List<HeadFeature> headFeatures) {
        itemListLayout.removeAllViews();
        fillItemListWithHeadFeatures(headFeatures);
    }

    @Override
    public void onSkinAsyncTaskFinished(List<Skin> skins) {
        itemListLayout.removeAllViews();
        fillItemListWithSkinColors(skins);
    }

    private void fillItemListWithClothes(List<Clothing> clothes) {
        for (final Clothing clothing : clothes) {
            ImageView imageView = new ImageView(this);
            Bitmap bitmap = BitmapLoader.load(clothing.getPath());

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

    private void fillItemListWithHeadFeatures(List<HeadFeature> features) {
        for (final HeadFeature feature : features) {
            ImageView imageView = new ImageView(this);
            Bitmap bitmap = BitmapLoader.load(feature.getPath());

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
            Bitmap bitmap = BitmapLoader.load(skin.getPath().toString());

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
        List<Class<? extends Clothing>> clothingClasses = Arrays.asList(Back.class, Glasses.class, Hand.class,
                Hat.class, Necklace.class, Pants.class, Shirt.class);
        for (final Class<? extends Clothing> clothingClass : clothingClasses) {
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
        List<Class<? extends HeadFeature>> featureClasses = Arrays.asList(Beard.class, Eyes.class, Hair.class, Mouth.class);
        for (final Class<? extends HeadFeature> featureClass : featureClasses) {
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
        File file = new File(getFilesDir() + File.separator + "characters.json");
        ItemPersister<Character> persister = new CharacterPersister(file);
        persister.save(character);
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
}
