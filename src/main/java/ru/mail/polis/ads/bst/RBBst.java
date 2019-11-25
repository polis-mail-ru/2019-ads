package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RBBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

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
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
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
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node = fixUp(node);
        return node;
    }

    void deleteMin() {
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

    void deleteMax() {
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
        return remove(root, key).value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = remove(node.left, key);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.right != null) {
                if (isRed(node.left)) {
                    node = rotateRight(node);
                }
                if (!isRed(node.right.left) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                node.right = remove(node.right, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (node.right == null) {
                return null;
            }
            node.key = min(node.right).key;
            node.value = get(node.right, node.key);
            node.right = deleteMin(node.right);
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
        return max(node);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.right.key) > 0) {
            return floor(node.right, key);
        }
        return node.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.left.key) < 0) {
            return ceil(node.left, key);
        }
        return node.key;
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
}
