package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

public class HashTableBase<Key, Value> implements HashTable<Key, Value> {

    static class Node<Key, Value> implements Map.Entry<Key, Value> {
        final int hash;
        final Key key;
        Value value;
        Node<Key, Value> next;

        Node(int hash, Key key, Value value, Node<Key, Value> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final Key getKey() {
            return key;
        }

        public final Value getValue() {
            return value;
        }

        public final String toString() {
            return key + " : " + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final Value setValue(Value value) {
            Value tempValue = this.value;
            this.value = value;
            return tempValue;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static final int MAX_INITIAL_CAPACITY = (int)Math.pow(2, 30);

    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int size;

    private int capacity;

    private final double loadFactor;

    @NotNull
    private Node<Key, Value>[] table;

    public HashTableBase(int capacity, double loadFactor) {
        if (capacity < 0 || loadFactor > 1 || loadFactor < 0.25 || capacity > MAX_INITIAL_CAPACITY) {
            throw new IllegalArgumentException("Incorrect data");
        }
        if (capacity == 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.loadFactor = loadFactor;
        this.table = (Node<Key, Value>[])new Node[capacity];
    }

    public HashTableBase(double loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor);
    }

    public HashTableBase(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableBase() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {

    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
