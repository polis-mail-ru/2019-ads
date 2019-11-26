package ru.mail.polis.ads.bst;


import javax.annotation.Nullable;

/**
 * Binary search tree with ordered operations support.
 */

public interface Bst<Key extends Comparable<Key>, Value> {
    @Nullable
    Value get(Key key);
    
    default boolean containsKey(Key key) {
        return get(key) != null;
    }
    
    void put(Key key, Value value);

    @Nullable Value remove(Key key);

    @Nullable Key min();

    @Nullable Value minValue();

    @Nullable Key max();

    @Nullable Value maxValue();

    @Nullable Key floor(Key key);

    @Nullable Key ceil(Key key);

    int size();

    int height();
    
    default boolean isEmpty() {
        return size() == 0;
    }
}
