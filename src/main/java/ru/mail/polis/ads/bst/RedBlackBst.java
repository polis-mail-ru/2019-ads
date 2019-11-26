package ru.mail.polis.ads.bst;

public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node root;
    private Node removed;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;
        private boolean color;

        private Node(Key key, Value value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.color = x.color;
        x.color = RED;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        return y;
    }

    private void flipColours(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1, BLACK);
        }
        int tmp = key.compareTo(x.key);
        if (tmp > 0) {
            x.right = put(x.right, key, value);
        } else if (tmp < 0) {
            x.left = put(x.left, key, value);
        } else {
            x.value = value;
        }

        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColours(x);
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Value get(Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        Node tmp = root;
        if (cmp > 0) {
            tmp = tmp.right;
        } else if (cmp < 0) {
            tmp = tmp.left;
        }
        return tmp.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node remove(Node node, Key key) {
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            if (!isRed(node.left) || !isRed(node.left.left)) {
                moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (cmp == 0 && node.right == null) {
                removed = node;
                return null;
            }

            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            cmp = key.compareTo(node.key);
            if (cmp == 0) {
                removed = node;
                Node min = min(node.right);
                min.left = node.left;
                min.right = deleteMin(node.right);
                node = min;
            } else {
                node.right = remove(node.right, key);
            }
        }
        return fixUp(node);
    }

    private Node moveRedRight(Node node) {
        flipColours(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColours(node);
        }
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColours(node);
        if (node.right != null && isRed(node.right.right)) {
            node.right = rotateLeft(node.right);
            node = rotateLeft(node);
            flipColours(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) || !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColours(node);
        }
        return node;
    }

    @Override
    public Value remove(Key key) {
        if (root == null) return null;
        root = remove(root, key);
        Node removed = this.removed;
        this.removed = null;
        if (removed != null) {
            if (root != null) {
                root.color = BLACK;
            }
            return removed.value;
        }
        return null;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;

    }

    @Override
    public Key floor(Key key) {
        if (root == null) {
            return null;
        }
        Node floor = floor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node floor(Node node, Key key) {
        if (node.right != null && key.compareTo(node.right.key) > 0) {
            return floor(node.right, key);
        }
        if (node.left != null && key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.key) < 0) {
            return null;
        }
        return node;
    }

    @Override
    public Key ceil(Key key) {
        if (root == null) return null;
        Node ceil = ceil(root, key);
        if (ceil == null) return null;
        return ceil.key;
    }

    private Node ceil(Node node, Key key) {
        if (node.left != null && key.compareTo(node.left.key) < 0) {
            return ceil(node.left, key);
        }
        if (node.right != null && key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.key) > 0) {
            return null;
        }
        return node;
    }

    @Override
    public int size() {
        if (root == null) return 0;
        return root.size;
    }

    @Override
    public int height() {
        return 0;
    }
}
