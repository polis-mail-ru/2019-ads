package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;
        Node (Key key, Value value, int height, boolean color){
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root,key);
    }

    private Value get(Node x, Key key){
        if (x == null)
            return null;
        int comp = key.compareTo(x.key);
        if (comp > 0)
            return get(x.right, key);
        if (comp < 0)
            return get(x.left, key);
        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value){
        if (x == null) {
            size++;
            return new Node(key, value, 1, RED);
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, value);
        } else if (comp > 0) {
            x.right = put(x.right,key,value);
        } else {
            x.value = value;
        }
        x = fixUp(x);
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (!containsKey(key)) {
            return null;
        }
        Value del = get(key);
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = remove(root, key);
        if (root != null) {
            root.color = BLACK;
        }
        return del;

    }

    private Node remove(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = remove(x.left, key);
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }
            if (key.compareTo(x.key) == 0 && (x.right == null)) {
                return null;
            }
            if (!isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if (key.compareTo(x.key) == 0) {
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = deleteMin(x.right);
            } else {
                x.right = remove(x.right, key);
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        return  root == null ? null : min(root).key;
    }

    private Node min(Node x){
        if (x.left != null)
            return min(x.left);
        return x;
    }

    @Nullable
    @Override
    public Value minValue() {
        return (root == null) ? null : min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    private Node max(Node x) {
        if (x.right != null)
            return max(x.right);
        return x;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (size == 0) return null;
        return (floor(root, key) == null) ? null : floor(root, key).key;
    }

    private Node floor(Node x, Key key){
        if (x == null)
            return null;
        int comp = key.compareTo(x.key);
        if (comp < 0)
            return floor(x.left, key);
        if (comp == 0)
            return x;
        return floor(x.right, key) != null ? floor(x.right, key) : x;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (size == 0) return null;
        return (ceil(root, key) == null) ? null : ceil(root, key).key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null)
            return null;
        int comp = key.compareTo(x.key);
        if (comp == 0)
            return x;
        if (comp > 0)
            return ceil(x.right, key);
        Node node = ceil(x.left, key);
        return node != null ? node : x;
    }

    @Override
    public int size() {
        return root == null ? 0 : size(root);
    }

    private int size(Node x){
        return x == null ? 0 : size(x.left) + size(x.right) + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private boolean isRed(Node x){
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x){
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }

    private Node rotateRight(Node x){
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color=x.color;
        x.color = RED;
        return left;
    }

    private void flipColors(Node x){
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node fixUp(Node x){
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    private Node deleteMin(Node x){
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node moveRedLeft(Node x){
        flipColors(x);
        if (isRed(x.right.left)){
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x){
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }
}