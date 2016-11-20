package com.rpg.southparkavatars;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidplot.pie.PieChart;
import com.androidplot.pie.PieRenderer;
import com.androidplot.pie.Segment;
import com.androidplot.pie.SegmentFormatter;
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
import com.rpg.southparkavatars.task.AsyncTaskFactory;
import com.rpg.southparkavatars.task.AsyncTaskListener;
import com.rpg.southparkavatars.tool.BitmapLoader;
import com.rpg.southparkavatars.tool.CharacterPersister;
import com.rpg.southparkavatars.tool.ItemPersister;
import com.rpg.southparkavatars.visitor.ClothingCoolnessVisitor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StatisticActivity extends AppCompatActivity implements AsyncTaskListener {
    private AssetManager assetManager;
    private AsyncTaskFactory asyncTaskFactory;
    private TextView clothText;
    private PieChart pie;
    private Segment hats, hand, back, shirts, pants, necklaces, glasses;
    private TextView displayCoolness;
    private RelativeLayout layout;
    private BitmapLoader bitmapLoader;
    private LinearLayout clothLayout;
    private int step = 0;
    private List<Integer> countClothTypes = new ArrayList<Integer>();
    private List<Integer> getMaxClothCoolness = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        layout = (RelativeLayout) findViewById(R.id.activity_statistic);
        TextView numOfCharacters = (TextView) findViewById(R.id.num_of_characters);
        numOfCharacters.setText("Number of characters that have been created:  " + Integer.toString(countAllCharacters()));
        numOfCharacters.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        displayCoolness = (TextView) findViewById(R.id.display_avg_coolness);
        displayCoolness.setText("Average coolness value of characters: " + Integer.toString(getAverageCharacterCoolness()));

        pie = (PieChart) findViewById(R.id.chart);
        createPieChart();

        bitmapLoader = new BitmapLoader(getAssets(), getFilesDir());

        asyncTaskFactory = new AsyncTaskFactory(this, getAssets(), getFilesDir());
        createClothingLoadingTasks();

        clothLayout = (LinearLayout) findViewById(R.id.list_coolest_clothes);
    }

    public Character[] loadCharacters() {
        File file = new File(getFilesDir() + File.separator + "characters.json");
        final ItemPersister<Character> persister = new CharacterPersister(file);
        Character[] characters = persister.loadAll();
        return characters;
    }

    private void createClothingLoadingTasks() {
        List<Class<? extends AbstractClothing>> classList = Arrays.asList(Hat.class, Hand.class, Back.class,
                Shirt.class, Pants.class, Necklace.class, Glasses.class);

        for (Class<? extends AbstractClothing> clothingClass : classList) {
            asyncTaskFactory.createClothingLoadingTask(clothingClass)
                    .execute();
        }
    }

    public int countAllCharacters() {
        Character[] characters = loadCharacters();
        return characters.length;
    }

    public int getAverageCharacterCoolness() {
        Character[] characters = loadCharacters();
        int coolness = 0;

        for (Character character : characters) {
            ClothingCoolnessVisitor visitor = new ClothingCoolnessVisitor();

            character.accept(visitor);
            coolness += visitor.getOverallCoolness();
        }
        if(countAllCharacters()!= 0)
            return (coolness / countAllCharacters());
        return 0;
    }


    @Override
    public void onClothesAsyncTaskFinished(List<AbstractClothing> clothes) {
        int index = 0;
        int coolness = 0;
        int maxIndex = 0;

        if (countClothTypes.size() <= 7) {
            countClothTypes.add(clothes.size());
        } else {
            countClothTypes = new ArrayList<Integer>();
        }

        updatePieChart();

        for (AbstractClothing cloth : clothes) {
            if (cloth.getCoolness() > coolness) {
                coolness = cloth.getCoolness();
                maxIndex = index;
            }
            index++;
        }
        getMaxClothCoolness.add(clothes.get(maxIndex).getCoolness());

        ImageView imageView = new ImageView(this);
        Bitmap bitmap = bitmapLoader.load(clothes.get(maxIndex).getPath());

        imageView.setImageBitmap(bitmap);
        imageView.setScaleX(1.1f);
        imageView.setScaleY(1.1f);

        clothLayout.addView(imageView);

        TextView getMaxCoolness = new TextView(this);
        getMaxCoolness.setText(getMaxClothCoolness.get(step).toString());
        getMaxCoolness.setTextColor(Color.WHITE);
        getMaxCoolness.getLayoutParams();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 45, 0);

        getMaxCoolness.setLayoutParams(layoutParams);
        getMaxCoolness.setPadding(5, 5, 5, 5);
        getMaxCoolness.setBackgroundResource(R.drawable.coolness_background);
        getMaxCoolness.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        step++;
        clothLayout.addView(getMaxCoolness);
    }


    @Override
    public void onHeadFeaturesAsyncTaskFinished(List<AbstractHeadFeature> headFeatures) {

    }

    @Override
    public void onSkinAsyncTaskFinished(List<Skin> skins) {

    }

    @Override
    public void onBackgroundsLoaded(List<BitmapDrawable> bitmaps) {

    }

    public void createPieChart() {
        hats = new Segment("Hats: ", 1);
        hand = new Segment("Hands: ", 1);
        back = new Segment("Back: ", 1);
        shirts = new Segment("Shirts: ", 1);
        pants = new Segment("Pants: ", 1);
        necklaces = new Segment("Necklaces: ", 1);
        glasses = new Segment("Glasses: ", 1);

        pie.addSegment(hats, new SegmentFormatter(Color.RED));
        pie.addSegment(hand, new SegmentFormatter(Color.BLUE));
        pie.addSegment(back, new SegmentFormatter(Color.parseColor("#999900")));
        pie.addSegment(shirts, new SegmentFormatter(Color.parseColor("#0f8700")));
        pie.addSegment(pants, new SegmentFormatter(Color.parseColor("#045c7c")));
        pie.addSegment(necklaces, new SegmentFormatter(Color.parseColor("#8e5a05")));

        pie.getRenderer(PieRenderer.class).setDonutSize(10 / 100f, PieRenderer.DonutMode.PERCENT);
        pie.getBackgroundPaint().setColor(Color.TRANSPARENT);
        pie.getBorderPaint().setColor(Color.TRANSPARENT);

        pie.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void updatePieChart() {
        try {
            hats.setValue(countClothTypes.get(0));
            hats.setTitle("Hats: \n" + countClothTypes.get(0));

            hand.setValue(countClothTypes.get(1));
            hand.setTitle("Hands: \n" + countClothTypes.get(1));

            back.setValue(countClothTypes.get(2));
            back.setTitle("Back: \n" + countClothTypes.get(2));

            shirts.setValue(countClothTypes.get(3));
            shirts.setTitle("Shirts: \n" + countClothTypes.get(3));

            pants.setValue(countClothTypes.get(4));
            pants.setTitle("Pants: \n" + countClothTypes.get(4));

            necklaces.setValue(countClothTypes.get(5));
            necklaces.setTitle("Necklaces: \n" + countClothTypes.get(5));

            glasses.setValue(countClothTypes.get(6));
            glasses.setTitle("Glasses: \n" + countClothTypes.get(6));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pie.redraw();
    }
}
