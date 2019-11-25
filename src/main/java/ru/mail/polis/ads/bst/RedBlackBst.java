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
    
    private static final boolean RIGHT = false;
    private static final boolean LEFT = true;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, boolean color, int height) {
          this.key = key;
          this.value = value;
          this.color = color;
          this.height = height;
        }
    }

    private Node node;
    private int size;

    public RedBlackBst() {
        this.size = 0;
    }

    public RedBlackBst(Node node) {
        this.node = node;
        this.size = 1;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(node, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compRes = key.compareTo(node.key);

        if (compRes < 0) {
            return get(node.left, key);
        }

        if (compRes > 0) {
            return get(node.right, key);
        }

        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        node = put(node, key, value);
        node.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            ++size;
            return new Node(key, value, RED, 1);
        }

        int compRes = key.compareTo(node.key);

        if (compRes < 0) {
            node.left = put(node.left, key, value);
        } else if (compRes > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node = balance(node);
        updateHeight(node);

        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(node, key);

        if (value != null) {
            node = remove(node, key);
            --size;
        }

        return value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compRes = key.compareTo(node.key);

        if (compRes < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRed(node, LEFT);
                }

                node.left = remove(node.left, key);
            }
        } else if (compRes > 0) {
            if (node.right != null) {
                if (isRed(node.left)) {
                    node = rotate(node, RIGHT);
                }

                if (!isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRed(node, RIGHT);
                }

                node.right = remove(node.right, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotate(node, RIGHT);
            }

            if (node.right == null) {
                return null;
            }

            node.key = min(node.right).key;
            node.value = get(node.right, node.key);
            node.right = removeMin(node.right);
        }

        updateHeight(node);

        return balance(node);
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(node);

        return min == null ? null : min.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(node);

        return min == null ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(node);

        return max == null ? null : max.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(node);

        return max == null ? null : max.value;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }

        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }

        return max(node.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(node, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compRes = key.compareTo(node.key);

        if (compRes < 0) {
            return floor(node.left, key);
        }

        if (compRes > 0) {
            return floor(node.right, key);
        }

        return node.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(node, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compRes = key.compareTo(node.key);

        if (compRes > 0) {
            return floor(node.right, key);
        }

        if (compRes < 0) {
            return floor(node.left, key);
        }

        return node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(node);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node rotate(Node node, boolean left) {
        Node tmp = null;

        if (left) {
            tmp = node.right;
            node.right = tmp.left;
            tmp.left = node;
        } else {
            tmp = node.left;
            node.left = tmp.right;
            tmp.right = node;
        }

        tmp.color = node.color;
        node.color = RED;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private void colorFlip(Node node) {
        if ((node.left == null) || (node.right == null))
        {
            throw new NullPointerException("One of the tree nodes is null.");
        }

        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotate(node, LEFT);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotate(node, RIGHT);
        }

        if (isRed(node.left) && isRed(node.right)) {
            colorFlip(node);
        }

        return node;
    }

    private Node moveRed(Node node, boolean left) {
        colorFlip(node);

        if (isRed((left ? node.right : node.left).left)) {
            if (left) {
                node.right = rotate(node.right, RIGHT);
            }

            node = rotate(node, left ? LEFT : RIGHT); //Ternary just for better code readability.
            colorFlip(node);
        }

        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRed(node, LEFT);
        }

        node.left = removeMin(node.left);

        return balance(node);
    }

    private boolean isRed(Node node) {
        return (node != null) && node.color;
    }
}
