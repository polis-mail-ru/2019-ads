package ru.mail.polis.ads.bst;

import java.awt.image.AreaAveragingScaleFilter;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size;

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
        }
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public void put(Key key, Value value) {
        ++size;
        root = put(root, key, value);
    }

    @Override
    public void remove(Key key) {
        --size;
        remove(root, key);
    }

    @Override
    public Key min() {
        return min(root).key;
    }

    @Override
    public Value minValue() {
        return min(root).value;
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        return max(root).value;
    }

    @Override
    public Key floor(Key key) {
        Node nodeWithKey = findNode(root, key);
        Node x = nodeWithKey.left;
        if (x == null)
            return null;
        else {
            while (x.right != null)
                x = x.right;
            return x.key;
        }
    }

    @Override
    public Key ceil(Key key) {
        Node nodeWithKey = findNode(root, key);
        Node x = nodeWithKey.right;
        if (x == null)
            return null;
        else {
            while (x.left != null)
                x = x.left;
            return x.key;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Node findNode(Node x, Key key) {
        if (x == null)
            return null;
        if (key.compareTo(x.key) > 0)
            return findNode(x.right, key);
        if (key.compareTo(x.key) < 0)
            return findNode(x.left, key);
        return x;

    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        if (key.compareTo(x.key) < 0)
            return get(x.left, key);
        if (key.compareTo(x.key) > 0)
            return get(x.right, key);
        return x.value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null)
            return new Node(key, value, 1);
        if (key.compareTo(x.key) < 0)
            x.left =  put(x.left, key, value);
        else if (key.compareTo(x.key) > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        fixHeight(x);
        x = balance(x);

        return x;
    }

    private Node remove(Node x, Key key) {
        if (x == null)
            return null;
        if (key.compareTo(x.key) < 0)
            x.left = remove(x.left, key);
        else if (key.compareTo(x.key) > 0)
            x.right = remove(x.right, key);
        else
            x = innerRemove(x);

        return x;
    }

    private Node innerRemove(Node x) {
        if (x.right == null)
            return x.left;
        if (x.left == null)
            return x.right;
        Node tmp = x;
        x = min(tmp.right);
        x.right = removeMin(tmp.right);
        x.left = tmp.left;
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = removeMin(x.left);
        return x;
    }

    private Node min(Node node) {
        Node curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private Node max(Node node) {
        Node curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0)
                x.left = rotateLeft(x.left);
            rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0)
                x.right = rotateRight(x.right);
            rotateLeft(x);
        }

        return x;
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
}
