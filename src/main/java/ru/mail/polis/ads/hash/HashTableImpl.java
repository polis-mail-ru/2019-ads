package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static class KeyValuePair<Key1, Value1> {

        private final int hash;
        private final Key1 key;
        Value1 value;

        private KeyValuePair(int hash, Key1 key, Value1 value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int SCALE_FACTOR = 2;

    private List<KeyValuePair<Key, Value>>[] buckets;
    private int size;
    private int power;
    private int threshold;

    public HashTableImpl() {
        buckets = new List[INITIAL_CAPACITY];
        size = 0;
        power = 4;
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = key.hashCode();
        int index = getIndex(hash);
        if (buckets[index] == null || buckets[index].isEmpty()) {
            return null;
        }
        KeyValuePair<Key, Value> pair = null;
        for (KeyValuePair<Key, Value> p : buckets[index]) {
            if (hash == p.hash && p.key.equals(key)) {
                pair = p;
                break;
            }
        }
        if (pair == null) {
            return null;
        }
        return pair.value;
    }

    private int getIndex(int hash) {
        return hash & 0x7fffffff >>> (32 - power - 1);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (size > threshold) {
            resize();
        }
        int hash = key.hashCode();
        int index = getIndex(hash);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }
        List<KeyValuePair<Key, Value>> bucket = buckets[index];
        KeyValuePair<Key, Value> pair = null;
        for (KeyValuePair<Key, Value> p : bucket) {
            if (hash == p.hash && p.key.equals(key)) {
                pair = p;
                break;
            }
        }
        if (pair == null) {
            buckets[index].add(new KeyValuePair<>(hash, key, value));
            size++;
        } else {
            pair.value = value;
        }
    }

    private void resize() {
        int newCapacity = buckets.length * SCALE_FACTOR;
        power++;
        List<KeyValuePair<Key, Value>>[] buckets = new List[newCapacity];
        for (List<KeyValuePair<Key, Value>> bucket : this.buckets) {
            if (bucket == null) {
                continue;
            }
            for (KeyValuePair<Key, Value> pair : bucket) {
                int index = getIndex(pair.hash);
                if (buckets[index] == null) {
                    buckets[index] = new LinkedList<>();
                }
                buckets[index].add(pair);
            }
        }
        this.buckets = buckets;
        threshold = (int) (newCapacity * LOAD_FACTOR);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = key.hashCode();
        int index = getIndex(hash);
        if (buckets[index] == null || buckets[index].isEmpty()) {
            return null;
        }
        KeyValuePair<Key, Value> pair = null;
        Iterator<KeyValuePair<Key, Value>> iterator = buckets[index].iterator();
        while (iterator.hasNext()) {
            KeyValuePair<Key, Value> p = iterator.next();
            if (hash == p.key.hashCode() && p.key.equals(key)) {
                pair = p;
                iterator.remove();
                size--;
                break;
            }
        }
        if (pair == null) {
            return null;
        }
        return pair.value;
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