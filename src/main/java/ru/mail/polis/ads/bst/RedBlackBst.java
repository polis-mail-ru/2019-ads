package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node(@NotNull Key key, @NotNull Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    static final boolean RED = true;
    static final boolean BLACK = false;
    Node root;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int comparing = key.compareTo(node.key);

        if (comparing > 0) {
            return get(node.right, key);
        } else if (comparing < 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, @NotNull Key key, @NotNull Value value) {
        if (node == null) {
            return new Node(key, value, 1, RED);
        }
        int comparing = key.compareTo(node.key);
        if (comparing < 0) {
            node.left = put(node.left, key, value);
        } else if (comparing > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    public void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
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

    public void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node node) {
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        if (node.right == null) {
            return null;
        }
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
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
        int comparing = key.compareTo(node.key);
        if (comparing < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (node.right == null && comparing == 0) {
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
    public Value minValue() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.value;
    }

    @Nullable
    @Override
    public Key min() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.key;
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
    public Value maxValue() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.key;
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
        return floor(root, key, null);
    }

    private Key floor(Node x, Key key, Key max) {
        if (x == null) {
            return max;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            max = floor(x.left, key, max);
        } else if (comp > 0) {
            if (max == null || max.compareTo(x.key) < 0) {
                max = x.key;
            }
            max = floor(x.right, key, max);
        } else {
            max = x.key;
        }
        return max;
    }


    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key, null);
    }

    private Key ceil(Node node, Key key, Key min) {
        if (node == null) {
            return min;
        }
        int comp = key.compareTo(node.key);
        if (comp > 0) {
            min = ceil(node.right, key, min);
        } else if (comp < 0) {
            if (min == null || min.compareTo(node.key) > 0) {
                min = node.key;
            }
            min = ceil(node.left, key, min);
        } else {
            min = node.key;
        }
        return min;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
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

    private Node flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        return node;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            node = flipColors(node);
        }
        return node;
    }

    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
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

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }
}
