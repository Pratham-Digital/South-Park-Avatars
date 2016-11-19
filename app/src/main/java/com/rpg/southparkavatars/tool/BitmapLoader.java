package com.rpg.southparkavatars.tool;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BitmapLoader {
    private AssetManager assetManager;
    private File internalStorage;

    public BitmapLoader(AssetManager assetManager, File internalStorage) {
        this.assetManager = assetManager;
        this.internalStorage = internalStorage;
    }

    public Bitmap load(String path) {
        try (InputStream inputStream = assetManager.open(path)) {
            return createBitmap(inputStream);
        } catch (IOException e) {
//            e.printStackTrace();
        }

        File filePath = new File(internalStorage.getPath() + File.separator + path);
        if (filePath.exists()) {
            try (InputStream inputStream = FileUtils.openInputStream(filePath)) {
                return createBitmap(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Bitmap createBitmap(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }
}
