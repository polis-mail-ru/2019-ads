package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        //my
        public Node(Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
        //end my
    }

    //my
    private Node root;
    private int size;

    RedBlackBst() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft (Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;

        fixHeight(x);
        fixHeight(right);

        return right;
    }

    private Node rotateRight (Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;

        fixHeight(x);
        fixHeight(left);

        return left;
    }

    private void flipColor(Node x) {
        x.color = !x.color;
        if (x.left != null) {
            x.left.color = !x.left.color;
        }
        if (x.right != null) {
            x.right.color = !x.right.color;
        }
    }

    private Node fixUp (Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (x.left != null) {
            if (isRed(x.left) && isRed(x.left.left)) {
                x = rotateRight(x);
            }
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColor(x);
        }

        return x;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.right), height(node.left));
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, 1, RED);
        }

        final int keyCompare = key.compareTo(x.key);

        if (keyCompare < 0)
            x.left = put(x.left, key, value);
        else if (keyCompare > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;

        x = fixUp(x);
        fixHeight(x);

        return x;
    }

    private Node getNode(Node x, Key key) {
        if (x == null)
            return null;

        final int keyCompare = key.compareTo(x.key);
        if(keyCompare < 0)
            return getNode(x.left, key);
        else if (keyCompare > 0)
            return getNode(x.right, key);

        return x;
    }
    //endmy

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node answ = getNode(root, key);
        return answ == null ? null : answ.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    //my
    private Node moveRedLeft(Node x) {
        flipColor(x);
        if (x.right != null) {
            if (isRed(x.right.left)) {
                x.right = rotateRight(x.right);
                x = rotateLeft(x);
                flipColor(x);
            }
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColor(x);
        if (x.left != null) {
            if (isRed(x.left.right)) {
                x = rotateRight(x);
                flipColor(x);
            }
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);

        return fixUp(x);
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }

        Node tmp_node = x;
        while (tmp_node.left != null) {
            tmp_node = tmp_node.left;
        }

        return tmp_node;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        final int keyCompare = key.compareTo(x.key);
        if (keyCompare < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else if (keyCompare > 0) {
            if (x.right != null) {
                if (isRed(x.left)) {
                    x = rotateRight(x);
                }
                if (!isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                x.right = delete(x.right, key);
            }
        } else {
            Node deleted = x;
            if (isRed(x.left)) {
                x = rotateRight(x);
                deleted = x.right;
            }
            if (deleted.right == null) {
                if (deleted.left != null) {
                    return deleted.left;
                } else {
                  return null;
                }
            }
            deleted.key = minNode(deleted.right).key;
            deleted.value = getNode(deleted.right, deleted.key).value;
            deleted.right = deleteMin(deleted.right);
        }
        return fixUp(x);
    }
    //endmy
    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node deletedNode = getNode(root, key);
        if (deletedNode == null) {
            return null;
        }
        Value deletedValue = deletedNode.value;
        root = delete(root, key);
        --size;
        return deletedValue;

    }

    @Nullable
    @Override
    public Key min() {
        Node min = minNode(root);
        return min == null ? null : min.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = minNode(root);
        return min == null ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null)
            return null;

        Node tmp_node = root;
        while (tmp_node.right != null) {
            tmp_node = tmp_node.right;
        }

        return tmp_node.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null)
            return null;

        Node tmp_node = root;
        while (tmp_node.right != null) {
            tmp_node = tmp_node.right;
        }

        return tmp_node.value;
    }
    //my
    private Node floor(Node x, Key key) {
        if (x == null)
            return null;

        final int keyComp = key.compareTo(x.key);

        if (keyComp == 0)
            return x;

        if (keyComp < 0)
            return floor(x.left, key);

        final Node answ = floor(x.right, key);
        return answ == null ? x : answ;
    }
    //endmy
    @Nullable
    @Override
    public Key floor(Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }
    //my
    private Node ceil(Node x, Key key) {
        if (x == null)
            return null;

        final int keyComp = key.compareTo(x.key);

        if (keyComp == 0)
            return x;

        if (keyComp > 0)
            return ceil(x.right, key);

        final Node answ = ceil(x.left, key);
        return answ == null ? x : answ;
    }
    //endmy
    @Nullable
    @Override
    public Key ceil(Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public int size() {
        return size;
    }
    //my
    int height(Node x) {
        return x == null ? 0 : x.height;
    }
    //endmy
    @Override
    public int height() {
        return height(root);
    }
}
