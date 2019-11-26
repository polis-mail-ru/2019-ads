package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RedBlackBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    static boolean RED = true;
    static boolean BLACK = false;
    class Node {

        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;
        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = 1;
            color = RED;
        }

    }

    Node top;
    private int size;
    private Node removed;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node found = findNode(top, key);
        return found != null ? found.value : null;
    }

    private Node findNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return findNode(node.left, key);
        } else {
            return findNode(node.right, key);
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        top = put(top, key, value);
        top.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node = fixUp(node);
        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (top == null) {
            return null;
        }
        top = remove(top, key);
        Node removed = this.removed;
        this.removed = null;
        if (removed != null) {
            if (top != null) {
                top.color = BLACK;
            }
            size--;
            return removed.value;
        }
        return null;
    }

    private Node remove(Node node, Key key) {
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            if (!isRed(node.left) && node.left != null && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (cmp == 0 && node.right == null) {
                removed = node;
                return null;
            }

            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            cmp = key.compareTo(node.key);
            if (cmp == 0) {
                removed = node;
                Node min = min(node.right);
                min.left = node.left;
                min.right = deleteMin(node.right);
                node = min;
            } else {
                node.right = remove(node.right, key);
            }
        }
        return fixUp(node);
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
        return top.height;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        if (node.left != null) {
            node.left.color = !node.left.color;
        }
        if (node.right != null) {
            node.right.color = !node.right.color;
        }
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (node.right != null && isRed(node.right.right)) {
            node.right = rotateLeft(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) || !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return fixUp(node);
    }
}
