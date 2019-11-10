package main.java.ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */

public class AvlBst<Key extends Comparable<Key>, Value>
        implements main.java.ru.mail.polis.ads.bst.Bst<Key, Value> {

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    private Node root;

    @Override
    public Value get(Key key) {
        if (root == null) {
            return null;
        }

        Node tmp = root;
        while (tmp.key.compareTo(key) != 0) {
            if (tmp.key.compareTo(key) < 0) {
                if (tmp.left == null) {
                    return null;
                }
                tmp = tmp.left;
            } else {
                if (tmp.right == null) {
                    return null;
                }
                tmp = tmp.right;
            }
        }

        return tmp.value;
    }

    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {

        if (node == null) {

            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }

        node.value = value;

        updateHeight(node);
        node = balanceAndRotate(node);

        return node;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node balanceAndRotate(Node node) {
        if (balanceFactor(node) == 2) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }

            return rightRotate(node);
        }

        if (balanceFactor(node) == -2) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }

            return leftRotate(node);
        }

        return node;
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node leftRotate(Node node) {
        Node tmp;

        tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    private Node rightRotate(Node node) {
        Node tmp;

        tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        }

        if ((node.left != null) && (node.right != null)) {
            node.key = min(node).key;
            node.right = remove(node.right, node.key);
        } else if (node.left != null) {
            node = node.left;
        } else {
            node = node.right;
        }

        updateHeight(node);
        node = balanceAndRotate(node);

        return node;
    }

    @Override
    public Key min() {
        Node min = min(root);

        if (root == null) {
            return null;
        }
        return min.key;
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
        Node min = min(root);

        if (root == null) {
            return null;
        }

        return min.value;
    }

    @Override
    public Key max() {
        Node max = max(root);

        return max == null ? null : max.key;
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

    @Override
    public Value maxValue() {
        Node max = max(root);

        if (root == null) {
            return null;
        }

        return max.value;
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

        if (key.compareTo(node.key) > 0) {
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
            return floor(node.right, key);
        }

        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }

        return node.key;
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }

        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }

        return size(node.right) + size(node.left) + 1;
    }

    @Override
    public int height() {
        if (root == null) {
            return 0;
        }

        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return  node.height;
    }
}
