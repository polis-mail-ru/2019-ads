package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {
    //my
    private Node root;
    private int size;
    //end my

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    //my
    AvlBst() {
        root = null;
        size = 0;
    }

    public Value get(Node node, Key key) {
        if (node == null)
            return null;
        if(key.compareTo(node.key) < 0)
            get(node.left, key);
        else if (key.compareTo(node.key) > 0)
            get(node.right, key);

        return node.value;
    }
    //end my
    @Override
    public Value get(Key key) {
        return get(root, key);
    }
    //my
    Node put(Key key, Value value, Node node) {
        if (node == null) {
            ++size;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = put(key, value, node.left);
        else if (key.compareTo(node.key) > 0)
            node.right = put(key, value, node.right);
        else
            node.value = value;

        return node;
    }
    //endmy

    @Override
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    @Override
    public void remove(Key key) {
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
        return size;
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
