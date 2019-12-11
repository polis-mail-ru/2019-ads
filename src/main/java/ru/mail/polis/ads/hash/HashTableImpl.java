package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    static class Node<Key, Value> implements Map.Entry<Key, Value> {
        final Key key;
        Value value;
        final int hash;
        Node<Key, Value> next;

        Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = null;
        }

        @Override
        public final Key getKey() {
            return key;
        }

        @Override
        public final Value getValue() {
            return value;
        }

        @Override
        public final Value setValue(Value value) {
            Value tempValue = this.value;
            this.value = value;
            return tempValue;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (getClass() != o.getClass())
                return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return key.equals(node.key) &&
                    value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size;
    private int capacity;
    private Node<Key, Value>[] table;
    private final double loadFactor;

    public HashTableImpl(int capacity, double loadFactor) {
        if (capacity <= 0 || Double.isNaN(loadFactor) || loadFactor < 0)
            throw new IllegalArgumentException("Incorrect input data");

        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Node[capacity];
    }

    public HashTableImpl(int loadFactor) {
        this(INITIAL_CAPACITY, loadFactor);
    }

    public HashTableImpl() {
        this(INITIAL_CAPACITY, LOAD_FACTOR);
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        Node<Key, Value> node = table[hash];

        while (node != null) {
            if (node.hash == hash && key.equals(node.key))
                return node.value;

            node = node.next;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key);
        Node<Key, Value> node = table[hash];

        if (node == null) {
            table[hash] = new Node<>(key, value, hash);
            size++;
        } else {
            while (node != null && node.key != null)
                node = node.next;

            if (node != null) {
                node.value = value;
                node.next = new Node<>(key, value, hash);
            }
        }

        if (size > capacity * loadFactor)
            rehash();
    }

    private void rehash() {
        capacity *= 2;
        size = 0;

        Node<Key, Value>[] oldTable = table;
        table = new Node[capacity];

        for (Node<Key, Value> node : oldTable) {
            Node<Key, Value> head = node;
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);

        Node<Key, Value> prevNode = null;
        Node<Key, Value> node = table[hash];

        if (node != null) {
            Value nodeValue = node.value;
            size--;

            while (node.next != null && node.key != key) {
                prevNode = node;
                node = node.next;
            }

            if (node.key == key) {
                if (prevNode == null)
                    table[hash] = node.next;
                else
                    prevNode.next = node.next;

                return nodeValue;
            }
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

    @Override
    public void clear() {
        Arrays.fill(table, null);
        capacity = INITIAL_CAPACITY;
        size = 0;
    }
}
