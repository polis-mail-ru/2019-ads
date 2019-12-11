package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        public Node(Key key, Value value, int height){
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }
        else {
            node.value = value;
        }

        fixHeight(node);
        node = balance(node);
        return node;
    }
    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node gotoMin = node.left;
        node.left = gotoMin.right;
        gotoMin.right = node;
        fixHeight(node);
        fixHeight(gotoMin);
        return gotoMin;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        }
        else node = innerDelete(node);
        fixHeight(node);
        balance(node);
        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node nodeHelp = node;
        node = minNode(nodeHelp.right);
        node.right = deleteMin(nodeHelp.right);
        node.left = nodeHelp.left;
        return node;
    }

    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    @Override
    public Key min() {
        return min(root);
    }
    private Key min(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.key;
        }
        return min(node.left);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return max(root);
    }

    private Key max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.key;
        }
        return max(node.right);
    }


    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(Key key) {
        if (root == null){
            return null;
        }
        return floor(root, key);
    }
    private Key floor(Node node, Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.key;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        Key result = floor(node.right, key);
        return (result == null) ? node.key : result;

    }

    @Override
    public Key ceil(Key key) {
        if (root == null){
            return null;
        }
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node.key;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        Key result = ceil(node.left, key);
        return (result == null) ? node.key : result;

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
}
