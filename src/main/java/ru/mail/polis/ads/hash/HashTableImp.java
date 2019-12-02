package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HashTableImp<Key, Value> implements HashTable<Key, Value> {

    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node[] table;
    private int capacity = INITIAL_CAPACITY;
    private int size;
    private int usedCounter;

    class Node<Key, Value> {
        final Key key;
        final int hash;
        Value value;
        Node<Key, Value> next;

        Node(Key key, Value value, int hash){
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj instanceof Node) {
                return Objects.equals(this.key, ((Node) obj).key) && Objects.equals(this.value, ((Node) obj).value);
            }
            return false;
        }
    }
    HashTableImp() {
        this.table = new Node[INITIAL_CAPACITY];
    }

    private static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        int box = hash % table.length;
        Node node = table[box];
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                return (Value) node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node<>(key, value, hash(key)));
        resize();
    }

    private void put(Node<Key, Value> x) {
        int box = x.hash % table.length;
        Node node = table[box];
        if (node == null) {
            table[box] = x;
            size++;
            usedCounter++;
        } else {
            Node last;
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
        if (capacity * LOAD_FACTOR < usedCounter) {
            capacity *= 2;
            usedCounter = 0;
            Node[] oldTable = table;
            table = new Node[capacity];
            for (Node node : oldTable) {
                while (node != null) {
                    Node next = node.next;
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
        Node node = table[box];
        Node prev = null;

        while (node != null) {
            if (key.equals(node.key)) {
                size--;
                if (prev == null) {
                    table[box] = node.next;
                    if (table[box] == null) usedCounter--;
                    return (Value) node.value;
                } else {
                    prev.next = node.next;
                    return (Value) node.value;
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


}



