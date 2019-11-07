package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private int size;

    public AvlBst() {
        root = null;
        size = 0;
    }

    @Override
    public Value get(Key key) {
        final Node result = find(root, key);
        return result == null ? null : result.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    @Override
    public void remove(Key key) {
        root = remove(root, key);
    }

    @Override
    public Key min() {
        final Node minNode = minNode(root);
        return minNode == null ? null : minNode.key;
    }

    @Override
    public Value minValue() {
        final Node minNode = minNode(root);
        return minNode == null ? null : minNode.value;
    }

    @Override
    public Key max() {
        final Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.key;
    }

    @Override
    public Value maxValue() {
        final Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Override
    public Key floor(Key key) {
        final Node node = floor(root, key);
        return (node == null) ? null : node.key;
    }

    @Override
    public Key ceil(Key key) {
        final Node node = ceil(root, key);
        return (node == null) ? null : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }
    }

    private Node rightOrNull(Node node) {
        return (node == null) ? null : node.right;
    }

    private Node leftOrNull(Node node) {
        return (node == null) ? null : node.left;
    }

    private Node find(Node current, Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            return find(current.left, key);
        } else if (comp < 0) {
            return find(current.right, key);
        }

        return current;
    }

    private Node floor(final Node current, final Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            return floor(current.left, key);
        }

        final Node result = floor(current.right, key);
        return (result == null) ? current : result;
    }

    private Node ceil(final Node current, final Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp < 0) {
            return ceil(current.right, key);
        }

        final Node result = ceil(current.left, key);
        return (result == null) ? current : result;
    }

    private Node put(Node current, Key key, Value value) {
        if (current == null) {
            ++size;
            return new Node(key, value);
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            current.left = put(current.left, key, value);
        } else if (comp < 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }

        updateHeight(current);
        current = balance(current);
        return current;
    }

    private Node balance(Node current) {
        final int deltaHeight = height(current.left) - height(current.right);
        if (deltaHeight == 2) {
            final int deltaHeightLeft = height(leftOrNull(current.left)) - height(rightOrNull(current.left));
            if (deltaHeightLeft < 0) {
                current.left = rotateLeft(current.left);
                updateHeight(current);
            }
            return rotateRight(current);
        }
        if (deltaHeight == -2) {
            final int deltaHeightRight = height(leftOrNull(current.right)) - height(rightOrNull(current.right));
            if (deltaHeightRight > 0) {
                current.right = rotateRight(current.right);
                updateHeight(current);
            }
            return rotateLeft(current);
        }
        return current;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node rotateRight(Node current) {
        Node left = current.left;
        current.left = left.right;
        left.right = current;

        updateHeight(current);
        updateHeight(left);

        return left;
    }

    private Node rotateLeft(Node current) {
        Node right = current.right;
        current.right = right.left;
        right.left = current;

        updateHeight(current);
        updateHeight(right);

        return right;
    }

    private Node remove(Node current, Key key) {
        if (current == null) {
            return null;
        }

        final int comp = current.key.compareTo(key);
        if (comp > 0) {
            current.left = remove(current.left, key);
        } else if (comp < 0) {
            current.right = remove(current.right, key);
        } else {
            --size;
            if (current.right == null) {
                current = current.left;
                if (current == null) {
                    return null;
                }
            } else {
                final Node min = minNode(current.right);
                current.right = deleteMin(current.right);
                min.right = current.right;
                min.left = current.left;
                current = min;
            }
        }

        updateHeight(current);
        current = balance(current);

        return current;
    }

    private Node minNode(final Node current) {
        if (current == null) {
            return null;
        }

        Node node = current;
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private Node deleteMin(Node current) {
        if (current.left == null) {
            return current.right;
        }
        current.left = deleteMin(current.left);
        return current;
    }

    private Node maxNode(final Node current) {
        if (current == null) {
            return null;
        }

        Node node = current;
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
