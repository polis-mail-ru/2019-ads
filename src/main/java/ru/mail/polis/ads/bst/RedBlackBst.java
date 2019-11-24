package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        public Node(Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    private Node root;

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

    void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }

        return x.color;
    }

    private int getHeight(Node x) {
        if (x == null) {
            return 0;
        }

        return x.height;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }

        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }

        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        x.height = getHeight(x.left) + getHeight(x.right) + 1;

        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        if (key == null) {
            return null;
        }

        return get(key, root);
    }

    private Value get(Key key, Node x) {
        if (x == null) {
            return null; // не найдено
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return get(key, x.left); // идём влево, если меньше, чем x.key
        }

        if (cmp > 0) {
            return get(key, x.right); // идём вправо, если больше, чем x.key
        }

        return x.value; // если key == x.key, то возвращаем x.value
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (key == null) {
            throw new NullPointerException();
        }

        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            x = new Node(key, value, 1, RED);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x = fixUp(x);
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (!containsKey(key)) {
            return null;
        }

        Value result = get(key);

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);

        if (root != null) {
            root.color = BLACK;
        }

        return result;
    }

    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }

            x.left = delete(x.left, key);
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }

            if (key.compareTo(x.key) == 0 && (x.right == null)) {
                return null;
            }

            if (!isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }

            if (key.compareTo(x.key) == 0) {
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = deleteMin(x.right);
            } else {
                x.right = delete(x.right, key);
            }
        }

        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }

        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }

        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }

        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }

        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);

        return (min == null) ? null : min.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }

        if (x.left == null) {
            return x;
        }

        return min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);

        return (min == null) ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);

        return (max == null) ? null : max.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }

        if (x.right == null) {
            return x;
        }

        return max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);

        return (max == null) ? null : max.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node result = floor(key, root);

        return (result == null) ? null : result.key;
    }

    private Node floor(Key key, Node x) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        }

        if (cmp < 0) {
            return floor(key, x.left);
        }

        Node r = floor(key, x.right);

        return (r == null) ? x : r;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node result = ceil(key, root);

        return (result == null) ? null : result.key;
    }

    private Node ceil(Key key, Node x) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        }

        if (cmp > 0) {
            return ceil(key, x.right);
        }

        Node l = ceil(key, x.left);

        return (l == null) ? x : l;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }

        return size(x.left) + size(x.right) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return 0;
        }

        return x.height;
    }
}
