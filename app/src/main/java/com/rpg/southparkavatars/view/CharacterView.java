package com.rpg.southparkavatars.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.DrawableItem;
import com.rpg.southparkavatars.tool.BitmapLoader;

import java.util.List;

public class CharacterView extends LinearLayout {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 800;

    private AssetManager assetManager;
    private Canvas canvas;
    private Bitmap bitmap;
    private Paint paint;

    private RectF rect;

    public CharacterView(Context context) {
        super(context);

        initParentBound();
        initCanvas();
    }

    public CharacterView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initParentBound();
        initCanvas();
    }

    public CharacterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initParentBound();
        initCanvas();
    }

    private void initParentBound() {
        Activity parent = (Activity) getContext();
        LayoutInflater inflater = parent.getLayoutInflater();

        inflater.inflate(R.layout.character_view_layout, this, true);
        assetManager = parent.getAssets();
        rect = new RectF();

        BitmapLoader.setAssetManager(assetManager);
    }

    private void initCanvas() {
        bitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));

        this.setBackground(new BitmapDrawable(getResources(), bitmap));
    }

    public void draw(Character character) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        drawItem(character.getSkin());
        drawItems(character.getHeadFeatures().getHeadFeatures());
        drawItems(character.getClothes().getClothes());

        this.invalidate();
    }

    private <T extends DrawableItem> void drawItems(List<T> items) {
        for (T feature : items) {
            float scale = feature.getScale();
            Bitmap bitmap = BitmapLoader.load(feature.getPath());

            float width = bitmap.getWidth();
            float height = bitmap.getHeight();

            float coordX = (canvas.getWidth() - scale * width) / feature.getXPosDivider();
            float coordY = (canvas.getHeight() - scale * height) / feature.getYPosDivider();

            rect.set(coordX, coordY, coordX + scale * width, coordY + scale * height);
            canvas.drawBitmap(bitmap, null, rect, paint);
        }
    }


    private <T extends DrawableItem> void drawItem(T item) {
        Bitmap bitmap = BitmapLoader.load(item.getPath());

        float scale = item.getScale();
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();

        float coordX = (canvas.getWidth() - scale * width) / item.getXPosDivider();
        float coordY = (canvas.getHeight() - scale * height) / item.getYPosDivider();

        rect.set(coordX, coordY, coordX + scale * width, coordY + scale * height);
        canvas.drawBitmap(bitmap, null, rect, paint);
    }

    public Image saveAsPNG() {
//        bitmap.compress(Bitmap.CompressFormat.PNG, 90, );

        return null;
    }
}
