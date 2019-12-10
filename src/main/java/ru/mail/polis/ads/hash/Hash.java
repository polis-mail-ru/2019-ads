package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Hash<Key, Value> implements HashTable<Key, Value> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private int sizeElem;
    private int sizeArr;
    private Node<Key, Value>[] values;

    Hash() {
        values = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    static class Node<Key,Value> {
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
    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        int index = hash % values.length;
        Node<Key, Value> temp = values[index];
        while (temp != null) {
            if (key.equals(temp.key)) {
                return temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        return get(key) != null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {

        Node<Key, Value> node = new Node<>(hash(key), key, value, null);
        int index = node.hash % values.length;

        if (values[index] != null) {
            Node<Key, Value> temp = values[index];
            while (temp != null) {
                if (temp.key.equals(node.key)) {
                    temp.value = node.value;
                    return;
                }
                if (temp.next == null) {
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }

        } else {
            values[index] = node;
            sizeArr++;
        }
        sizeElem++;
        addSize();
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        int index = hash % values.length;

        Node<Key, Value> prev = null;
        Node<Key, Value> current = values[index];

        while (current != null) {
            if (key.equals(current.key)) {
                if (prev == null) {
                    values[index] = current.next;
                    if (values[index] == null) sizeArr--;
                } else {
                    prev.next = current.next;
                }
                sizeElem--;
            }
            prev = current;
            current = current.next;
        }

        return current.value;
    }

    @Override
    public int size() {
        return sizeElem;
    }

    @Override
    public boolean isEmpty() {
        return sizeElem == 0;
    }

    private void addSize() {
        if (sizeArr > capacity * DEFAULT_LOAD_FACTOR) {
            int tempSize = sizeArr;
            sizeArr = 0;
            capacity = capacity * 2;
            Node<Key, Value>[] temp = values;
            values = new Node[capacity];
            for (int i = 0; i < tempSize; i++) {
                Node<Key, Value> tempNode = temp[i];
                while (tempNode != null) {
                    put(tempNode.key, tempNode.value);
                    tempNode = tempNode.next;
                }
            }
        }
    }
}
