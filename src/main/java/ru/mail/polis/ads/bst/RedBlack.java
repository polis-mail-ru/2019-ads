package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class RedBlack<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;

    public class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node(Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) return null;

        Value removed = get(key);
        root = remove(root, key);

        return removed;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (size() == 0) return null;

        Node node = floor(root, key);

        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (size() == 0) return null;

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

    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1, RED);

        int compare = key.compareTo(node.key);

        if (compare < 0) node.left = put(node.left, key, value);
        else if (compare > 0) node.right = put(node.right, key, value);
        else node.value = value;

        node = fixUp(node);
        fixHeight(node);

        return node;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColor(node);

        return node;
    }

    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;

        right.color = node.color;
        node.color = RED;

        fixHeight(node);

        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;

        left.color = node.color;
        node.color = RED;

        fixHeight(node);

        return left;
    }

    private void flipColor(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
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

    private Node remove(Node node, Key key) {
        int compare = key.compareTo(node.key);

        if (compare < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node);
                node.left = remove(node.left, key);
            }
        } else {
            if (isRed(node.left)) node = rotateRight(node);
            if (node.right == null && compare == 0) return null;
            if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) node = moveRedRight(node);

            if (key.compareTo(node.key) == 0) {
                Node min = min(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = deleteMin(node.right);
            } else if (node.right != null) node.right = remove(node.right, key);
        }

        return fixUp(node);
    }

    private Node moveRedLeft(Node root) {
        flipColor(root);

        if (isRed(root.right.left)) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
            flipColor(root);
        }

        return root;
    }

    private Node moveRedRight(Node root) {
        flipColor(root);

        if (isRed(root.left.left)) {
            root = rotateRight(root);
            flipColor(root);
        }

        return root;
    }

    private Node deleteMin(Node root) {
        if (root == null || root.left == null) return null;
        if (!isRed(root.left) && !isRed(root.left.left)) root = moveRedLeft(root);

        root.left = deleteMin(root.left);

        return fixUp(root);
    }
}