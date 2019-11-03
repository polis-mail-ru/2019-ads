package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root = null;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private int height(Node element) {
        return element == null ? 0: element.height;
    }

    private int fixHeight(Node element) {
        if (element == null) {
            return 0;
        }

        element.height = 1 + Math.max(fixHeight(element.left), fixHeight(element.right));
        return element.height;
    }

    @Override
    public Value get(Key key) {
        return get(root, key).value;
    }

    private Node get(Node element, Key key) {
        if (element == null) {
            return null;
        }

        switch (element.key.compareTo(key)) {
            case 0:
                return element;

            case 1:
                return get(element.left, key);

            case -1:
                return get(element.right, key);

        }

        return element;
    }

    @Override
    public void put(Key key, Value value) {
        root = addElement(root, key, value);
        size++;
    }

    private Node addElement(Node element, Key key, Value value) {
        if (element == null) return new Node(key, value);

        if (key.compareTo(element.key) == -1) {
            element.left = addElement(element.left, key, value);
        }
        else  if (key.compareTo(element.key) == 1){
            element.right = addElement(element.right, key, value);
        }
        else {
            element.value = value;
        }
        fixHeight(element);
        element = balance(element);
        return element;
    }


    private int factor(Node element) {
        return height(element.left) - height(element.right);
    }

    private Node rightRotate(Node element) {
        Node left = element.left;
        element.left = left.right;
        left.right = element;
        fixHeight(element);
        fixHeight(left);
        return left;
    }

    private Node leftRotate(Node element) {
        Node right = element.right;
        element.right = right.left;
        right.left = element;
        fixHeight(element);
        fixHeight(right);
        return right;
    }

    private Node balance(Node element) {
        if (factor(element) == 2) {
            if (factor(element.left) < 0){
                element.left = leftRotate(element.left);
            }
            return rightRotate(element);
        }

        if (factor(element) == -2) {
            if (factor(element.right) > 0) {
                element.right = rightRotate(element.right);
            }
            return leftRotate(element);
        }
        return element;
    }


    private void deleteMin() {
        root = deleteMin(root);
        fixHeight(root);
    }

    private Node deleteMin(Node element) {
        if (element.left == null) {
            return element.right;
        }
        element.left = deleteMin(element.left);
        return element;
    }

    private Node innerDelete(Node element) {
        if (element.right == null) return element.left;
        if (element.left == null) return element.right;

        Node temp = element;
        element = getMin(temp.right);
        element.right = deleteMin(temp.right);
        element.left = temp.left;
        size--;
        return element;
    }

    private Node delete (Key key, Node element) {
        if (element == null) {
            return null;
        }
        if (key.compareTo(element.key) == -1) {
            element.left = delete(key, element.left);
        }
        if (key.compareTo(element.key) == 1) {
            element.right = delete(key, element.right);
        }
        if (key.compareTo(element.key) == 0) {
            element = innerDelete(element);
        }
        return element;
    }

    @Override
    public void remove(Key key) {
        root = delete(key, root);
        fixHeight(root);
    }


    @Override
    public Key min() {
        Node min = getMin(root);
        return min != null ? min.key : null;
    }

    @Override
    public Value minValue() {
        Node min = getMin(root);
        return min != null ? min.value : null;
    }

    private Node getMin(Node start){
        if (start == null) {
            return null;
        }

        Node current = start;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private Node getMax(Node start){
        if (start == null) {
            return null;
        }

        Node current = start;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }


    @Override
    public Key max() {
        Node max = getMax(root);
        return max != null ? max.key : null;
    }

    @Override
    public Value maxValue() {
        Node max = getMax(root);
        return max != null ? max.value : null;
    }

    @Override
    public Key floor(Key key) {
        if (root == null) {
            return null;
        }

        Key floor = min();
        if (floor.compareTo(key) == 1) {
            return null;
        }

        Node current = root;
        while (current != null) {
            switch (current.key.compareTo(key)) {
                case 0:
                    return current.key;
                case 1:
                    floor = floor.compareTo(current.key) == 1 ? floor : current.key;
                    current = current.left;
                case -1:
                   floor = getMax(current.left).key;
                   return floor;
            }
        }
        return floor;
    }



    @Override
    public Key ceil(Key key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root.height;
    }
}


