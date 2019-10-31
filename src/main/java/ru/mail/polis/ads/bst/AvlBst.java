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

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;

    @Override
    public Value get(Key key) {
        if (key == null) {
            return null;
        }

        return get(key, root);
    }

    private Value get(Key key, Node x) {
        if (x == null) {
            return null; // не найдено
        } else if (key.compareTo(x.key) < 0) {
            return get(key, x.left); // идём влево, если меньше, чем x.key
        } else if (key.compareTo(x.key) > 0) {
            return get(key, x.right); // идём вправо, если больше, чем x.key
        } else {
            return x.value; // если key == x.key, то возвращаем x.value
        }
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) {
            throw new NullPointerException();
        }

        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }

        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }

        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }

        return x;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
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

    @Override
    public void remove(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }

        remove(key, root);
    }

    private void remove(Key key, Node x) {
        if (x == null) {
            throw new NullPointerException();
        }

        if (key.compareTo(x.key) > 0 && x.right != null) {
            remove(key, x.right);
        } else if (key.compareTo(x.key) < 0 && x.left != null) {
            remove(key, x.left);
        } else {
            x = removeFound(x);
        }
    }

    private Node removeFound(Node x) {
        if (x.left == null) {
            return x.right;
        }

        if (x.right == null) {
            return x.left;
        }

        Node temp = x;
        x = minNode(x);
        x.left = temp.left;
        x.right = temp.right;

        fixHeight(x);
        balance(x);

        return x;
    }

    private Node minNode(Node x) {
        if (x == null) {
            throw new NullPointerException();
        }

        if (x.left == null) {
            Node minNode = x;
            x = null;
            return minNode;
        }

        return minNode(x);
    }

    @Override
    public Key min() {
        return min(root);
    }

    private Key min(Node x) {
        if (x == null) {
            return null;
        }

        if (x.left == null) {
            return x.key;
        }

        return min(x.left);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (x == null) {
            return null;
        }

        if (x.right == null) {
            return x.key;
        }

        return max(x.right);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }

        return floor(key, root);
    }

    private Key floor(Key key, Node x) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) > 0 && x.right != null) {
            return floor(key, x.right);
        } else if (key.compareTo(x.key) < 0 && x.left != null) {
            return floor(key, x.left);
        }

        return x.key;
    }

    @Override
    public Key ceil(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }

        return ceil(key, root);
    }

    private Key ceil(Key key, Node x) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) > 0 && x.left != null) {
            return floor(key, x.left);
        } else if (key.compareTo(x.key) < 0 && x.right != null) {
            return floor(key, x.right);
        }

        return x.key;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return size(x.left) + size(x.right) + 1;
        }
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + max(height(x.left), height(x.right));
    }

    private int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
}
