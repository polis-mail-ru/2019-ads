package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;
    private class Node {
        Key key;
        Value value;
        Node left, right;
        boolean color;
        int size;

        Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    private boolean isRed(Node x){
        return x != null && x.color == RED;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.value = val;

        return balance(x);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node balance(Node x) {
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (!containsKey(key)) return null;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        Value deleted = get(key);
        root = remove(root, key);
        if (!isEmpty()) root.color = BLACK;
        return deleted;
    }

    private Node remove(Node h, Key key) {
        if (h == null) return null;
        if (key.compareTo(h.key) < 0)  {
            if (h.left != null) {
                if (!isRed(h.left) && !isRed(h.left.left))
                    h = moveRedLeft(h);
                h.left = remove(h.left, key);
            }
        }
        else {
            if (isRed(h.left)) {
                h = rotateRight(h);
                h.right = remove(h.right, key);
            }
            if (key.compareTo(h.key) == 0 && h.right == null)
                return null;
            if (h.right != null && !isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                Node removeMin = deleteMin(h.right);
                h.key = x.key;
                h.value = x.value;
                h.right = removeMin;
            }
            else h.right = remove(h.right, key);
        }
        return balance(h);
    }

    @Nullable
    @Override
    public Key min() {
        return isEmpty() ? null : min(root).key;
    }
    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        return isEmpty() ? null : min(root).value;
    }

    public void deleteMin(){
        root = deleteMin(root);
        Objects.requireNonNull(root).color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    @Nullable
    @Override
    public Key max() {
        return isEmpty() ? null : max(root).key;
    }
    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        return isEmpty() ? null : max(root).value;
    }

    public void deleteMax(){
        root = deleteMax(root);
        Objects.requireNonNull(root).color = BLACK;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (isEmpty()) return null;
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node f = floor(x.right, key);
        return f != null ? f : x;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (isEmpty()) return null;
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0)  return ceiling(x.right, key);
        Node c = ceiling(x.left, key);
        return c != null ? c : x;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    @Override
    public int height() {
        return height(root);
    }
    private int height(Node x) {
        return x == null ? 0 : 1 + Math.max(height(x.left), height(x.right));
    }
}