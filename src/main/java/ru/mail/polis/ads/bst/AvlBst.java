package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private final static boolean RED = true;
    private final static boolean BLACK = false;
    private Node root;
    private int size;

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

    public AvlBst() {
        size = 0;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

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

    private Node moveRedLeft(Node x) {
        flip(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flip(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flip(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flip(x);
        }
        return x;
    }
    private void flip(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node fix(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flip(x);
        }
        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x = fix(x);
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        return delete(root, key).value;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else if (key.compareTo(x.key) > 0) {
            if (x.right != null) {
                if (isRed(x.left)) {
                    x = rotateRight(x);
                }
                if (!isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                x.right = delete(x.right, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }
            if (x.right == null) {
                return null;
            }
            x.key = minNode(x.right).key;
            x.value = get(x.right, x.key);
            x.right = deleteMin(x.right);
        }
        return fix(x);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return  null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fix(x);
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return fix(x);
    }

    private Node minNode(Node currentNode) {
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    private Node maxNode() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }

    @Nullable
    @Override
    public Key min() {
        Node node = minNode(root);
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node node = minNode(root);
        return node == null ? null : node.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node node = maxNode();
        return node == null ? null : node.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node node = maxNode();
        return node == null ? null : node.value;
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
        if (node.key.compareTo(key) == 0) {
            return node.key;
        }
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }
        Key floor = node.key;
        if (node.right != null) {
            floor = floor(node.right, key);
        }
        return (floor.compareTo(key) <= 0) ? floor : node.key;
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
        if (node.key.compareTo(key) == 0) {
            return node.key;
        }
        if (node.key.compareTo(key) < 0) {
            return ceil(node.right, key);
        }
        Key ceil = node.key;
        if (node.left != null) {
            ceil = ceil(node.left, key);
        }
        return (ceil.compareTo(key) >= 0) ? ceil : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
