package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    
    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int height;

        Node(Key key, Value value, int height){
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;

    private void fixHeight(Node elem) {
        elem.height = 1 + Math.max(height(elem.left), height(elem.right));
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key ){
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) return get(x.left, key);
        if (key.compareTo(x.key) > 0) return get(x.right, key);
        return x.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);

    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);
        if (key.compareTo(x.key) < 0) x.left = put(x.left, key, value);
        else if (key.compareTo(x.key) > 0) x.right = put(x.right, key, value);
        else x.value = value;

        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node balance(Node x) {
        if (delta(x) == -2) {
            if (delta(x.right) > 0)  x.right = rotateRight(x.right);
            return rotateLeft(x);
        }

        if (delta(x) == 2) {
            if (delta(x.left) < 0) x.left = rotateLeft(x.left);
            return rotateRight(x);
        }

        return x;
    }

    private int delta(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    private Node remove(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) > 0) x.right = remove(x.right, key);
        else if (key.compareTo(x.key) < 0) x.left = remove(x.left, key);
        else x = innerDelete(x);
        fixHeight(x);
        balance(x);
        return x;
    }

    private Node minNode(Node x) {
        if (x.left == null) return x;
        return minNode(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }

        x.left = deleteMin(x.left);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.left == null) return x.right;
        if (x.right == null) return x.left;
        Node t = x;
        x = minNode(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }


    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    @Override
    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.key;
        return min(x.left);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.key;
        return max(x.right);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(Key key) {
        if (root == null) {
            return null;
        }

        Node floor = floor(root, key);
        if (floor == null) {
            return null;
        }

        return floor.key;
    }

    private Node floor(Node x, Key key) {
        if (x.right != null && key.compareTo(x.right.key) > 0) {
            return floor(x.right, key);
        }
        if (x.left != null && key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        if (key.compareTo(x.key) < 0) {
            return null;
        }
        return x;
    }

    @Override
    public Key ceil(Key key) {
        if (root == null) {
            return null;
        }

        Node ceil = ceil(root, key);
        if (ceil == null) {
            return null;
        }

        return ceil.key;
    }

    private Node ceil(Node x, Key key) {
        if (x.left != null && key.compareTo(x.left.key) < 0) {
            return ceil(x.left, key);
        }
        if (x.right != null && key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        if (key.compareTo(x.key) > 0) {
            return null;
        }
        return x;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return size(x.left) + size(x.right) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }
}

