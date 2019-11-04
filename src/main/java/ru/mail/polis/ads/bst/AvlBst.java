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
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
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
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
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
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (node.left != null && node.right != null) {
            node.key = min(node).key;
            node.right = remove(node.right, node.key);
        } else {
            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        fixHeight(node);
        balance(node);
        return node;
    }

    @Override
    public Key min() {
        Node minNode = min(node);
        return minNode == null ? null : minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = min(node);
        return minNode == null ? null : minNode.value;
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
    public Key max() {
        Node maxNode = max(node);
        return maxNode == null ? null : maxNode.key;
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(node);
        return maxNode == null ? null : maxNode.value;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node);
    }

    @Override
    public Key floor(Key key) {
        return floor(node, key);
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
        return ceil(node, key);
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
        return size(node);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    @Override
    public int height() {
        return height(node);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
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

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
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
