package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Hash_Table<Key, Value>
        implements HashTable<Key, Value> {

    private int INITIAL_CAPACITY = 16;
    private HashNode<Key, Value>[] buckets = new HashNode[INITIAL_CAPACITY];
    private int size = 0;

    private static class HashNode<K, V>{
        K key;
        V value;

        HashNode<K, V> next;

        public HashNode(K key, V value, HashNode<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Hash_Table(){ }

    private int getBucketIndex(Key key)
    {
        return Math.abs(key.hashCode() % buckets.length);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        HashNode<Key, Value> bucket = buckets[getBucketIndex(key)];
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
        int bucket = getBucketIndex(key);
        HashNode<Key, Value> existing = buckets[bucket];
        HashNode<Key, Value> entry = new HashNode<>(key, value, null);
        if (existing == null) {
            buckets[bucket] = entry;
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
        if ((1.0 * size) / INITIAL_CAPACITY >= 0.7)
        {
            HashNode<Key, Value>[] temp = buckets;
            INITIAL_CAPACITY *= 2;
            size = 0;
            buckets = new HashNode[INITIAL_CAPACITY];

            for (HashNode<Key, Value> headNode : temp)
            {
                while (headNode != null)
                {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int bucketIndex = getBucketIndex(key);
        HashNode<Key, Value> head = buckets[bucketIndex];
        HashNode<Key, Value> prev = null;
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
            buckets[bucketIndex] = head.next;
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
