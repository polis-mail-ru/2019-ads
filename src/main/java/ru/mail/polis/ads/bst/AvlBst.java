package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
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

    private Node node;

    @Override
    public Value get(Key key) {
        return get(node, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return get(node.right, key);
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return node.value;

        }
    }

    @Override
    public void put(Key key, Value value) {
        node = put(node, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    @Override
    public Value remove(Key key) {
        return remove(node, key).value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = remove(node.right, key);
        } else if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (node.left != null && node.right != null) {
            node.key = min(node).key;
            node.right = remove(node.right, node.key);
        } else {
            if (node.left == null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        fixHeight(node);
        balance(node);
        return node;
    }

    @Override
    public Key min() {
        Node minNode = min(node);
        if (minNode == null) {
            return null;
        }
        return minNode.key;
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

    @Override
    public Value minValue() {
        Node minNode = min(node);
        if (minNode == null) {
            return null;
        }
        return minNode.value;
    }

    @Override
    public Key max() {
        Node maxNode = max(node);
        if (maxNode == null) {
            return null;
        }
        return maxNode.key;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return null;
        }
        return max(node);
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(node);
        if (maxNode == null) {
            return null;
        }
        return maxNode.value;
    }

    @Override
    public Key floor(Key key) {
        return floor(node, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return floor(node.right, key);
        } else if (cmp < 0) {
            return floor(node.left, key);
        } else {
            return node.key;
        }
    }

    @Override
    public Key ceil(Key key) {
        return ceil(node, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return ceil(node.right, key);
        } else if (cmp > 0) {
            return ceil(node.left, key);
        } else {
            return node.key;
        }
    }

    @Override
    public int size() {
        return size(node);
    }

    private int size(Node node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(node);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int balanceDiff(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (balanceDiff(node) > 1) {
            if (balanceDiff(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
        }
        if (balanceDiff(node) < -1) {
            if (balanceDiff(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(left);
        fixHeight(node);
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

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }
}
