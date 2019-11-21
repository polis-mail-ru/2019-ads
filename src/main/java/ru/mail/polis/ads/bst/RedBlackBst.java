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

    // For tests
    @Override
    public boolean isEmpty(){
        return root == null;
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    // Maim part
    private boolean isRed(Node node){
        return node != null && node.color == RED;
    }


    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;

        left.color = x.color;
        x.color = RED;

        fixHeight(x);
        fixHeight(left);

        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;

        right.color = x.color;
        x.color = RED;

        fixHeight(x);
        fixHeight(right);

        return right;
    }

    private void flipColors(Node node){
        node.color = !node.color;
        if (node.left != null) node.left.color = !node.left.color;
        if (node.right != null) node.right.color = !node.right.color;
    }

    private Node fixUp(Node node){
        if(isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);

        if(isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);


        if(isRed(node.left) && isRed(node.right))
           flipColors(node);

        return node;
    }


    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root,key);
        if(root == null) return null;
        if(node == null) return null;
        else return node.value;
//        return root == null ? null : get(root,key).value;
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

    private Node put(Node node,Key key, Value value){
        if(node==null)
            return new Node(key,value,RED);

        byte isKeyMoreNodeKey = (byte) key.compareTo(node.key);
        if (isKeyMoreNodeKey < 0)
            node.left = put(node.left,key,value);
        else if(isKeyMoreNodeKey > 0)
            node.right = put(node.right,key,value);
        else
            node.value = value;

        node = fixUp(node);

        fixHeight(node);

        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
            Value value = get(key);
            if(root != null){
                root = remove(root,key);
                return value;
            } else return null;
        }

        private Node remove(Node node, Key key) {
            if (node == null) return null;
            byte isKeyMoreNodeKey = (byte) key.compareTo(node.key);

            if (isKeyMoreNodeKey < 0) {
                // delete left snippet
                if (node.left != null) {
                    if (!isRed(node.left) && !isRed(node.left.left))
                        node = moveRedLeft(node);
                    node.left = remove(node.left, key);
                }

            } else if (isKeyMoreNodeKey > 0) {
                // delete right snippet
                if (node.right != null) {
                    if (isRed(node.left)) node = rotateRight(node);
                    if (!isRed(node.right) && !isRed(node.right.left))
                        node = moveRedRight(node);
                    node.right = remove(node.right, key);
                }
            } else {
                if (isRed(node.left)) node = rotateRight(node);
                if (node.right == null) {
                    if(node.left != null) return node.left;
                    else return null;
                }

                node.key = min(node.right).key;
                node.value = get(node.right, node.key).value;
                node.right = deleteMin(node.right);
            }

            return fixUp(node);
            }

            private void deleteMin() {
                root = deleteMin(root);
                root.color = BLACK;
                }

            void deleteMax() {
                root = deleteMax(root);
                root.color = BLACK;
                }

                private Node moveRedLeft(Node x) {
                    flipColors(x);
                    if ((x.right != null) && (isRed(x.right.left))) {
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

                private Node deleteMin(Node x) {
                    if (x.left == null)
                        return null;
                    if (!isRed(x.left) && !isRed(x.left.left))
                        x = moveRedLeft(x);
                    x.left = deleteMin(x.left);
                    return fixUp(x);
                    }

                private Node deleteMax(Node x) {
                        if (isRed(x.left)) x = rotateRight(x);
                        if (x.right == null) return null;
                        if (!isRed(x.right) &&!isRed(x.right.left))
                            x = moveRedRight(x);
                        x.right = deleteMax(x.right);
                        return fixUp(x);
                    }

    // Search min
    private Node min(Node node) {
        if (node == null) return null;
        while(node.left != null)
            node = node.left;
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
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = Math.max(height(x.left), height(x.right)) + 1;
    }
}
