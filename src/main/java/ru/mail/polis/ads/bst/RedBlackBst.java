package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node node;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, boolean color, int height) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {
        return get(node, key);
    }

    @Override
    public void put(Key key, Value value) {
        node = put(node, key, value);
        node.color = BLACK;
    }

    @Override
    public Value remove(Key key) {
        Value remove = get(node, key);
        node = remove(node, key);
        return remove;
    }

    @Override
    public Key min() {
        return min(node);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return max(node);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(Key key) {
        return floor(node, key, null);
    }

    @Override
    public Key ceil(Key key) {
        return ceil(node, key, null);
    }

    @Override
    public int size() {
        return size(node);
    }

    @Override
    public int height() {
        return height(node);
    }

    private Value get(Node node, Key key) {
        if (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                return get(node.left, key);
            } else if (compare > 0) {
                return get(node.right, key);
            } else {
                return node.value;
            }
        }
        return null;
    }

    private Node put(Node node, Key key, Value value) {
        if (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else if (compare > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }
            setNewHeight(node);
            return fixedNode(node);
        }
        return new Node(key, value, RED, 1);
    }

    private Node moveRedLeft(Node node) {
        invertColor(node);
        if (node.right != null && isRed(node.right.left)) {
            node.right = rotateNodeRight(node.right);
            node = rotateNodeLeft(node);
            invertColor(node);
        }
        return node;
    }

    private Node deleteMinNode(Node node) {
        if (node.left != null) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = deleteMinNode(node.left);
            return fixedNode(node);
        }
        return null;
    }

    private Node moveRedRight(Node node) {
        invertColor(node);
        if (node.left != null && isRed(node.left.left)) {
            node = rotateNodeRight(node);
            invertColor(node);
        }
        return node;
    }

    private Node remove(Node node, Key key) {
        if (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                if (node.left != null) {
                    if (!isRed(node.left) && !isRed(node.left.left)) {
                        node = moveRedLeft(node);
                    }
                    node.left = remove(node.left, key);
                }
            } else if (compare > 0) {
                if (node.right != null) {
                    if (isRed(node.left)) {
                        node = rotateNodeRight(node);
                    }
                    if (!isRed(node.right) && !isRed(node.right.left)) {
                        node = moveRedRight(node);
                    }
                    node.right = remove(node.right, key);
                }
            } else {
                Node remove = node;
                if (isRed(node.left)) {
                    node = rotateNodeRight(node);
                    remove = node.right;
                }
                if (remove.right == null) {
                    if (remove.left == null) {
                        return null;
                    }
                    return remove.left;
                }
                remove.key = min(remove.right);
                remove.value = get(remove.right, remove.key);
                remove.right = deleteMinNode(remove.right);
            }
            return fixedNode(node);
        }
        return null;
    }

    private Key min(Node node) {
        if (node != null) {
            if (node.left == null) {
                return node.key;
            }
            return min(node.left);
        }
        return null;
    }

    private Key max(Node node) {
        if (node != null) {
            if (node.right != null) {
                return max(node.right);
            }
            return node.key;
        }
        return null;
    }

    private Key floor(Node node, Key key, Key max) {
        if (node != null) {
            int compare = key.compareTo(node.key);
            if (compare < 0) {
                max = floor(node.left, key, max);
            } else if (compare > 0) {
                if (max == null || max.compareTo(node.key) < 0) {
                    max = node.key;
                }
                max = floor(node.right, key, max);
            } else {
                max = node.key;
            }
        }
        return max;
    }

    private Key ceil(Node node, Key key, Key min) {
        if (node != null) {
            int compare = key.compareTo(node.key);
            if (compare > 0) {
                min = ceil(node.right, key, min);
            } else if (compare < 0) {
                if (min == null || min.compareTo(node.key) > 0) {
                    min = node.key;
                }
                min = ceil(node.left, key, min);
            } else {
                min = node.key;
            }
        }
        return min;
    }

    private int size(Node node) {
        return node != null ? size(node.left) + size(node.right) + 1 : 0;
    }

    private int height(Node node) {
        return node != null ? node.height : 0;
    }

    private Node fixedNode(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateNodeLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateNodeRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            invertColor(node);
        }
        return node;
    }

    private void setNewHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateNodeRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private Node rotateNodeLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node invertColor(Node node) {
        node.color = !node.color;
        if (node.left != null ) {
            node.left.color = !node.left.color;
        }
        if (node.right != null) {
            node.right.color = !node.right.color;
        }
        return node;
    }
}