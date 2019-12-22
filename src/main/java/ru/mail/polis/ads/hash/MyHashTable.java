package ru.mail.polis.ads.hash;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    private class Node {
        Key key;
        Value value;
        private int hashCode;

        public Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.hashCode = key.hashCode();
        }

        public boolean equalsKey(Key key) {
            if (this.key == key) return true;
            return key.hashCode() == hashCode;
        }
    }

    private int m;
    private LinkedList<Node>[] data;
    private int size;

    MyHashTable(int length) {
        m = length;
        size = 0;
        data = new LinkedList[m];
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        return getNode(key) != null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hashCode = keyHashCode(key);
        if (data[hashCode] == null) {
            data[hashCode] = new LinkedList<>();
            data[hashCode].add(new Node(key, value));
            size++;
        } else {
            LinkedList<Node> currentList = data[hashCode];
            Node currentNode = null;
            for (Node node : currentList) {
                if (node.equalsKey(key)) {
                    currentNode = node;
                    break;
                }
            }
            if (currentNode != null) {
                currentNode.value = value;
            } else {
                currentList.add(new Node(key, value));
                size++;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hashCode = keyHashCode(key);
        if (data[hashCode] == null) {
            return null;
        }
        LinkedList<Node> currentList = data[hashCode];
        for (Node node : currentList) {
            if (node.equalsKey(key)) {
                size--;
                return currentList.remove(node) ? node.value : null;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private Node getNode(Key key) {
        int hashCode = keyHashCode(key);
        if (data[hashCode] == null) {
            return null;
        }
        LinkedList<Node> currentList = data[hashCode];
        for (Node node : currentList) {
            if (node.equalsKey(key)) {
                return node;
            }
        }
        return null;
    }

    private int keyHashCode(Key key) {
        return Math.abs(key.hashCode() % m);
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
