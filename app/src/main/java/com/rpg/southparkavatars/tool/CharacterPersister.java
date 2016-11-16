package com.rpg.southparkavatars.tool;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.southparkavatars.character.Character;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterPersister implements ItemPersister<Character> {
    private File path;
    private ObjectMapper mapper = new ObjectMapper()
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    public CharacterPersister(File path) {
        this.path = path;
    }

    @Override
    public void save(Character item) {
        Character character = Character.getInstance();
        Character[] characters = null;

        try (FileReader fileIn = new FileReader(path)) {
            if (!path.exists()) {
                FileUtils.touch(path);
            }

            characters = mapper.readValue(fileIn, Character[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileOut = new FileWriter(path, false)) {
            mapper.writeValue(fileOut, ArrayUtils.add(characters, character));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Character[] loadAll() {
        Character[] characters;
        try (FileReader fileIn = new FileReader(path)) {
            characters = mapper.readValue(fileIn, Character[].class);
            return characters;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
