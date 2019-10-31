package ru.mail.polis.ads.bst;

import javax.swing.tree.FixedHeightLayoutCache;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    Node root;
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height){
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {

        return get(root, key);
    }
    public Value get(Node x, Key key){
        if (x == null){
            return null;
        }
        if (key.compareTo(x.key) > 0){
            return get(x.right, key);
        }
        if (key.compareTo(x.key) < 0){
            return get(x.left, key);
        }

        return x.value;
    }

    @Override
    public void put(Key key, Value value) {
        if (root == null){
            root = new Node(key, value, 1);
            return;
        }
        put(root, key, value);
    }
    public Node put(Node x, Key key, Value value){
        if (x == null){
            x = new Node(key, value, 1);
            return x;
        }
        if (key.compareTo(x.key) > 0){
            return put(x.right, key, value);
        }
         if (key.compareTo(x.key) < 0){
            return put(x.left, key, value);
        }
        x.value = value;
        fixHeight(x);
        x = balance(x);
        return x;
    }


    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }
    public Node remove(Node x, Key key){
        if (x.value == null){
            return null;
        }
        if (key.compareTo(x.key) > 0){
            return remove(x.right, key);
        }
        if (key.compareTo(x.key) < 0){
            return remove(x.left, key);
        }
        return delete(x);
    }
    Node deleteMin(Node x) {
        if (x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
        }
    Node delete(Node x){
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node temp = x;
        x = min(temp.right);
        x.right = deleteMin(temp.right);
        x.left = temp.left;
        return x;
    }
    void deleteMin() {
        root = deleteMin(root);
    }
    @Override
    public Key min() {
        if (root == null){
            return null;
        }else {
            return min(root).key;
        }
    }
    Node min(Node x){
        if (x.left != null){
            return min(x.left);
        }else{
            return x;
        }
    }

    @Override
    public Value minValue() {
        if (root == null){
            return null;
        }else {
            return get(min(root).key);
        }
    }

    @Override
    public Key max() {
        if (root == null){
            return null;
        }else {
            return max(root).key;
        }
    }
    Node max(Node x){
        if (x.right != null){
            return max(x.right);
        }else{
            return x;
        }
    }

    @Override
    public Value maxValue() {
        if (root == null){
            return null;
        }else {
            return get(max(root).key);
        }
    }

    @Override
    public Key floor(Key key) {
        if (root == null){
            return null;
        }
        return  floor(root, key);
    }
    Key floor(Node x, Key key){
        if (key.compareTo(x.key) < 0){
            return max(x.left).key;
        }else if (key == x.key){
            return x.key;
        }return floor(x.left, key);
    }

    @Override
    public Key ceil(Key key) {
        if (root == null){
            return null;
        }
        return ceil(root, key);
    }

    Key ceil(Node x, Key key){
        if (key.compareTo(x.key) > 0){
            return min(x.right).key;
        }else if (key == x.key){
            return x.key;
        }return ceil(x.right, key);
    }

    @Override
    public int size() {
        return size(root);
    }
    int size(Node x){
        if (x == null){
            return 0;
        }
        if (x.left == null && x.right == null ){
            return 1;
        }
        return size(x.left)+size(x.right);
    }

    @Override
    public int height() {
        return height(root);
    }
    int height(Node x) {
        if (x==null){
            return 0;
        }
        return x.height;
    }

    void fixHeight(Node x){
        if (x.left == null){
            if (x.right == null){
                x.height = height(x);
            }else {
                x.height = height(x.right);
            }
        }else if (x.right == null){
                x.height = height(x.left);
            }
        else{
            x.height = Math.max(height(x.left), height(x.right));
        }
    }

    Node rotateRight(Node x){
        Node t = x.left;
        t.right = x;
        x.left = null;
        fixHeight(x);
        fixHeight(t);
        return t;
    }
    Node rotateLeft(Node x){
        Node t = x.right;
        t.left = x;
        x.right = null;
        fixHeight(x);
        fixHeight(t);
        return t;
    }
    int factor(Node x){
        return height(x.left) - height(x.right);
    }

    Node balance(Node x){
        if (factor(x) == -2) {
            if (factor(x.left) > 0){
                rotateLeft(x.left);
            }
            return rotateRight(x);
        }if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
