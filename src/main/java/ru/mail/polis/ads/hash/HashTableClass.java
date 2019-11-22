package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HashTableClass<Key, Value> implements HashTable<Key, Value> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private Node<Key, Value>[] table;
    private int size;
    private int usedBoxCount;

    public HashTableClass() {
        this.table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        int box = hash % table.length;
        Node<Key, Value> node = table[box];
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
        put(new Node<Key, Value>(key, value, hash(key)));
        resize();
    }

    private void put(Node<Key, Value> x) {
        int box = x.hash % table.length;
        Node<Key, Value> node = table[box];
        if (node == null) {
            table[box] = x;
            size++;
            usedBoxCount++;
        } else {
            Node<Key, Value> last = node;
            do {
                if (x.key.equals(node.key)) {
                    node.value = x.value;
                    return;
                }
                last = node;
                node = node.next;
            } while (node != null);
            last.next = x;
            size++;
        }
    }

    private void resize() {
        if (capacity * DEFAULT_LOAD_FACTOR < usedBoxCount) {
            capacity *= 2;
            usedBoxCount = 0;
            Node<Key, Value>[] oldTable = table;
            table = new Node[capacity];
            for (Node<Key, Value> node : oldTable) {
                while (node != null) {
                    Node<Key, Value> next = node.next;
                    put(node);
                    node = next;
                }
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        int box = hash % table.length;
        Node<Key, Value> node = table[box];
        Node<Key, Value> prev = null;

        while (node != null) {
            if (key.equals(node.key)) {
                size--;
                if (prev == null) {
                    table[box] = node.next;
                    if (table[box] == null) usedBoxCount--;
                    return node.value;
                } else {
                    prev.next = node.next;
                    return node.value;
                }
            }
            prev = node;
            node = node.next;
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

    static final int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    static class Node<Key, Value> {
        final Key key;
        final int hash;
        Value value;
        Node<Key, Value> next;

        Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Node) {
                return Objects.equals(this.key, ((Node) o).key) && Objects.equals(this.value, ((Node) o).value);
            }
            return false;
        }
    }
}



