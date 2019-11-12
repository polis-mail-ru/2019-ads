package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Value get(Key key) {
        if (size == 0) return null;
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0)
            return (get(node.left, key));
        if (key.compareTo(node.key) > 0)
            return (get(node.right, key));
        return node.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        size++;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) < 0)
            node.left = put(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = put(node.right, key, value);
        else {
            node.value = value;
        }
        /*fixHeight(node);
        node = balance(node);*/
        return node;
    }

    private boolean deleted = false;

    @Override
    public void remove(Key key) {
        if (size == 1) {
            if (root.key == key) {
                root = null;
                size--;
            }
        } else {
            root = remove(root, key);
        }
        deleted = false;
    }


    private Node remove(Node node, Key key) {
        if (node == null) return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (!deleted) {
            deleted = true;
            size--;
            if (node.left != null && node.right != null){
                Node xNode = node;
                node = min(xNode.right);
                node.right = deleteMin(xNode.right);
                node.left = xNode.left;
            }
            else if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
            /*fixedNodeHeight(node);
            return balanceNode(node);*/
        return node;
    }

    /*private Node innerDelete(Node node) {
        if (node.right == null) {
            node = node.left;
            return null;
        }
        if (node.left == null) {
            node = node.right;
            return null;
        }
        Node xNode = node;
        node = min(xNode.right);
        node.right = deleteMin(xNode.right);
        node.left = xNode.left;
        return node;
    }*/

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;

        node.left = deleteMin(node.left);

        return node;
    }

    @Override
    public Key min() {
        if (size == 0) return null;
        Node foundNode = min(root);
        return foundNode.key;
    }

    private Node min(Node node) {
        if (node == null) return null;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }


    @Override
    public Value minValue() {
        if (size == 0) return null;
        Node foundNode = min(root);
        return foundNode.value;
    }

    @Override
    public Key max() {
        if (size == 0) return null;
        Node foundNode = max(root);
        return foundNode.key;
    }

    private Node max(Node node) {
        if (node == null) return null;

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    @Override
    public Value maxValue() {
        if (size == 0) return null;
        Node foundNode = max(root);
        return foundNode.value;
    }

    @Override
    public Key floor(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key ceil(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
