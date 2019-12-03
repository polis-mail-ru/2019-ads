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

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(@NotNull Key key, @NotNull Value value, @NotNull int height, @NotNull boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    private Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return get(node.right, key);
        } else if (cmp < 0) {
            return get(node.left, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {

        if (!containsKey(key) || root == null) {
            return null;
        }
        Value value = get(key);
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = remove(root, key);
        return value;
    }

    private Node remove(Node node, Key key) {
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }

            if (node.right == null && cmp == 0) {
                return null;
            }

            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0) {
                Node min = min(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = remove(node.right, key);
            }
        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : min(root).key;
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
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node res = floor(root, key);
        return res == null ? null : res.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return floor(node.left, key);
        }
        Node res = floor(node.right, key);
        return res == null ? node : res;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Node res = ceil(root, key);

        return (res == null) ? null : res.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            return ceil(node.right, key);
        }

        Node res = ceil(node.left, key);
        return res == null ? node : res;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
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

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }
}
