package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class CustomHashTable<Key, Value> implements HashTable<Key, Value> {

    private class Node {
        Key key;
        Value value;
        private int hashcode;

        public Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.hashcode = this.key.hashCode();
        }

        public boolean equalsKey(Key key) {
            if (this.key == key) return true;
            return key.hashCode() == hashcode;
        }

    }

    private int size;
    private LinkedList<Node>[] values;
    private int n;

    CustomHashTable(int length) {
        this.n = length;
        this.size = 0;
        this.values = new LinkedList[n];
    }

    private int getCurHashCode(Key key) {
        return Math.abs(key.hashCode() % this.n);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node getNode(Key key) {
        int hashcode = getCurHashCode(key);
        if (this.values[hashcode] == null) {
            return null;
        }
        LinkedList<Node> tmp = this.values[hashcode];
        for (Node node : tmp) {
            if (node.equalsKey(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hashcode = getCurHashCode(key);
        if (this.values[hashcode] == null) {
            this.values[hashcode] = new LinkedList<>();
            this.values[hashcode].add(new Node(key, value));
            this.size++;
        } else {
            LinkedList<Node> tmp = this.values[hashcode];
            Node tmpNode = null;
            for (Node node : tmp) {
                if (node.equalsKey(key)) {
                    tmpNode = node;
                    break;
                }
            }
            if (tmpNode != null) {
                tmpNode.value = value;
            } else {
                tmp.add(new Node(key, value));
                this.size++;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hashcode = getCurHashCode(key);
        if (this.values[hashcode] == null) {
            return null;
        }
        LinkedList<Node> tmp = this.values[hashcode];
        for (Node node : tmp) {
            if (node.equalsKey(key)) {
                this.size--;
                if (tmp.remove(node)) {
                    return node.value;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
