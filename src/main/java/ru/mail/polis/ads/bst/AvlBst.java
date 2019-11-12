package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value>{

    public Node root;
    public class Node {
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

    public Value get(Node x, Key key) {
        if (x == null)
            return null;
        if (key.compareTo(x.key) < 0)
            return get(x.left, key);
        if (key.compareTo(x.key) > 0)
            return get(x.right, key);

        return x.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {

        if (x == null)
            return new Node(key, value, 1);
        if (key.compareTo(x.key) < 0){
            x.left = put(x.left, key, value);
        }
        else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        }
        else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    public Node remove(Node x, Key key){
        if (x == null)
            return null;
        if (key.compareTo(x.key) < 0)
            x.left = remove(x.left, key);
        else if (key.compareTo(x.key) > 0)
            x.right = remove(x.right, key);
        else
            x = innerDelete(x);
        return x;
    }

    private Node minNode(Node x){
        if (x.left == null)
            return x;
        return minNode(x.left);
    }

    private Node deleteMin(Node x){
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    private Key min(Node x){
        if (x == null)
            return null;
        if (x.left == null)
            return x.key;
        return min(x.left);
    }
    @Override
    public Key min() {
        return min(root);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    private Key max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.key;
        return max(x.right);
    }

    @Override
    public Key max() {
        return max(root);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    private Node innerDelete(Node x){
        if (x.right == null)
            return x.left;
        if (x.left == null)
            return x.right;
        Node tmp = x;
        x = minNode(tmp.right);
        x.right = deleteMin(tmp.right);
        x.left = tmp.left;
        return x;
    }

    public void fixHeight(Node x){
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    public int factor(Node x){
        return height(x.left) - height(x.right);
    }

    public Node balance(Node x){
        if(factor(x) == 2){
            if(factor(x.left) < 0){
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if(factor(x) == -2){
            if(factor(x.right) < 0){
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    public Node rotateRight(Node x){
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    public Node rotateLeft(Node x){
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    public int size(Node x) {
        return (x == null) ? 0 : ( size(x.left) + size(x.right) + 1 );
    }

    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public Key floor(Key key) {
        return floor(root, key, max(root));
    }

    @Override
    public Key ceil(Key key) {
        return ceil(root, key, min(root));
    }

    private Key floor(Node x, Key key, Key max){
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {
            max = floor(x.left, key, max);
        } else if (key.compareTo(x.key) > 0) {
            if (max == null || max.compareTo(x.key) < 0)
                max = x.key;
            max = floor(x.right, key, max);
        } else max = x.key;
        return max;
    }

    private Key ceil(Node x, Key key, Key min){
        if (x == null) return null;
        if (key.compareTo(x.key) > 0) {
            min = ceil(x.right, key, min);
        } else if (key.compareTo(x.key) < 0) {
            if (min == null || min.compareTo(x.key) > 0) min = x.key;
            min = ceil(x.left, key, min);
        } else min = x.key;
        return min;
    }
}
