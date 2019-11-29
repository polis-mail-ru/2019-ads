package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Hash_Table<Key extends Comparable<Key>, Value>
        implements HashTable<Key, Value> {

    private final int INITIAL_CAPACITY = 16;
    private ArrayList<HashNode> buckets;
    private int size;

    private class HashNode{
        Key key;
        Value value;

        HashNode next;

        public HashNode(Key key, Value value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Hash_Table(){
        this.size = 0;
        this.buckets = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++)
            buckets.add(null);
    }

    private int getBucketIndex(Key key)
    {
        return Math.abs(key.hashCode() % buckets.size());
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        HashNode bucket = buckets.get(getBucketIndex(key));
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        HashNode entry = new HashNode(key, value, null);
        int bucket = getBucketIndex(key);
        HashNode existing = buckets.get(bucket);

        if (existing == null) {
            buckets.set(bucket, entry);
            size++;
        } else {
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }
            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets.get(bucketIndex);
        HashNode prev = null;

        while (head != null)
        {
            if (head.key.equals(key))
                break;

            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;

        size--;
        if (prev != null)
            prev.next = head.next;
        else
            buckets.set(bucketIndex, head.next);

        return head.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
