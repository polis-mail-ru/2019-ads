package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp < 0) return get(x.left, key);
        if (comp > 0) return get(x.right, key);
        return x.value;
    }

    private Node getNode(Node x, Key key) {
        if (x == null) return null;
        int comp = key.compareTo(x.key);
        if (comp < 0) return getNode(x.left, key);
        if (comp > 0) return getNode(x.right, key);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value, 1, RED);
        int comp = key.compareTo(x.key);
        if (comp < 0)
            x.left = put(x.left, key, value);
        else if (comp > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        fixHeight(x);
        x = fixUp(x);
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.key;
        return min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        return get(min());
    }

    @Nullable
    @Override
    public Key max() {
        return max(root);
    }

    private Key max(@NotNull Node x) {
        if (x == null) return null;
        if (x.right == null) return x.key;
        return max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        return get(max());
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key, null);
    }

    private Key floor(Node x, Key key, Key max) {
        if (x == null) return max;
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            max = floor(x.left, key, max);
        } else if (comp > 0) {
            if (max == null || max.compareTo(x.key) < 0) max = x.key;
            max = floor(x.right, key, max);
        } else max = x.key;
        return max;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }

    private Key ceil(Node x, Key key, Key min) {
        if (x == null) return min;
        int comp = key.compareTo(x.key);
        if (comp > 0) {
            min = ceil(x.right, key, min);
        } else if (comp < 0) {
            if (min == null || min.compareTo(x.key) > 0) min = x.key;
            min = ceil(x.left, key, min);
        } else min = x.key;
        return min;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return size(x.left) + size(x.right) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private void fixHeight(Node x) {
        x.height = Math.max(height(x.left), height(x.right)) + 1;
    }

    private class Node {
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

}
