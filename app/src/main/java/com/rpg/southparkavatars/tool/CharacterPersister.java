package com.rpg.southparkavatars.tool;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rpg.southparkavatars.character.AbstractCharacter;
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
    public void save(Character character) {
        createPathIfNotExists();
        Character[] characters = readCharacters();

        if (filterDuplicates(character, characters)) {
            writeCharacter(characters);
        } else {
            writeCharacter(ArrayUtils.add(characters, character));
        }
    }

    private boolean filterDuplicates(Character character, Character[] characters) {
        if (characters != null) {
            for (Character item : characters) {
                if (item.getUuid().equals(character.getUuid())) {
                    item.copy(character);
                    return true;
                }
            }
        }
        return false;
    }

    private void createPathIfNotExists() {
        if (!path.exists()) {
            try {
                FileUtils.touch(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeCharacter(Character[] characters) {
        try (FileWriter fileOut = new FileWriter(path, false)) {
            mapper.writeValue(fileOut, characters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Character[] readCharacters() {
        try (FileReader fileIn = new FileReader(path)) {
            return mapper.readValue(fileIn, Character[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

    @Override
    public String serialize(Character object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
