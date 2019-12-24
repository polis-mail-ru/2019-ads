package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RedBlackBst <Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private Node root;
    private int size;
    private static boolean RED = true;
    private static boolean BLACK = false;

    public RedBlackBst() { }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node(Key key, Value value, boolean color, int height) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.height = height;
            size++;
        }
    }

    public RedBlackBst newBst() {
        return new RedBlackBst();
    }

    // Main methods

    private Node get(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) == 0) return x;
        if (key.compareTo(x.key) > 0) x = get(x.right, key);
        if (key.compareTo(x.key) < 0) x = get(x.left, key);
        return x;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key).value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, RED, 1);
        if (key.compareTo(x.key) < 0) {x.left = put(x.left, key, value);}
        else if (key.compareTo(x.key) > 0) {x.right = put(x.right, key, value);}
        else {x.value = value;}
        x = balance(x);
        fixHeight(x);
        return x;
    }

    @Nullable
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node remove(Node x, Key key) {
        if (x == null) return null;
        Node del = null;

        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (x.left.color == BLACK & x.left.left != null & x.left.left.color == BLACK) x = moveRedLeft(x);
                x.left = remove(x.left, key);
            }
        } else {
            if (key.compareTo(x.key) == 0 && x.right == null) {
                return null;
            } else if (x.left != null && x.left.color == RED) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
                del = x;
            } else {
                if (x.right != null && x.right.color == BLACK && x.right.left != null && x.right.left.color == BLACK)
                    x = moveRedRight(x);
                if (key.compareTo(x.key) == 0) {
                    Node min = get(root, min(x.right).key);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                    del = x;
                } else
                    x.right = remove(x.right, key);
                del = x;
            }
        }
        fixHeight(x);
        balance(x);
        return del;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        size--;
        Node t = remove(root, key);
        return t == null ? null : t.value;
    }

    // Key Based Methods

    private Node min(@NotNull Node x) {
        if (x == null) return null;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) return null;
        return get(min());
    }

    @Nullable
    @Override
    public Key max() {
        return max(root);
    }

    private Key max(@NotNull Node x) {
        if (x == null) return null;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) return null;
        return get(max());
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node node = root;
        Key max = null;
        if (node == null) return null;

        while (node != null) {
            if (key.compareTo(node.key) > 0) {
                max = node.key;
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                max = node.key;
                node = node.left;
            } else if (key.compareTo(node.key) == 0) {
                return node.key;
            }
        }
        return max;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = root;
        Node nodeNext = null;
        Key min = null;
        if (node == null) return null;

        while (node != null) {
            nodeNext = node.right;
            if (key.compareTo(node.key) > 0) {
                min = node.key;
                if (key.compareTo(nodeNext.key) < 0) {
                    break;
                }
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                min = node.key;
                node = node.left;
            } else if (key.compareTo(node.key) == 0) {
                return node.key;
            }
        }

        return min;
    }

    // Utility Methods

    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }


    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    // Other Methods

    private Node deleteMax(Node x) {
        if (x.left != null && x.left.color == RED) x = rotateRight(x);
        if (x.right == null) return null;
        if ((x.right != null && x.right.color == BLACK) && (x.right.left != null && x.right.left.color == BLACK)) x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }

    public void deleteMax() {
        size--;
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (x.left.color == BLACK && x.left.left.color == BLACK) x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMin() {
        size--;
        root = deleteMin(root);
        root.color = BLACK;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    private Node moveRedLeft(Node x) {
        x = flipColors(x);
        if (x.right.left != null && x.right.left.color == RED) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            x = flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        x = flipColors(x);
        if (x.left.left != null && x.left.left.color == RED) {
            x = rotateRight(x);
            x = flipColors(x);
        }
        return x;
    }

    private Node balance(Node x) {
        if ((x.left != null && x.left.color == RED) && (x.right != null && x.right.color == RED)) x = flipColors(x);
        if ((x.left != null && x.left.color == RED) && x.left.left.color == RED) x = rotateRight(x);
        if ((x.right != null && x.right.color == RED) && x.left.color == BLACK) x = rotateLeft(x);
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.right.color = !x.right.color;
        x.left.color = !x.left.color;
        return x;
    }
}