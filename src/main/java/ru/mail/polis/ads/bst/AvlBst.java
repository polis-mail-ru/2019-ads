package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node (Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    AvlBst() {
        size = 0;
    }
    private Node topNode;
    private Value deletedValue;
    private int size;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return topNode != null ? get(topNode, key).value : null;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;

        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return node;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        topNode = put(topNode, key, value);
        topNode.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1, RED);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    private void fixHeight(Node node) {
        if (node.right == null && node.left != null)
            node.height = isRed(node.left) ? node.left.height : node.left.height + 1;
        else if (node.right != null && node.left == null)
            node.height = node.right.height + 1;
        else if (node.right == null)
            node.height = 1;
        else
            node.height = Math.max(node.left.height, node.right.height) + 1;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        deletedValue = null;

        if (topNode != null)
            topNode = delete(topNode, key);
        else
            return null;

        return deletedValue;
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left))
                    node = moveRedLeft(node);
                node.left = delete(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }

            if (cmp == 0 && (node.right == null)) {
                deletedValue = node.value;
                return null;
            }

            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }

            if (cmp == 0) {
                deletedValue = node.value;

                Node minNode = min(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return fixUp(node);
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
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

    private Node deleteMin(Node node) {
        if (node.left == null)
            return null;
        if (!isRed(node.left) && !isRed(node.left.left))
            node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    @Nullable
    @Override
    public Key min() {
        if (topNode == null) return null;
        Node node = min(topNode);
        return node.key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        if (topNode == null) return null;
        Node minNode = min(topNode);
        return minNode.value;
    }

    @Nullable
    @Override
    public Key max() {
        if (topNode == null) return null;
        Node node = max(topNode);
        return node.key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (topNode == null) return null;
        Node maxNode = max(topNode);
        return maxNode.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (topNode == null) return null;
        Node node = get(topNode, key);
        return node != null ? max(node.left).key : null;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (topNode == null) return null;
        Node node = get(topNode, key);
        return node != null ? min(node.right).key : null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return topNode != null ? topNode.height : 0;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
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

    private void flipColors(Node node) {
        node.color = !node.color;
        node.right.color = !node.right.color;
        node.left.color = !node.left.color;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.right) && isRed(node.left)) {
            flipColors(node);
        }
        return node;
    }

}
