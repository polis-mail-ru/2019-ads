package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;
    private int size = 0;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, int height,boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }

    }

    private boolean isRed(Node element) {
        return element != null && element.color == RED;
    }

    private Node leftRotate(Node element) {
        Node right = element.right;
        element.right = right.left;
        right.left = element;
        right.color = element.color;
        element.color = RED;
        return right;
    }

    private Node rightRotate(Node element) {
        Node left = element.left;
        element.left = left.right;
        left.right = element;
        left.color = element.color;
        element.color = RED;
        return left;
    }

    private void flipColors(Node element) {
        element.color = !element.color;
        element.left.color = !element.left.color;
        element.right.color = !element.right.color;
    }

    private Node fixUp(Node element) {
        if (isRed(element.right) && !isRed(element.left)) {
            element = leftRotate(element);
        }
        else if (isRed(element.left) && !isRed(element.left.left)) {
            element = rightRotate(element);
        }
        else if (isRed(element.left) && isRed(element.right)) {
            flipColors(element);
        }

        return element;
    }

    private Node put(Node element, Key key, Value value) {
        if (element == null) {
            size++;
            return new Node(key, value, 1, RED);
        }

        if (key.compareTo(element.key) == -1) {
            element.left = put(element.left, key, value);
        }
        else if (key.compareTo(element.key) == 1) {
            element.right = put(element.right, key, value);
        }
        else {
            element.value = value;
        }

        element = fixUp(element);
        return element;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node result = get(root, key);
        return result == null ? null : result.value;
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
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        delete(root, key);
        size = size == 0 ? 0 : size-1;
        return value;
    }

    private Node delete(Node element, Key key) {
        if (element == null) {
            return null;
        }
        int comparatorResult = key.compareTo(element.key);

        if (comparatorResult < 0) {

            if (element.left != null) {

                if (!isRed(element.left) && !isRed(element.left.left)) {
                    element = moveRedLeft(element);
                }

                element.left = delete(element.left, key);
            }

        }
        else if (comparatorResult > 0) {
            if (element.right != null) {
                if (isRed(element.left)) {
                    element = rightRotate(element);
                }

                if (!isRed(element.right) && !isRed(element.right.left)) {
                    element = moveRedRight(element);
                }

                element.right = delete(element.right, key);
            }
        }
        else {

            if (isRed(element.left)) {
                element = rightRotate(element);
            }

            if (element.right == null) {
                return null;
            }

            element = getMin(element.right);
            element.right = deleteMin(element.right);

        }

        return fixUp(element);
    }

    private Node moveRedLeft(Node element) {
        flipColors(element);

        if (isRed(element.right.left)) {
            element.right = rightRotate(element.right);
            element = leftRotate(element);
            flipColors(element);
        }

        return element;
    }

    private Node moveRedRight(Node element) {
        flipColors(element);

        if (isRed(element.left.left)) {
            element = rightRotate(element);
            flipColors(element);
        }

        return element;
    }

    private Node deleteMax(Node element) {
        if (isRed(element.left)) {
            element = rightRotate(element);
        }

        if (element.right == null) {
            return null;
        }

        if (!isRed(element.right) && !isRed(element.right.left)) {
            element = moveRedRight(element);
        }

        element.right = deleteMax(element.right);
        return fixUp(element);
    }

    private Node deleteMin(Node element) {
        if (element == null || element.left == null) {
            return null;
        }

        if (!isRed(element.left) && !isRed(element.left.left)) {
            element = moveRedLeft(element);
        }

        element.left = deleteMin(element.left);
        return fixUp(element);
    }

    @Nullable
    @Override
    public Key min() {
        Node min = getMin(root);
        return min != null ? min.key : null;
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

    @Nullable
    @Override
    public Value minValue() {
        Node min = getMin(root);
        return min != null ? min.value : null;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = getMax(root);
        return max != null ? max.key : null;
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
    public Value maxValue() {
        Node max = getMax(root);
        return max != null ? max.value : null;
    }


    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        ceilFloor(key, root);
        return floor;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        ceilFloor(key, root);
        return ceil;
    }

    private Key ceil = null;
    private Key floor = null;

    private void ceilFloor(Key key, Node element) {
        if (element == null) {
            return;
        }

        if (element.key.compareTo(key) == 0) {
            ceil = element.key;
            floor = element.key;
        }
        else if (element.key.compareTo(key) == 1) {
            ceil = element.key;
            ceilFloor(key, element.left);
        }
        else {
            floor = element.key;
            ceilFloor(key, element.right);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root != null ? root.height : 0;
    }
}
