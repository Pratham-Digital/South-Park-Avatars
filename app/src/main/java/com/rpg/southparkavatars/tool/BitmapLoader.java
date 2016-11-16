package com.rpg.southparkavatars.tool;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public abstract class BitmapLoader {
    private static AssetManager assetManager;

    public static void setAssetManager(AssetManager assetManager) {
        BitmapLoader.assetManager = assetManager;
    }

    public static Bitmap load(String path) {
        if (assetManager != null) {
            Bitmap bitmap;
            try (InputStream inputStream = assetManager.open(path)) {
                bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
