package ru.mail.polis.ads.bst.AvlTree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AvlBst <Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private Node root;
    private int size;

    public AvlBst() {
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
            size++;
        }
    }

    public AvlBst newBst() {
        return new AvlBst();
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
        Node node = get(root, key);
        if (node == null) return null;
        return node.value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        if (key.compareTo(x.key) < 0) {x.left = put(x.left, key, value);}
        else if (key.compareTo(x.key) > 0) {x.right = put(x.right, key, value);}
        else {x.value = value;}
        x = balance(x);
        fixHeight(x);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {root = put(root, key, value);}

    public Node remove(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {x.left = remove(x.left, key);}
        if (key.compareTo(x.key) > 0) {x.right = remove(x.right, key);}
        if (key == x.key) {x = innerDelete(x);}
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        size--;
        return remove(root, key).value;
    }

    // Key Based Methods

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node node = min(root);
        if (node == null) return null;
        return node.key;
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

    private Key max(Node x) {
        if (root == null) return null;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {return height(root);}

    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    // Other Methods

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public void deleteMin() {
        size--;
        root = deleteMin(root);
    }

    private Node innerDelete(Node x) {
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }
}
