package com.rpg.southparkavatars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

import java.util.List;

public class PlayActivity extends AppCompatActivity implements AsyncTaskListener {
    private Character character = Character.getInstance();
    private AsyncTaskFactory asyncTaskFactory;
    private LinearLayout itemListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        asyncTaskFactory = new AsyncTaskFactory(this, getAssets());
        itemListLayout = (LinearLayout) findViewById(R.id.item_list_layout);

        asyncTaskFactory.createClothingLoadingTask(Hat.class)
                .execute();

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
            imageView.setImageBitmap(clothing.getBitmap());
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
            imageView.setImageBitmap(feature.getBitmap());
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
            imageView.setImageBitmap(skin.getBitmap());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    character.setSkin(skin);
                }
            });
            itemListLayout.addView(imageView);
        }
    }

    public void onItemCategoryButtonClick(View view) {
        switch (view.getId()) {
            case R.id.accessories_1_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Back.class)
                        .execute();
                break;
            case R.id.accessories_2_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Hand.class)
                        .execute();
                break;
            case R.id.beard_tab_button:
                asyncTaskFactory.createHeadFeaturesLoadingTask(Beard.class)
                        .execute();
                break;
            case R.id.eye_tab_button:
                asyncTaskFactory.createHeadFeaturesLoadingTask(Eyes.class)
                        .execute();
                break;
            case R.id.glasses_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Glasses.class)
                        .execute();
                break;
            case R.id.hair_tab_button:
                asyncTaskFactory.createHeadFeaturesLoadingTask(Hair.class)
                        .execute();
                break;
            case R.id.hat_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Hat.class)
                        .execute();
                break;
            case R.id.mouth_tab_button:
                asyncTaskFactory.createHeadFeaturesLoadingTask(Mouth.class)
                        .execute();
                break;
            case R.id.necklace_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Necklace.class)
                        .execute();
                break;
            case R.id.pants_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Pants.class)
                        .execute();
                break;
            case R.id.shirt_tab_button:
                asyncTaskFactory.createClothingLoadingTask(Shirt.class)
                        .execute();
                break;
            case R.id.skinColor_tab_button:
                asyncTaskFactory.createSkinLoadingTask()
                        .execute();
                break;
        }
    }
}
