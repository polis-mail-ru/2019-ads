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

        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }

        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }

        return node.value;
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
        }

        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        }

        node.value = value;

        updateHeight(node);
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
        node = balance(node);

        return node;
    }

    @Override
    public Key min() {
        Node min = min(node);

        return min == null ? null : min.key;
    }

    @Override
    public Value minValue() {
        Node min = min(node);

        return min == null ? null : min.value;
    }

    @Override
    public Key max() {
        Node max = max(node);

        return max == null ? null : max.key;
    }

    @Override
    public Value maxValue() {
        Node max = max(node);

        return max == null ? null : max.value;
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

        if (key.compareTo(node.key) > 0) {
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
            return floor(node.right, key);
        }

        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        
        return node.key;
    }

    @Override
    public int size() {
        return size(node);
    }

    private int size(Node node) {
        return node == null ? 0 : size(node.left) + size(node.right) + 1;
    }

    @Override
    public int height() {
        return height(node);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node balance(Node node) {
        if (heightDiff(node) == 2) {
            if (heightDiff(node.left) < 0) {
                node.left = rotate(node.left, true);
            }

            return rotate(node, false);
        }

        if (heightDiff(node) == -2) {
            if (heightDiff(node.right) > 0) {
                node.right = rotate(node.right, false);
            }

            return rotate(node, true);
        }
    }

    private int heightDiff(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotate(Node node, boolean left) {
        Node tmp = null;

        if (left) {
            tmp = node.right;
            node.right = tmp.left;
            tmp.left = node;
        } else {
            tmp = node.left;
            node.left = tmp.right;
            tmp.right = node;
        }
        
        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }
}
