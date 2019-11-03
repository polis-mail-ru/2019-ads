package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

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

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Override
    public Key floor(Key key) {
        if (key == null || size() == 0) return null;

        Node node = floor(root, key);

        return node == null ? null : node.key;
    }

    @Override
    public Key ceil(Key key) {
        if (key == null || size() == 0) return null;

        Node node = ceil(root, key);

        return node == null ? null : node.key;
    }

    @Override
    public int size() {
        return root == null ? 0 : size(root);
    }

    @Override
    public int height() {
        return height(root);
    }

    private Value get(Node root, Key key) {
        if (root == null) return null;

        int compare = key.compareTo(root.key);

        if (compare < 0) return get(root.left, key);
        if (compare > 0) return get(root.right, key);

        return root.value;
    }

    private Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, 1);

        if (key.compareTo(root.key) < 0) root.left = put(root.left, key, value);
        else if (key.compareTo(root.key) > 0) root.right = put(root.right, key, value);
        else root.value = value;

        fixHeight(root);
        root = balance(root);

        return root;
    }

    private void fixHeight(Node root) {
        root.height = 1 + Math.max(height(root.left), height(root.right));
    }

    private int factor(Node root) {
        return height(root.left) - height(root.right);
    }

    private Node balance(Node root) {
        if (factor(root) == 2) {
            if (factor(root.left) < 0) root.left = rotateLeft(root.left);

            return rotateRight(root);
        }

        if (factor(root) == -2) {
            if (factor(root.right) > 0) root.right = rotateRight(root.right);

            return rotateLeft(root);
        }

        return root;
    }

    private Node rotateRight(Node root) {
        Node left = root.left;
        root.left = left.right;
        left.right = root;

        fixHeight(root);
        fixHeight(left);

        return left;
    }

    private Node rotateLeft(Node root) {
        Node right = root.right;
        root.right = right.left;
        right.left = root;

        fixHeight(root);
        fixHeight(right);

        return right;
    }

    private Node remove(Node root, Key key) {
        if (root == null) return null;

        int compare = key.compareTo(root.key);

        if (compare < 0) root.left = remove(root.left, key);
        if (compare > 0) root.right = remove(root.right, key);
        if (compare == 0) root = innerDelete(root);

        return root;
    }

    private Node innerDelete(Node root) {
        if (root.right == null) return root.left;
        if (root.left == null) return root.right;

        Node t = root;
        root = min(t.right);
        root.right = deleteMin(t.right);
        root.left = t.left;

        return root;
    }

    private Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);

        return root;
    }

    private Node min(Node root) {
        Node min = root;

        while (min.left != null) min = min.left;

        return min;
    }

    private Node max(Node root) {
        Node max = root;

        while (max.right != null) max = max.right;

        return max;
    }

    private Node floor(Node root, Key key) {
        if (root == null) return null;

        int compare = key.compareTo(root.key);

        if (compare == 0) return root;
        if (compare < 0) return floor(root.left, key);

        Node node = floor(root.right, key);

        return node != null ? node : root;
    }

    private Node ceil(Node root, Key key) {
        if (root == null) return null;

        int compare = key.compareTo(root.key);

        if (compare == 0) return root;
        if (compare > 0) return ceil(root.right, key);

        Node node = ceil(root.left, key);

        return node != null ? node : root;
    }

    private int size(Node root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    private int height(Node root) {
        return root == null ? 0 : root.height;
    }
}
