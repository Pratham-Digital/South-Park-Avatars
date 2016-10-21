package com.rpg.southparkavatars.image;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.clothing.Clothing;
import com.rpg.southparkavatars.character.clothing.concrete.BackAccessory;
import com.rpg.southparkavatars.character.clothing.concrete.HandAccessory;
import com.rpg.southparkavatars.character.head.HeadFeature;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class ImageLoader {
    private static AssetManager assetManager;

    static public void loadImages(Context context, final String path, ViewGroup viewGroup) {
        assetManager = context.getAssets();
        List<String> shirtNameList = new ArrayList<>();
        final Character character = Character.getInstance();

        try {
            shirtNameList = Arrays.asList(assetManager.list(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String shirtName : shirtNameList) {
            try (InputStream iStream = assetManager.open(path + "/" + shirtName)) {
                final ImageView image = new ImageView(context);
                final Bitmap bitmap = BitmapFactory.decodeStream(iStream);

                image.setImageBitmap(bitmap);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (path.startsWith("clothing")) {
                            String name = path.split("/")[1];
                            Clothing clothing;

                            switch (name) {
                                case "accessory_1":
                                    clothing = new BackAccessory(0, bitmap);
                                    break;
                                case "accessory_2":
                                    clothing = new HandAccessory(0, bitmap);
                                    break;
                                default:
                                    name = "com.rpg.southparkavatars.character.clothing.concrete." + StringUtils.capitalize(name);

                                    clothing = (Clothing) createInstance(name, 0, bitmap);
                                    break;
                            }

                            character.addClothing(clothing);
                        } else if (path.startsWith("head")) {
                            String name = path.split("/")[1];
                            name = "com.rpg.southparkavatars.character.head.concrete." + StringUtils.capitalize(name);

                            HeadFeature headFeature = (HeadFeature) createInstance(name, bitmap);
                            if (headFeature != null) {
                                character.addHeadFeature(headFeature);
                            }
                        } else if (path.equals("skin_color")) {
                            character.setSkinColorBitmap(bitmap);
                        }
                    }
                });

                viewGroup.addView(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object createInstance(String name, Object... ctorParams) {
        Object object = null;

        try {
            Class<?> dynClass = Class.forName(name);
            Class[] ctorParamsType = new Class[ctorParams.length];

            for (int i = 0; i < ctorParams.length; i++) {
                if (ctorParams[i].getClass().getSimpleName().equals(Integer.class.getSimpleName())) {
                    ctorParamsType[i] = int.class;
                } else {
                    ctorParamsType[i] = ctorParams[i].getClass();
                }
            }

            Constructor<?> ctor = dynClass.getConstructor(ctorParamsType);
            object = ctor.newInstance(ctorParams);
        } catch (ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return object;
    }
}
