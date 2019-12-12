package ru.mail.polis.ads.hash;

import javax.annotation.Nullable;

/**
 * Associative array based on hashing.
 */

public interface HashTable<Key, Value> {

    @Nullable
    Value get(Key key);
    
    default boolean containsKey(Key key) {
        return get(key) != null;
    }

    void put(Key key, Value value);

    @Nullable Value remove(Key key);

    int size();
    
    boolean isEmpty();
}