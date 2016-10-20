package com.rpg.southparkavatars;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.common.collect.ImmutableMap;
import com.rpg.southparkavatars.character.Character;
import com.rpg.southparkavatars.character.CharacterClothingChanged;
import com.rpg.southparkavatars.character.CharacterHeadFeatureChanged;
import com.rpg.southparkavatars.character.CharacterSkinColorChanged;
import com.rpg.southparkavatars.fragments.AccessoryOneListFragment;
import com.rpg.southparkavatars.fragments.AccessoryTwoListFragment;
import com.rpg.southparkavatars.fragments.BackgroundListFragment;
import com.rpg.southparkavatars.fragments.BeardListFragment;
import com.rpg.southparkavatars.fragments.EyeListFragment;
import com.rpg.southparkavatars.fragments.GlassesListFragment;
import com.rpg.southparkavatars.fragments.HairListFragment;
import com.rpg.southparkavatars.fragments.HatListFragment;
import com.rpg.southparkavatars.fragments.MouthListFragment;
import com.rpg.southparkavatars.fragments.NecklaceListFragment;
import com.rpg.southparkavatars.fragments.PantsListFragment;
import com.rpg.southparkavatars.fragments.ShirtListFragment;
import com.rpg.southparkavatars.fragments.SkinColorListFragment;

import java.util.Map;

public class PlayActivity extends AppCompatActivity {
    private Character character = Character.getInstance();

    private FragmentManager fragmentManager = getFragmentManager();
    private Fragment currentFragment;
    private Map<String, Fragment> fragmentMap = ImmutableMap.<String, Fragment>builder()
            .put("background", new BackgroundListFragment())
            .put("skin", new SkinColorListFragment())
            .put("shirt", new ShirtListFragment())
            .put("beard", new BeardListFragment())
            .put("eyes", new EyeListFragment())
            .put("glasses", new GlassesListFragment())
            .put("hair", new HairListFragment())
            .put("hat", new HatListFragment())
            .put("mouth", new MouthListFragment())
            .put("necklace", new NecklaceListFragment())
            .put("pants", new PantsListFragment())
            .put("accessory_1", new AccessoryOneListFragment())
            .put("accessory_2", new AccessoryTwoListFragment())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        initCharacterDelegate();
        initFragments();
    }

    private void initCharacterDelegate() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);

        character.setSkinColorDelegate(new CharacterSkinColorChanged(root));
        character.setClothingDelegate(new CharacterClothingChanged(root));
        character.setHeadFeatureDelegate(new CharacterHeadFeatureChanged(root));
    }

    private void initFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Map.Entry<String, Fragment> entry : fragmentMap.entrySet()) {
            Fragment value = entry.getValue();
            fragmentTransaction
                    .add(R.id.play_fragment_container, value)
                    .hide(value);
        }

        fragmentTransaction.show(fragmentMap.get("skin"));
        fragmentTransaction.commit();

        currentFragment = fragmentMap.get("skin");
    }

    public void onButtonClick(View view) {
        Button button = (Button) view;
        String name = button.getText().toString().toLowerCase();
        name = name.split("_")[0];
        name = name.replace(' ', '_');

        Fragment newFragment = fragmentMap.get(name);

        if (currentFragment != newFragment) {
            fragmentManager.beginTransaction()
                    .show(newFragment)
                    .hide(currentFragment)
                    .commit();

            currentFragment = newFragment;
        }
    }
}
