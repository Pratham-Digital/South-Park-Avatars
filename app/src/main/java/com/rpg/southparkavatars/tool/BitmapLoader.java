package com.rpg.southparkavatars.tool;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

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
        Bitmap bitmap = loadFromAssets(path);
        if (bitmap != null) return bitmap;

        bitmap = loadFromInternalStorage(internalStorage.getPath() + File.separator + path);
        if (bitmap != null) return bitmap;

        bitmap = loadFromPreviews(path);
        if (bitmap != null) return bitmap;

        return null;
    }

    public Bitmap loadFromBackgrounds(String fileName) {
        String filePath = "background" + File.separator + fileName;
        try (InputStream inputStream = assetManager.open(filePath)) {
            return createBitmap(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private Bitmap loadFromPreviews(String path) {
        File previewFile = new File(path);
        if (previewFile.exists()) {
            try (InputStream inputStream = FileUtils.openInputStream(previewFile)) {
                return createBitmap(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Nullable
    private Bitmap loadFromAssets(String path) {
        try (InputStream inputStream = assetManager.open(path)) {
            return createBitmap(inputStream);
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    private Bitmap loadFromInternalStorage(String pathname) {
        File filePath = new File(pathname);
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
