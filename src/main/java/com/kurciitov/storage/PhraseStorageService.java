package com.kurciitov.storage;

import java.util.*;
import java.util.stream.Collectors;

public class PhraseStorageService {

    private int phraseId;

    private final Map<Integer, String> store = new HashMap<>();

    public void create(String phrase) {
        store.put(phraseId++, phrase);
    }

    public String getRandomPhrase() {
        if (store.size() == 0) {
            return "I help you";
        } else {
            List<String> list = new ArrayList(store.values());
            Random randomizer = new Random();
            return list.get(randomizer.nextInt(list.size()));
        }
    }
}
