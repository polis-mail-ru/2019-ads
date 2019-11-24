package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.Contract;
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
        int height;
        boolean color;
        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
            this.color = color;
        }
    }

    private Node root;
    private int size;

    RedBlackBst() {
        root = null;
        size = 0;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node result = find(root, key);
        return result == null ? null : result.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Not today");
    }

    @Nullable
    @Override
    public Key min() {
        final Node minNode = minNode(root);
        return minNode == null ? null : minNode.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        final Node minNode = minNode(root);
        return minNode == null ? null : minNode.value;
    }

    @Nullable
    @Override
    public Key max() {
        final Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        final Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        final Node node = floor(root, key);
        return (node == null) ? null : node.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        final Node node = ceil(root, key);
        return (node == null) ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Contract(value = "null -> null", pure = true)
    private Node leftOrNull(Node node) {
        return (node == null) ? null : node.left;
    }

    @Contract("null, _ -> null")
    private Node find(Node current, Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            return find(current.left, key);
        } else if (comp < 0) {
            return find(current.right, key);
        }

        return current;
    }

    @Contract("null, _ -> null")
    private Node floor(final Node current, final Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            return floor(current.left, key);
        }

        final Node result = floor(current.right, key);
        return (result == null) ? current : result;
    }

    @Contract("null, _ -> null")
    private Node ceil(final Node current, final Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp < 0) {
            return ceil(current.right, key);
        }

        final Node result = ceil(current.left, key);
        return (result == null) ? current : result;
    }

    @NotNull
    private Node put(Node current, Key key, Value value) {
        if (current == null) {
            ++size;
            return new Node(key, value, RED);
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            current.left = put(current.left, key, value);
        } else if (comp < 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }

        updateHeight(current);
        current = fixUp(current);
        return current;
    }

    @NotNull
    private Node fixUp(@NotNull Node current) {
        if (isRed(current.right) && !isRed(current.left)) {
            current = rotateLeft(current);
        }
        if (isRed(current.left) && isRed(leftOrNull(current.left))) {
            current = rotateRight(current);
        }
        if (isRed(current.left) && isRed(current.right)) {
            flipColors(current);
        }
        return current;
    }

    private void updateHeight(@NotNull Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    @NotNull
    private Node rotateRight(@NotNull Node current) {
        Node left = current.left;
        current.left = left.right;
        left.right = current;
        left.color = current.color;
        current.color = RED;

        updateHeight(current);
        updateHeight(left);

        return left;
    }

    @NotNull
    private Node rotateLeft(@NotNull Node current) {
        Node right = current.right;
        current.right = right.left;
        right.left = current;
        right.color = current.color;
        current.color = RED;

        updateHeight(current);
        updateHeight(right);

        return right;
    }

    @Contract(value = "null -> null", pure = true)
    private Node minNode(final Node current) {
        if (current == null) {
            return null;
        }

        Node node = current;
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    @Contract(value = "null -> null", pure = true)
    private Node maxNode(final Node current) {
        if (current == null) {
            return null;
        }

        Node node = current;
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    @Contract(value = "null -> false", pure = true)
    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    private void flipColors(@NotNull Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

}
