package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    
    private class Node implements Cloneable {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    // node on the top of heap
    private Node topNode;
    private int size;

    AvlBst() {
        this.size = 0;
    }

    @Override
    public Value get(Key key) {
        if (topNode == null)
            return null;
        Node node = get(key, topNode);
        return node != null ? node.value : null;
    }

    private Node get(Key key, Node node) {

        if (key.compareTo(node.key) > 0) {
            return node.right != null ? get(key, node.right) : null;
        } else if (key.compareTo(node.key) < 0) {
            return node.left != null ? get(key, node.left) : null;
        } else {
            return node;
        }
    }

    @Override
    public void put(Key key, Value value) {
        if (topNode == null) {
            topNode = new Node(key, value, 1);
            size++;
            return;
        }
        topNode = put(key,value, topNode);
    }

    private Node put(Key key, Value value, Node node) {

        if (key.compareTo(node.key) > 0) {
            if (node.right != null) node.right = put(key, value, node.right);
            else {
                node.right = new Node(key, value, 1);
                size++;
            }
        } else if (key.compareTo(node.key) < 0) {
            if (node.left != null) node.left = put(key, value, node.left);
            else {
                node.left = new Node(key, value, 1);
                size++;
            }
        } else {
            node.value = value;
        }

        fixHeight(node);
        return balance(node);
    }


    @Override
    public void remove(Key key) {
        if (topNode != null) topNode = remove(key, topNode);
    }

    private Node remove(Key key, Node node) {
        if (key.compareTo(node.key) > 0) {
            if (node.right != null)
                node.right = remove(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            if (node.left != null)
                node.left = remove(key, node.left);
        } else {
            node =  deleteNode(node);
        }

        if (node != null) {
            fixHeight(node);
            node = balance(node);
        }
        return node;
    }

    private Node deleteNode(Node node) {
        if (node.right == null && node.left == null) {
            node = null;
        }
        else if (node.right != null && node.left == null) {
            node = node.right;
        }
        else if (node.right == null) {
            node = node.left;
        }
        else {
            Node minNodeOfRightChild = min(node.right);
            Key key = minNodeOfRightChild.key;
            Value value = minNodeOfRightChild.value;
            remove(minNodeOfRightChild.key);
            node.key = key;
            node.value = value;
            // size-- уже выполнялся в remove(minNodeOfRightChild.key)
            return node;
        }
        size--;
        return node;
    }

    @Override
    public Key min() {
        if (topNode == null) return null;
        Node node = min(topNode);
        return node.key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    @Override
    public Value minValue() {
        Key key = min();
        return get(key);
    }

    @Override
    public Key max() {
        if (topNode == null) return null;
        Node node = max(topNode);
        return node.key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    @Override
    public Value maxValue() {
        Key key = max();
        return get(key);
    }

    @Override
    public Key floor(Key key) {
        if (topNode == null) return null;
        Node node = get(key, topNode);
        if (node == null) return null;

        return max(node.left).key;
    }

    @Override
    public Key ceil(Key key) {
        if (topNode == null) return null;
        Node node = get(key, topNode);
        if (node == null) return null;

        return max(node.right).key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return topNode != null ? topNode.height : 0;
    }

    private void fixHeight(Node node) {
        if (node.right == null && node.left != null)
            node.height = node.left.height + 1;
        else if (node.right != null && node.left == null)
            node.height = node.right.height + 1;
        else if (node.right == null)
            node.height = 1;
        else
            node.height = Math.max(node.left.height, node.right.height) + 1;
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = spinLeft(node.left);
            }
            node = spinRight(node);
        }

        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = spinRight(node.right);
            }
            node = spinLeft(node);
        }

        return node;
    }

    private int factor(Node node) {
        if (node.right == null && node.left != null) return node.left.height;
        else if (node.right != null && node.left == null) return -node.right.height;
        else if (node.right == null) return 0;
        else return node.left.height - node.right.height;
    }

    private Node spinRight(Node node) {
        Node a = node;
        Node c = node.left.right;
        node = node.left;
        node.right = a;
        node.right.left = c;

        fixHeight(node.right);
        fixHeight(node);
        return node;
    }

    private Node spinLeft(Node node) {

        Node a = node;
        Node c = node.right.left;
        node = node.right;
        node.left = a;
        node.left.right = c;

        fixHeight(node.left);
        fixHeight(node);
        return node;
    }

}
