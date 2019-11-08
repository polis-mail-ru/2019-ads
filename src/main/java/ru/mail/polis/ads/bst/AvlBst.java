package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {
    //my
    private Node root;
    private int size;
    //end my

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    //my
    AvlBst() {
        root = null;
        size = 0;
    }

    private Value get(Node node, Key key) {
        if (node == null)
            return null;
        if(key.compareTo(node.key) < 0)
            get(node.left, key);
        else if (key.compareTo(node.key) > 0)
            get(node.right, key);

        return node.value;
    }
    //end my
    @Override
    public Value get(Key key) {
        return get(root, key);
    }
    //my

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);

        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);

        return right;
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node.right);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node.left);
        }

        return node;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.right), height(node.left));
    }

    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            ++size;
            return new Node(key, value, 1);
        }

        if (key.compareTo(node.key) < 0)
            node.left = put(key, value, node.left);
        else if (key.compareTo(node.key) > 0)
            node.right = put(key, value, node.right);
        else
            node.value = value;
        fixHeight(node);
        node = balance(node);

        return node;
    }
    //endmy

    @Override
    public void put(Key key, Value value) {
        root = put(key, value, root);
    }

    //my
    private Node min(Node node) {
        if (node == null)
            return null;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null)
            return node.right;
        node.left = deleteMin(node.left);

        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null)
            return node.left;
        if (node.left == null)
            return node.right;
        Node tmp_node = node;
        node = min(tmp_node.right);
        node.right = deleteMin(tmp_node.right);
        node.left = tmp_node.left;

        --size;
        return node;
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0)
            node.left = delete(node.left, key);
        if (key.compareTo(node.key) > 0)
            node.right = delete(node.right, key);
        if (key.compareTo(node.key) == 0)
            node = innerDelete(node);

        return node;
    }
    //enfmy
    @Override
    public void remove(Key key) {
        root = delete(root, key);
    }

    @Override
    public Key min() {
        if (root == null)
            return null;

        Node tmp_node;
        tmp_node = root;
        while (tmp_node.left != null) {
            tmp_node = tmp_node.left;
        }

        return tmp_node.key;
    }

    @Override
    public Value minValue() {
        if (root == null)
            return null;

        Node tmp_node;
        tmp_node = root;
        while (tmp_node.left != null) {
            tmp_node = tmp_node.left;
        }

        return tmp_node.value;
    }

    @Override
    public Key max() {
        if (root == null)
            return null;

        Node tmp_node;
        tmp_node = root;
        while (tmp_node.right != null) {
            tmp_node = tmp_node.right;
        }

        return tmp_node.key;
    }

    @Override
    public Value maxValue() {
        if (root == null)
            return null;

        Node tmp_node;
        tmp_node = root;
        while (tmp_node.right != null) {
            tmp_node = tmp_node.right;
        }

        return tmp_node.value;
    }
    //my
    private Node getNode(Node node, Key key) {
        if (node == null)
            return null;
        if(key.compareTo(node.key) < 0)
            get(node.left, key);
        else if (key.compareTo(node.key) > 0)
            get(node.right, key);

        return node;
    }
    //endmy
    @Override
    public Key floor(Key key) {
        Node tmp_node = getNode(root, key);
        if (tmp_node == null || tmp_node.left == null)
            return null;
        tmp_node = tmp_node.left;
        while (tmp_node.right != null)
            tmp_node = tmp_node.right;

        return tmp_node.key;
    }

    @Override
    public Key ceil(Key key) {
        Node tmp_node = getNode(root, key);
        if (tmp_node == null || tmp_node.right == null)
            return null;
        tmp_node = tmp_node.right;
        while (tmp_node.left != null)
            tmp_node = tmp_node.left;

        return tmp_node.key;
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
