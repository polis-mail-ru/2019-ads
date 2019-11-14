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
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height = 1;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private boolean isRed(Node node){
        return node != null && node.color == RED;
    }


    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;

        left.color = x.color;
        x.color = RED;

        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;

        right.color = x.color;
        x.color = RED;

        return right;
    }

    private void flipColors(Node node){
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node fixUp(Node node){
        if(isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if(isRed(node.left) && !isRed(node.left.left))
            node = rotateRight(node);

        if(isRed(node.left) && !isRed(node.right))
           flipColors(node);

        return node;
    }

    private Node put(Node node,Key key, Value value){
        if(node==null)
            return new Node(key,value,RED);

        if (key.compareTo(node.key) < 0)
            node.left = put(node.left,key,value);
        else if(key.compareTo(node.key) > 0)
            node.right = put(node.right,key,value);
        else
            node.value = value;

        node = fixUp(node);
        return node;
    }
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return root == null ? null : get(root,key).value;
    }

    private Node get(Node node, Key key) {
        while(node != null){
            if(node.key == key) return node;
            else if (key.compareTo(node.key) > 0) node = node.right;
            else node = node.left;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root,key,value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    // Search min
    private Node min(Node node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null? null: min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null? null: min(root).value;
    }

    // Search max
    private Node max(Node node) {
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null? null: max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null? null: max(root).value;
    }


    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return root == null? null: floor(root,key);
    }

    private Key floor(Node node, Key key){
        Key min = null;
        while(node != null){
            if (key.compareTo(node.key) == 0) return node.key;
            else if (key.compareTo(node.key) > 0) {
                if ((node.right == null) ||
                        (node.right.key.compareTo(key) > 0)){
                    min = node.key;
                }
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                node = node.left;
            }
        }
        return min;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return root == null? null: ceil(root,key);
    }

    private Key ceil(Node node, Key key){
        // Search value
        Key min = null;

        while(node != null){
            if (key.compareTo(node.key) == 0) return node.key;
            else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                if ((node.left == null) ||
                        (node.left.key.compareTo(key) < 0)){
                    min = node.key;
                }
                node = node.left;
            }
        }
        return min;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.color != RED?
                        1 + size(node.left) + size(node.right):
                        size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    // Height block
    private int height(Node x) {
        return x == null ? 0 : x.height;
    }
}
