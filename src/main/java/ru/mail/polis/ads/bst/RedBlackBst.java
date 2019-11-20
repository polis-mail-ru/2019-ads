package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value>  {

    static final boolean RED = true;
    static final boolean BLACK = false;
    public Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node(Key key, Value value, int height, boolean color){
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }

    }
    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }


    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }
    Value get(Node x, Key key){
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
    public void put(@NotNull Key key, @NotNull Value value) {
        if (root == null){
            root = new Node(key, value, 1, BLACK);
            return;
        }
        root = put(root, key, value);
        root.color = BLACK;
    }
    Node put(Node x, Key key, Value value){
        if (x == null){
            return new Node(key, value, 1, RED);

        }
        if (key.compareTo(x.key) > 0){
            x.right = put(x.right, key, value);
        }
        else if (key.compareTo(x.key) < 0){
            x.left = put(x.left, key, value);
        }
        else{
            x.value = value;
        }
        x = fixUp(x);
        fixHeight(x);
        return x;
    }
    Node fixUp(Node x){
        if (isRed(x.right) && !isRed(x.left)){
            x = rotateLeft(x);
        }if (isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }if (isRed(x.right) && isRed(x.left)){
            flipColors(x);
        }
        return x;
    }
    Node rotateLeft(Node x){
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }
    Node rotateRight(Node x){
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }
    Node flipColors(Node x){
        x.color = !x.color;
        x.right.color = !x.right.color;
        x.left.color = !x.left.color;
        return x;
    }


    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value res = this.get(key);
        if (res == null){
            return null;
        }
        remove(root, key);
    }
    Node remove(Node x, Key key){

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
        if (x.left == null){
            if (x.right == null){
                x.height = height(x);
            }else {
                x.height = height(x.right)+1;
            }
        }else if (x.right == null){
            if (!isRed(x.left))
                x.height = height(x.left)+1;
            else{
                x.height = height(x);
            }
        }
        else{
            if (!isRed(x.left))
                x.height = Math.max(height(x.left), height(x.right))+1;
            else{
                x.height = height(x.right);
            }
        }
    }
}