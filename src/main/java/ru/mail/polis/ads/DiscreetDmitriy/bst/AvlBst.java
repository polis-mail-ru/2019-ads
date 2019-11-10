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
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0)
            return get(node.left, key);

        if (key.compareTo(node.key) > 0)
            return get(node.right, key);

        return node.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, 1);

        if (key.compareTo(node.key) < 0)
            node.left = put(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        fixHeight(node);

        node = balance(node);

        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;

        right.left = node;

        fixHeight(node);
        fixHeight(right);

        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;

        left.right = node;

        fixHeight(node);
        fixHeight(left);

        return left;
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0)
                node.left = rotateLeft(node.left);

            return rotateRight(node);
        }

        if (factor(node) == -2) {
            if (factor(node.right) > 0)
                node.right = rotateRight(node.right);

            return rotateLeft(node);
        }

        return node;
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    private Node remove(Node node, Key key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0)
            node.left = remove(node.left, key);
        if (key.compareTo(node.key) > 0)
            node.right = remove(node.right, key);
        if (key == node.key)
            node = innerRemove(node);

        return node;
    }

    private Node innerRemove(Node node) {
        if (node.right == null)
            return node.left;
        if (node.left == null)
            return node.right;

        Node temp = node;
        node = min(temp.right);
        node.right = removeMin(temp.right);
        node.left = temp.left;
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null)
            return node.right;

        node.left = removeMin(node.left);

        return node;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    private Node min(Node node) {
        Node min = node;

        while (min.left != null)
            min = min.left;

        return min;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    private Node max(Node node) {
        Node max = node;

        while (max.right != null)
            max = max.right;

        return max;
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
        return root == null ? 0 : size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
