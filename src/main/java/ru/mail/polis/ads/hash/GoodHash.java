package ru.mail.polis.ads.hash;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GoodHash <K, V> implements HashTable<K, V> {

    private static final int CAPACITY = 16;

    private HashNode<K, V>[] hashTable;

    private int size;

    static class HashNode<K, V> {
        final int hash;

        final K key;
        V value;

        HashNode<K, V> next;

        HashNode(K key, V value, int hash) {
            this.key = key;
            this.value = value;

            this.hash = hash;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (obj instanceof HashNode) {
                return Objects.equals(this.key, ((HashNode) obj).key) &&
                        Objects.equals(this.value, ((HashNode) obj).value);
            }

            return false;
        }
    }

    public GoodHash() {
        this.hashTable = new HashNode[CAPACITY];
    }

    // имплементация хэш-функции для нахождения индекса по ключу
    static int hash(Object key) {
        int h;

        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    @Nullable
    @Override
    public V get(K key) {
        // голова цепочки по ключу
        int bucketIndex = hash(key);
        int box = bucketIndex % hashTable.length;

        HashNode<K, V> head = hashTable[box];

        // ключ в цепочке
        while (head != null) {
            if (head.hash == bucketIndex && key.equals(head.key)) {
                return head.value;
            }

            head = head.next;
        }

        // ключ не найден
        return null;
    }

    @Override
    public void put(K key, V value) {
        //
    }

    @Nullable
    @Override
    public V remove(K key) {
        //
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







    // https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/

}
