package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.height = 1;
        }
    }

    private Node root;

    private boolean isRed(Node node)
    {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.height = 1 + Math.max(node.left == null ? 0 : (node.left).height,
            node.right == null ? 0 : (node.right).height);
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }


    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        --size;
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return findNode(root, key);
    }
    
    private Value findNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return findNode(node.left, key);

        } if (cmp > 0) {
            return findNode(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key,@NotNull Value value){
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value){
        if (node == null) {
            ++size;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
           node.value = value;
        }
         node = fixUp(node);
        return node;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (root == null) {
            return null;
        }

        if (!containsKey(key)) {
            return null;
        }

        Value result = get(key);

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = remove(root, key);

        if (root != null) {
            root.color = BLACK;
        }
        --size;
        return result;
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
        Node min = min(root);
        return (min == null) ? null : min.key;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return (min == null) ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(root, key);
        return (result == null) ? null : result.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return floor(node.left, key);
        }
        if (cmp == 0) {
            return node;
        }
       Node right = floor(node.right, key);
        return (right == null) ? node : right;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node result= ceil(root, key);

        return (result == null) ? null : result.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node;
        }

        if (cmp > 0) {
            return ceil(node.right, key);
        }

        Node left = ceil(node.left, key);

        return (left == null) ? node : left;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }
}
