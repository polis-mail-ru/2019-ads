package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    private class Node {
        private Key key;
        private Value value;
        private int hashCode;

        public Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.hashCode = key.hashCode();
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return key.hashCode() % m;
        }

        public boolean equalsKey(Object o) {
            if (this == o) return true;
            if (o == null || key.getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return key.hashCode() == hashCode;
        }
    }

    private static int m;
    private LinkedList<Node>[] data;

    MyHashTable(int length) {
        m = length;
        data = new LinkedList[m];
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hashCode = key.hashCode() % m;
        if (data[hashCode] == null) {
            return null;
        }
        LinkedList<Node> currentList = data[hashCode];
        for (Node node : currentList) {
            if (node.equalsKey(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hashCode = key.hashCode() % m;
        if (data[hashCode] == null) {
            return false;
        }
        LinkedList<Node> currentList = data[hashCode];
        for (Node node : currentList) {
            if (node.equalsKey(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hashCode = key.hashCode() % m;
        if (data[hashCode] == null) {
            data[hashCode] = new LinkedList<>();
            data[hashCode].add(new Node(key, value));
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
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hashCode = key.hashCode() % m;
        if (data[hashCode] == null) {
            return null;
        }
        LinkedList<Node> currentList = data[hashCode];
        for (int i = 0; i < currentList.size(); i++) {
            if (currentList.get(i).equalsKey(key)) {
                return currentList.remove(i).value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedList<Node> list : data) {
            if (list != null) {
                size += list.size();
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
