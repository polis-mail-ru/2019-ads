package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class OwnHashTable<Key, Value>
    implements HashTable<Key, Value> {

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

    private static final int MAX_CAPACITY = (int)Math.pow(2, 30);

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;

    private int capacity;

    private int count;

    private final float loadFactor;

    private Node<Key, Value>[] table;

    public OwnHashTable(int capacity, float loadFactor) {
        if (capacity < 0 || Float.isNaN(loadFactor) || loadFactor < 0 || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Incorrect data");
        }
        if (capacity == 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.loadFactor = loadFactor;
        this.count = 0;
        this.table = (Node<Key, Value>[])new Node[capacity];
    }

    public OwnHashTable(float loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor);
    }

    public OwnHashTable(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public OwnHashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = key.hashCode();
        int index = (hash & 0x7ffffff) % table.length;
        Node<Key, Value> node = table[index];
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = key.hashCode();
        int index = (hash & 0x7ffffff) % table.length;
        Node<Key, Value> node = table[index];
        Node<Key, Value> tempNode = null;
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                node.value = value;
                return;
            }
            tempNode = node;
            node = node.next;
        }
        count++;
        if (tempNode == null) {
            table[index] = new Node<>(hash, key, value, null);
            size++;
            checkOverLoad();
        } else {
            tempNode.next = new Node<>(hash, key, value, null);
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = key.hashCode();
        int index = (hash & 0x7ffffff) % table.length;
        Node<Key, Value> removedNode = remove(hash, key, index);
        return removedNode != null ? removedNode.value : null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        count = 0;
    }

    private void putWhenResize(Node<Key, Value> node) {
        int index = (node.hash & 0x7ffffff) % table.length;
        node.next = table[index];
        if (node.next == null) {
            size++;
        }
        table[index] = node;
    }

    private Node<Key, Value> remove(int hash, Key key, int index) {
        Node<Key, Value> node = table[index];
        if (node == null) {
            return null;
        }
        Node<Key, Value> tempNode = null;

        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                count--;
                if (tempNode != null) {
                    tempNode.next = node.next;
                    node.next = null;
                    return node;
                } else {
                    tempNode = node.next;
                    node.next = null;
                    table[index] = tempNode;

                    if (tempNode == null) {
                        size--;
                    }
                    return node;
                }
            }
            tempNode = node;
            node = node.next;
        }
        return null;
    }

    private void checkOverLoad() {
        if (capacity * loadFactor < size) {
            resize();
        }
    }

    private void resize() {
        if (capacity == MAX_CAPACITY) {
            return;
        }

        size = 0;
        if (capacity * 2 > MAX_CAPACITY) {
            capacity = MAX_CAPACITY;
        } else {
            capacity *= 2;
        }

        Node<Key, Value>[] oldTable = table;
        table = (Node<Key, Value>[])new Node[capacity];
        for (Node<Key, Value> node : oldTable) {
            while (node != null) {
                Node<Key, Value> tempNode = node.next;
                putWhenResize(node);
                node = tempNode;
            }
        }
    }
}
