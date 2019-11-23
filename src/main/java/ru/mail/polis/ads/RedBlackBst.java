public class RedBlackBst<Key extends Comparable<Key>, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root = null;

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


    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }

        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }

        return node.value;
    }


    public void put(Key key, Value value) {
        root  = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, RED, 1);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }

        node.value = value;

        node = balance(node);
        updateHeight(node);

        return node;
    }

    public Value remove(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    public Key min() {
        Node min = min(root);

        if (min == null) {
            return null;
        }

        return min.key;
    }

    public Value minValue() {
        Node min = min(root);

        if (min == null) {
            return null;
        }

        return min.value;
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

    public Key max() {
        Node max = max(root);

        if (max == null) {
            return null;
        }

        return max.key;
    }

    public Value maxValue() {
        Node max = max(root);

        if (max == null) {
            return null;
        }

        return max.value;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }

        if (node.right == null) {
            return node;
        }

        return max(node.right);
    }

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

        if (key.compareTo(node.key) > 0) {
            return floor(node.right, key);
        }

        return node.key;
    }

    public Key ceil(Key key) {
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            return floor(node.right, key);
        }

        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }

        return node.key;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return size(node.left) + size(node.right) + 1;
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node balance(Node node) {
        if (red(node.right) && !red(node.left)) {
            node = leftRotate(node);
        }

        if (red(node.left) && !red(node.left.left)) {
            node = rightRotate(node);
        }

        if (red(node.left) && red(node.right)) {
            colorFlip(node);
        }

        return node;
    }

    private boolean red(Node node) {
        return (node != null) && node.color;
    }

    private Node leftRotate(Node node) {
        Node tmp;

        tmp = node.right;
        node.right = node.left;
        node.left = node;

        tmp.color = node.color;
        node.color = RED;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private Node rightRotate(Node node) {
        Node tmp;

        tmp = node.left;
        node.left = node.right;
        node.right = node;

        tmp.color = node.color;
        node.color = RED;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private void colorFlip(Node node) {
        node.color = !node.color;

        if (node.left != null) {
            node.left.color = !node.left.color;
        }

        if (node.right != null) {
            node.right.color = !node.right.color;
        }
    }
}
