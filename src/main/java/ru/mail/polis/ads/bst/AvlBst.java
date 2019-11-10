package ru.mail.polis.ads.bst;

import java.util.ArrayList;

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

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        @Override
        public String toString() {
            return this.key + " " + this.value;
        }

    }

    Node root;
    int size;

    private int heightDiff(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    private Node balance(Node node) {
        if (heightDiff(node) == 2) {
            if (heightDiff(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (heightDiff(node) == -2) {
            if (heightDiff(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private void updateHeight(Node node) {
        if (node != null)
            node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
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
        root = put(root, key, value);
        size++;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            size--;
            node.value = value;
        }
        updateHeight(node);
        node = balance(node);
        return node;
    }

    @Override
    public Value remove(Key key) {
        return remove(root, key).value;
    }

    private Node innerRemove(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node t = node;
        node = minNode(t.right);
        node.right = deleteMin(t.right);
        node.left = t.left;
        return node;
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
        if (node.key.compareTo(key) == 0) {
            System.out.println(node + "sdasdasd");
            node = innerRemove(node);
        }
        updateHeight(node);
        node = balance(node);
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node minNode(Node node) {
        Node currentNode = node;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    private Node minNode() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    @Override
    public Key min() {
        Node node = minNode();
        return node == null ? null : node.key ;
    }

    @Override
    public Value minValue() {
        Node node = minNode();
        return node == null ? null : node.value;
    }

    private Node maxNode() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode;
    }

    @Override
    public Key max() {
        Node node = maxNode();
        return node == null ? null : node.key;
    }

    @Override
    public Value maxValue() {
        Node node = maxNode();
        return node == null ? null : node.value;
    }

    @Override
    public Key floor(Key key) {
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.key;
        }
        if (node.key.compareTo(key) > 0) {
            return floor(node.left, key);
        }
        Key floor = node.key;
        if (node.right != null) {
            floor = floor(node.right, key);
        }
        return (floor.compareTo(key) <= 0) ? floor : node.key;
    }

    @Override
    public Key ceil(Key key) {
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node.key;
        }
        if (node.key.compareTo(key) < 0) {
            return ceil(node.right, key);
        }
        Key ceil = node.key;
        if (node.left != null) {
            ceil = ceil(node.left, key);
        }
        return (ceil.compareTo(key) >= 0) ? ceil : node.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    public static void main(String[] args) {
        AvlBst tree = new AvlBst();
        for (int i = 0; i < 100; i++) {
            tree.put(i, 123);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(root);
        for (int i = 1; i < height(); i++) {
            list.add(new ArrayList<>());
            for (int j = 0; j < list.get(i - 1).size() * 2; j++) {
                if (i == 0) {
                    list.get(i).add(root);
                } else if (list.get(i - 1).get(j / 2) != null) {
                    Node node;
                    Node currentNode = list.get(i - 1).get(j / 2);
                    if (j % 2 == 0) {
                        node = currentNode.left;
                    } else {
                        node = currentNode.right;
                    }
                    list.get(i).add(node);
                } else {
                    list.get(i).add(null);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < (list.get(height() - 1).size() / 2) - i; j++) {
                sb.append("\t\t");
            }
            for (int j = 0; j < list.get(i).size(); j++) {
                sb.append(list.get(i).get(j) == null ? "n" : list.get(i).get(j));
                sb.append("\t\t");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
