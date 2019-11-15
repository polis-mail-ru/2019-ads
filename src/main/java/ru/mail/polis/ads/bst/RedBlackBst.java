package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
    private Node root;

    class Node {
=======
    private static final boolean BLACK = false;
    private static final boolean RED = true;
    
    private class Node {
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
        Key key;
        Value value;
        Node left;
        Node right;
<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
        int height;
        int size;

        Node(Key key, Value value, int height, int size) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.size = size;
        }
=======
        boolean color;
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
    }

    @Nullable
    @Override
<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Input key is null");
        Node x = get(root, key);
        if (x == null) return null;
        return x.value;
    }

    private Node get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("Input key is null");
        if (value == null) {
            remove(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value){
        if (node == null) return new Node(key, value, 1, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else {
            node.value = value;
            return node;
        }
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node balance(Node node){
        if (balanceValue(node) < -1){
            if (balanceValue(node.right) > 0) node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        else if (balanceValue(node) > 1){
            if (balanceValue(node.left) < 0) node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        return node;
    }

    private int balanceValue(Node node){
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        rotate(node, tmp);
        return tmp;
    }

    private Node rotateLeft(Node node) {
        Node tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        rotate(node, tmp);
        return tmp;
    }

    private void rotate(Node x, Node y){
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
=======
    public Value get(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
    }

    @Nullable
    @Override
<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
    public Value remove(Key key) {
        if (key == null) throw new IllegalArgumentException("Input key is null");
        if (!containsKey(key)) return null;
        return remove(root, key).value;
    }

    private Node remove(Node node, Key key){
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = remove(node.left, key);
        else if (cmp > 0) node.right = remove(node.right, key);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                Node tmp = node;
                node = min(tmp.right);
                node.right = removeMin(tmp.right);
                node.left = tmp.left;
            }
        }
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private boolean containsKey(Key key) {
        return get(key) != null;
=======
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) return null;
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) return null;
        return min(root).value;
    }

    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }

    private Node removeMin(Node node){
        if (node.left == null) return node.right;
        node.left = removeMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) return null;
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) return null;
        return max(root).value;
    }

    private Node max(Node node){
        if (node.right == null) return node;
        return min(node.right);
    }

    @Nullable
    @Override
<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("Input key is null");
        if (root == null) return null;
        Node fl = floor(root, key);
        return fl == null ? null : fl.key;
    }

    private Node floor(Node node, Key key){
        if (key == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp < 0) return floor(node.left, key);
        Node y = floor(node.right, key);
        return y != null ? y : node;
=======
    public Key floor(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
    }

    @Nullable
    @Override
<<<<<<< HEAD:src/main/java/ru/mail/polis/ads/bst/AvlBst.java
    public Key ceil(Key key) {
        if (key == null) throw new IllegalArgumentException("Input key is null");
        if (root == null) return null;
        Node cl = ceil(root, key);
        return cl == null ? null : cl.key;
    }

    private Node ceil(Node node, Key key){
        if (key == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node;
        else if (cmp > 0) return ceil(node.right, key);
        Node y = ceil(node.left, key);
        return y != null ? y : node;
=======
    public Key ceil(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
>>>>>>> c77ac6fc4f9181d7099d8fee560ec5294cf49776:src/main/java/ru/mail/polis/ads/bst/RedBlackBst.java
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return node.height;
    }
}
