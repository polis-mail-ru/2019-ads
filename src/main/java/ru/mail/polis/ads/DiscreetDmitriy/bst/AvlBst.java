package ru.mail.polis.ads.DiscreetDmitriy.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root = null;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void put(Key key, Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value remove(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key min() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value minValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key max() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Value maxValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key floor(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key ceil(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
