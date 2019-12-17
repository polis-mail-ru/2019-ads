class Bst <Key extends Comparable<Key>, Value>{

    private Node root;
    private int size;
    private static boolean RED = true;
    private static boolean BLACK = false;

    public Bst() { }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
        boolean color;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size++;
        }
    }

    public Bst newBst() {
        return new Bst();
    }

    // Main methods

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

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node remove(Node x, Key key) {
        if (x == null) return null;

        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (x.left.color == BLACK & x.left.left != null & x.left.left.color == BLACK) x = moveRedLeft(x);
                x.left = remove(x.left, key);
            }
        } else {
            if (key.compareTo(x.key) == 0 && x.right == null) {
                return null;
            } else if (x.left != null && x.left.color == RED) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else {
                if (x.right != null && x.right.color == BLACK && x.right.left != null && x.right.left.color == BLACK)
                    x = moveRedRight(x);
                if (key.compareTo(x.key) == 0) {
                    Node min = get(root, min(x.right).key);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else
                    x.right = remove(x.right, key);
            }
        }
        fixHeight(x);
        return balance(x);
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

    public Key floor(Key key) {
        Node node = root;
        Key max = null;
        if (node == null) return null;

        while (node != null) {
            if (key.compareTo(node.key) > 0) {
                max = node.key;
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                max = node.key;
                node = node.left;
            } else if (key.compareTo(node.key) == 0) {
                return node.key;
            }
        }
        return max;
    }

    public Key ceil(Key key) {
        Node node = root;
        Node nodeNext = null;
        Key min = null;
        if (node == null) return null;

        while (node != null) {
            nodeNext = node.right;
            if (key.compareTo(node.key) > 0) {
                min = node.key;
                if (key.compareTo(nodeNext.key) < 0) {
                    break;
                }
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                min = node.key;
                node = node.left;
            } else if (key.compareTo(node.key) == 0) {
                return node.key;
            }
        }

        return min;
    }

    // Utility Methods

    public int size() {
        return size;
    }

    public int height(Node x) {
        return x == null ? 0 : x.height;
    }

    // Other Methods

    private Node deleteMax(Node x) {
        if (x.left != null && x.left.color == RED) x = rotateRight(x);
        if (x.right == null) return null;
        if ((x.right != null && x.right.color == BLACK) && (x.right.left != null && x.right.left.color == BLACK)) x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return balance(x);
    }

    public void deleteMax() {
        size--;
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return null;
        if (x.left.color == BLACK && x.left.left.color == BLACK) x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMin() {
        size--;
        root = deleteMin(root);
        root.color = BLACK;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        fixHeight(x);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        fixHeight(x);
        fixHeight(right);
        return right;
    }

    private Node moveRedLeft(Node x) {
        x = flipColors(x);
        if (x.right.left != null && x.right.left.color == RED) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            x = flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        x = flipColors(x);
        if (x.left.left != null && x.left.left.color == RED) {
            x = rotateRight(x);
            x = flipColors(x);
        }
        return x;
    }

    private Node balance(Node x) {
        if ((x.left != null && x.left.color == RED) && (x.right != null && x.right.color == RED)) x = flipColors(x);
        if ((x.left != null && x.left.color == RED) && x.left.left.color == RED) x = rotateRight(x);
        if ((x.right != null && x.right.color == RED) && x.left.color == BLACK) x = rotateLeft(x);
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.right.color = !x.right.color;
        x.left.color = !x.left.color;
        return x;
    }
}