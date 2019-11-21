package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
            this.height = 1;
        }
    }

    private int height(Node element) {
        return element == null ? 0: element.height;
    }

    private void fixHeight(Node element) {
        element.height = 1 + Math.max(height(element.left), height(element.right));
    }

    @Nullable
    @Override

    public Value get(Key key) {
        Node result = get(root, key);
        return result == null ? null : result.value;
    }

    private Node get(Node element, Key key) {
        if (element == null) {
            return null;
        }

        int comparisonResult = element.key.compareTo(key);

        if (comparisonResult > 0) {
            return get(element.left, key);
        }
        else if (comparisonResult < 0) {
            return get(element.right, key);
        }
        else {
            return element;
        }

    }

    @Override
    public void put(Key key, Value value) {
        root = addElement(root, key, value);
        size++;
    }

    private Node addElement(Node element, Key key, Value value) {
        if (element == null) return new Node(key, value);

        int comparatorResult = element.key.compareTo(key);

        if (comparatorResult > 0) {
            element.left = addElement(element.left, key, value);
        }
        else  if (comparatorResult < 0){
            element.right = addElement(element.right, key, value);
        }
        else {
            element.value = value;
            size--;
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
        deletedValue = element.value;
        if (element.right == null) return element.left;
        if (element.left == null) return element.right;

        Node temp = element;
        element = getMin(temp.right);
        element.right = deleteMin(temp.right);
        element.left = temp.left;
        element = balance(element);
        return element;
    }

    private Node delete (Key key, Node element) {
        if (element == null) {
            return null;
        }

        int comparatorResult = key.compareTo(element.key);

        if (comparatorResult < 0) {
            element.left = delete(key, element.left);
        }
        else if (comparatorResult > 0) {
            element.right = delete(key, element.right);
        }
         else {
            element = innerDelete(element);
        }
        return element;
    }

    private Value deletedValue = null;

    @Override
    public Value remove(Key key) {
        root = delete(key, root);
        fixHeight(root);
        size = root == null ? 0 : size - 1;
        return deletedValue;

    }



    @Override
    public Key min() {
        Node min = getMin(root);
        return min != null ? min.key : null;
    }

    @Nullable
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


    @Nullable
    @Override
    public Key max() {
        Node max = getMax(root);
        return max != null ? max.key : null;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = getMax(root);
        return max != null ? max.value : null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        CeilFloorHelper<Key> context = new CeilFloorHelper();
        ceilFloor(key, root, context);
        return context.floor;
    }

    @Nullable
    @Override

    public Key ceil(@NotNull Key key) {
        CeilFloorHelper<Key> context = new CeilFloorHelper();
        ceilFloor(key, root, context);
        return context.ceil;
    }

    private void ceilFloor(Key key, Node element, CeilFloorHelper context) {
        if (element == null) {
            return;
        }

        if (element.key.compareTo(key) == 0) {
            context.ceil = element.key;
            context.floor = element.key;
        }
        else if (element.key.compareTo(key) == 1) {
            context.ceil = element.key;
            ceilFloor(key, element.left, context);
        }
        else {
            context.floor = element.key;
            ceilFloor(key, element.right, context);
        }
    }

    private class CeilFloorHelper<Key> {
        public Key ceil = null;
        public Key floor = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }
}


