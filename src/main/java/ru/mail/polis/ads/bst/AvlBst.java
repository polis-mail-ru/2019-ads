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
        else if (key.compareTo(x.key) < 0){
            return get(x.left, key);
        }else{
            return x.value;
        }


    }

    @Override
    public void put(Key key, Value value) {
        if (root == null){
            root = new Node(key, value, 1);
            return;
        }
        root = put(root, key, value);
    }
    public Node put(Node x, Key key, Value value){
        if (x == null){
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) > 0){
            x.right = put(x.right, key, value);
        }
         else if (key.compareTo(x.key) < 0){
            x.left = put(x.left, key, value);
        }else {
            x.value = value;
            return x;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }


    @Override
    public Value remove(Key key) {

        Node res = remove(root, key);
        if (res == null){
            return null;
        }else{
            return res.value;
        }
    }
    public Node remove(Node x, Key key){
        if (x == null){
            return null;
        }
        if (key.compareTo(x.key) > 0){
            x.right = remove(x.right, key);
        }
        else if (key.compareTo(x.key) < 0){
            x.left = remove(x.left, key);
        }
        else {
            x = delete(x);

        }
        return x;
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
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
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
        return floor(root, key);
    }
    Key floor(Node x, Key key){
        if (x == null){
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x.key;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        Key rslt = floor(x.right, key);
        return (rslt == null) ? x.key : rslt;


    }

    @Override
    public Key ceil(Key key) {
        if (root == null){
            return null;
        }
        return ceil(root, key);
    }

    Key ceil(Node x, Key key){
        if (x == null){
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x.key;
        }
        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        Key rslt = ceil(x.left, key);
        return (rslt == null) ? x.key : rslt;

    }

    @Override
    public int size() {
        return size(root);
    }
    int size(Node x){
        if (x == null){
            return 0;
        }
        return size(x.left)+size(x.right)+1;
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
            x.height = Math.max(height(x.left), height(x.right))+1;
        }

    Node rotateRight(Node x){
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        fixHeight(x);
        fixHeight(left);
        return left;
    }
    Node rotateLeft(Node x){
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        fixHeight(x);
        fixHeight(right);

        return right;
    }
    int factor(Node x){
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
            if(factor(x.right) > 0){
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
