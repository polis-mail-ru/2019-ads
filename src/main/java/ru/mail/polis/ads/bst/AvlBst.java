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
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private boolean deleted = false;

    @Override
    public Value remove(Key key) {
        Value val = null;
        if (size == 1) {
            if (root.key == key) {
                val = root.value;
                root = null;
                size--;
            }
        } else {
            Node remNode = remove(root, key);
            val = remNode.value;
            root = remove(root, key);
        }
        deleted = false;
        return val;
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
            if (node.left != null && node.right != null) {
                Node xNode = node;
                node = min(xNode.right);
                node.right = deleteMin(xNode.right);
                node.left = xNode.left;
            } else if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        fixHeight(node);
        balance(node);
        return node;
    }

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
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.right.key) > 0) {
            return floor(node.right, key);
        }
        return node.key;
    }

    @Override
    public Key ceil(Key key) {
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.left.key) < 0) {
            return ceil(node.left, key);
        }
        return node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return size == 0 ? 0 : root.height;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private void fixHeight(Node node) {
        if (size == 0 || node == null) return;
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (size == 0 || node == null) return null;
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }
}
