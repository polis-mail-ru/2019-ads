class AvlTree <Key extends Comparable<Key>, Value> implements Bst<Key, Value>{

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size++;
        }
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) == 0) return x;
        if (key.compareTo(x.key) > 0) x = get(x.right, key);
        if (key.compareTo(x.key) < 0) x = get(x.left, key);
        return x;
    }

    public Value get(Key key) {
        return get(root, key).value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        if (key.compareTo(x.key) < 0) {x.left = put(x.left, key, value);}
        else if (key.compareTo(x.key) > 0) {x.right = put(x.right, key, value);}
        else {x.value = value;}
        x = balance(x);
        fixHeight(x);
        return x;
    }

    public void put(Key key, Value value) {root = put(root, key, value);}

    public Node remove(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {x.left = remove(x.left, key);}
        if (key.compareTo(x.key) > 0) {x.right = remove(x.right, key);}
        if (key == x.key) {x = innerDelete(x);}
        return x;
    }

    public Node remove(Key key) {
        size--;
        return remove(root, key);
    }

    // Key Based Methods

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    public Value minValue() {
        if (root == null) return null;
        return get(min());
    }

    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (root == null) return null;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public Value maxValue() {
        if (root == null) return null;
        return get(max());
    }

    public int size() {
        return size;
    }

    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public void deleteMin() {
        size--;
        root = deleteMin(root);
    }

    private Node innerDelete(Node x) {
        if (x.right == null) return x.left;
        if (x.left == null) return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }
}