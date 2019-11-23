package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value>  {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean isRed;

        public Node(Key key, Value value, int height, boolean isRed) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.isRed = isRed;
        }
    }

    private Node root;

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.isRed = x.isRed;
        x.isRed = true;
        return right;
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.isRed = x.isRed;
        x.isRed = true;
        return left;
    }

    void flipColors(Node x) {
        x.isRed = !x.isRed;
        x.left.isRed = !x.left.isRed;
        x.right.isRed = !x.right.isRed;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }

        return x.isRed;
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
        root.isRed = false;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            x = new Node(key, value, 1, true);
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

        return root.value;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);

        if (min == null) {
            return null;
        }

        return min.key;
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

        if (min == null) {
            return null;
        }

        return min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);

        if (max == null) {
            return null;
        }

        return max.key;
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

        if (max == null) {
            return null;
        }

        return max.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(key, root);
    }

    private Key floor(Key key, Node x) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp > 0 && x.right != null) {
            return floor(key, x.right);
        }

        if (cmp < 0 && x.left != null) {
            return floor(key, x.left);
        }

        return x.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(key, root);
    }

    private Key ceil(Key key, Node x) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp > 0 && x.left != null) {
            return floor(key, x.left);
        }

        if (cmp < 0 && x.right != null) {
            return floor(key, x.right);
        }

        return x.key;
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
