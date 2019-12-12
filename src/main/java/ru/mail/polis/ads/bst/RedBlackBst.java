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
        boolean color;
        int heigth;

        Node (Key key, Value value, boolean color, int heigth){
            this.key = key;
            this.value = value;
            this.color = color;
            this.heigth = 1;
        }
    }

    public RedBlackBst(){
        root = null;
        size = 0;
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
        left.color = x.color;
        x.color = RED;
        return left;
    }


    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node fixUp(Node x){
        if (isRed(x.right) && !isRed(x.left)){
            x = rotateLeft(x);
        }

        if (isRed(x.left) && isRed(x.left.left)){
            x = rotateRight(x);
        }

        if (isRed(x.left) && isRed(x.right)){
            flipColors(x);
        }

        return x;
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

    private Node deleteMin(Node x){
        if (x.left == null){
            return null;
        }

        if (!isRed(x.left) && !isRed(x.left.left)){
            x = moveRedLeft(x);
        }

        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node moveRedRight(Node x){
        flipColors(x);
        if (isRed(x.left.left)){
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    public void deleteMax(){
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node x){
        if (isRed(x.left)){
            x = rotateRight(x);
        }

        if (x.right == null){
            return null;
        }

        if (!isRed(x.right) && !isRed(x.right.left)){
            x = moveRedRight(x);
        }

        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    private Node delete(Node x, Key key){

        if (x == null){
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = delete(x.left, key);
        }

        else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }

            if ((x.right == null) && key.compareTo(x.key) == 0) {
                return null;
            }

            if (!isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if
            (key.compareTo(x.key) == 0) {
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = deleteMin(x.right);
            }

            else {
                x.right = delete(x.right, key);
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key){
        if (x == null){
            return null;
        }

        if (key.compareTo(x.key) > 0){
            return get(x.right, key);
        }

        else if (key.compareTo(x.key) < 0){
            return get(x.left, key);
        }

        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value){
        if (x == null){
            size++;
            return new Node(key, value, RED, 1);
        }

        if (key.compareTo(x.key) < 0){
            x.left = put(x.left, key, value);
        }

        else if (key.compareTo(x.key) > 0){
            x.right = put(x.right, key, value);
        }

        else {
            x.value = value;
        }

        x = fixUp(x);
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (!isEmpty()) {
            root.color = BLACK;
        }

        if (!containsKey(key)){
            return null;
        }

        Value value = get(key);

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);
        size--;

        if (root != null) {
            root.color = BLACK;
        }
        return value;
    }

    @Nullable
    @Override
    public Key min() {
        Node x = min(root);
        return (x == null) ? null : x.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }

        if (x.left == null) {
            return x;
        }

        return min(x.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        Node x = min(root);
        return (x == null) ? null : x.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node x = max(root);
        return (x == null) ? null : x.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }

        if (x.right == null) {
            return x;
        }

        return max(x.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node x = max(root);
        return (x == null) ? null : x.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (isEmpty()){
            return null;
        }
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key){
        if (x == null){
            return null;
        }

        if (key.compareTo(x.key) < 0){
            return floor(x.left, key);
        }

        else if (key.compareTo(x.key) == 0){
            return x;
        }

        return floor(x.right, key) != null ? floor(x.right, key) : x;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (isEmpty()){
            return null;
        }
        Node x = ceil(root, key);
        return (x == null) ? null : x.key;
    }

    private Node ceil(Node x, Key key){
        if (x == null){
            return null;
        }

        if (key.compareTo(x.key) == 0){
            return x;
        }

        else if (key.compareTo(x.key) > 0){
            return ceil(x.right, key);
        }

        return ceil(x.left, key) != null ? ceil(x.left, key) : x;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x){
        return (x == null) ? 0 : (size(x.left) + size(x.right) + 1);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x){
        return (x == null) ? 0 : x.heigth;
    }
}
