package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class BstAvlImpl<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {
    }

    @Override
    public void put(Key key, Value value) {
    }

    @Override
    public Value remove(Key key) {
    }

    @Override
    public Key min() {
    }

    @Override
    public Value minValue() {
    }

    @Override
    public Key max() {
    }

    @Override
    public Value maxValue() {
    }

    @Override
    public Key floor(Key key) {
    }

    @Override
    public Key ceil(Key key) {
    }

    @Override
    public int size() {
    }

    @Override
    public int height() {
    }
}
