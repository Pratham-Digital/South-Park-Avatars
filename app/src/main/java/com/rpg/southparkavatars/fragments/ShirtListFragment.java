package com.rpg.southparkavatars.fragments;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rpg.southparkavatars.R;
import com.rpg.southparkavatars.image.ImageLoader;

public class ShirtListFragment extends Fragment {
    private AssetManager assetManager;
    private final String DIRECTORY_NAME = "clothing/shirt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.general_list_fragment, container, false);

        LinearLayout layout = (LinearLayout) inflated.findViewById(R.id.concrete_image_layout);
        ImageLoader.loadImages(inflated.getContext(), DIRECTORY_NAME, layout);

        return inflated;
    }
}
