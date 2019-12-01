package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseHashTable<Key, Value> implements HashTable<Key, Value> {

    private static int INITIAL_CAPACITY = 16;
    private Entry<Key, Value>[] buckets = new Entry[INITIAL_CAPACITY];
    private int size = 0;

    private static class Entry<Key, Value> {
        Key key;
        Value value;

        Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        for (int i = hash(key); buckets[i] != null && buckets[i].key != null; i = (i + 1) % size) {
            if (buckets[i].key.equals(key)) {
                return buckets[i].value;
            }
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int i;

        for (i = hash(key); buckets[i] != null && buckets[i].key != null; i = (i + 1) % size) {
            if (buckets[i].key.equals(key)) {
                buckets[i].value = value;
                return;
            }
        }

        buckets[i] = new Entry<>(key, value);
        size++;
    }

    private int hash(Key key) {
        return key.hashCode() % buckets.length;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        Entry<Key, Value> entry = buckets[hash];

        if (entry != null && entry.key == key) {
            for (int i = hash; i < buckets.length - 1; i++) {
                buckets[hash] = buckets[hash + 1];
            }
            buckets[buckets.length - 1] = null;
            size--;
            return entry.value;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
