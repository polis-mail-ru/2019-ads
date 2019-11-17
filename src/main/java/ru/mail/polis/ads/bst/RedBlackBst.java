package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RedBlackBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    enum Color {
        RED,
        BLACK
    }

    class Node {
        private Key key;
        private Value value;
        Node left;
        Node right;
        Color color;
        private int height;

        private Node(Key key, Value value, Color color) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.height = 1;
        }

        private int getHeight() {
            return height;
        }
    }

    Node top;
    private int size;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = findNode(top, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node findNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return findNode(node.left, key);
        }
        return findNode(node.right, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        top = put(top, key, value);
        top.color = Color.BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, Color.RED);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            node.color = Color.RED;
            node.left.color = Color.BLACK;
            node.right.color = Color.BLACK;
        }
        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Nullable
    @Override
    public Key min() {
        if (top == null) {
            return null;
        }
        return min(top).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (top == null) {
            return null;
        }
        return min(top).value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Nullable
    @Override
    public Key max() {
        if (top == null) {
            return null;
        }
        return max(top).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (top == null) {
            return null;
        }
        return max(top).value;
    }

    private Node floor(Node node, Key key) {
        if (node.right != null && key.compareTo(node.right.key) > 0) {
            return floor(node.right, key);
        }
        if (node.left != null && key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.key) < 0) {
            return null;
        }
        return node;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (top == null) {
            return null;
        }
        Node floor = floor(top, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node ceil(Node node, Key key) {
        if (node.left != null && key.compareTo(node.left.key) < 0) {
            return ceil(node.left, key);
        }
        if (node.right != null && key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.key) > 0) {
            return null;
        }
        return node;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (top == null) {
            return null;
        }
        Node ceil = ceil(top, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        if (top == null) {
            return 0;
        }
        return top.getHeight();
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }
}
