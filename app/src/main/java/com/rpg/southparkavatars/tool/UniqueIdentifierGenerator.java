package com.rpg.southparkavatars.tool;

import java.util.UUID;

public class UniqueIdentifierGenerator {
    private static final UniqueIdentifierGenerator INSTANCE = new UniqueIdentifierGenerator();

    private UniqueIdentifierGenerator() {

    }

    public static UniqueIdentifierGenerator getInstance() {
        return INSTANCE;
    }

    public synchronized String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
