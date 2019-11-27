package ru.mail.polis.ads.bst;

import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * AVL implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private enum Color{
        RED, BLACK
    }
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        Color color;
        int height;
        public Node(Key key, Value value, Color color, Node left, Node right){
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = left;
            this.right = right;
            height = 1;
        }
        public  Node(Key key, Value value){
            this(key,value, Color.RED, null, null);
        }
    }
    private Node rootNode;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
            return get(key, rootNode);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        rootNode = put(key, value, rootNode);
        rootNode.color = Color.BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (rootNode == null){
            return null;
        }
        if (get(key) == null){
            return null;
        }
        Node found = new Node(null, null);
        if (isBlack(rootNode.left) && isBlack(rootNode.right)){
            rootNode.color = Color.RED;
        }
        rootNode = remove(key, rootNode, found);
        if (isRed(rootNode)){
            rootNode.color = Color.BLACK;
        }
        return found.value;
    }

    @Nullable
    @Override
    public Key min() {
        return min(rootNode);
    }

    @Nullable
    @Override
    public Value minValue() {
        return minValue(rootNode);
    }

    @Nullable
    @Override
    public Key max() {
        return max(rootNode);
    }

    @Nullable
    @Override
    public Value maxValue() {
        return maxValue(rootNode);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(rootNode, key);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(rootNode, key);
    }

    @Override
    public int size() {
        return size(rootNode);
    }


    private Value get(Key key, Node node){
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                node = node.left;
            }
            else if (cmp > 0){
                node = node.right;
            }
            else {
                return node.value;
            }
        }
        return  null;
    }
    private Key min(Node node){
        if (node == null){
            return  null;
        }
        else {
            while (node.left != null){
                node = node.left;
            }
            return node.key;
        }
    }
    private Value minValue(Node node){
        if (node == null){
            return  null;
        }
        else {
            while (node.left != null){
                node = node.left;
            }
            return node.value;
        }
    }
    private Key max(Node node){
        if (node == null){
            return  null;
        }
        else {
            while (node.right != null){
                node = node.right;
            }
            return node.key;
        }
    }
    private Value maxValue(Node node){
        if (node == null){
            return  null;
        }
        else {
            while (node.right != null){
                node = node.right;
            }
            return node.value;
        }
    }
    private Key ceil(Node node, Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0){
            return node.key;
        }
        if (node.key.compareTo(key) < 0){
            return ceil(node.right, key);
        }
        Key ceilValue = ceil(node.left, key);
        if (ceilValue == null){
            return node.key;
        }
        return (ceilValue.compareTo(key) >= 0) ? ceilValue : node.key;
    }
    private Key floor(Node node, Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) == 0){
            return node.key;
        }
        if (node.key.compareTo(key) > 0){
            return floor(node.left, key);
        }
        Key ceilValue = floor(node.right, key);
        if (ceilValue == null){
            return node.key;
        }
        return (ceilValue.compareTo(key) <= 0) ? ceilValue : node.key;
    }
    private int size(Node node){
        if (node == null){
            return 0;
        }
        else {
            return(size(node.left) + 1 + size(node.right));
        }
    }
    public int height() {
        if (rootNode == null){
            return 0;
        }
        else {
            return rootNode.height;
        }
    }
    private Node put(Key key, Value value, Node node){
        if (node == null){
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = put(key, value, node.left);
        }
        else if (cmp > 0){
            node.right = put(key, value, node.right);
        }
        else {
            node.value = value;
        }
        recalcHeight(node);
        return balanceTree(node);
    }
    private Node remove(Key key, Node node, Node found) {
        if (key.compareTo(node.key) < 0){
            if (isBlack(node.left) && isBlack(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = remove(key, node.left, found);
        }
        else {
            if (isRed(node.left)){
                node = pivotRight(node);
            }
            if (key.compareTo(node.key) == 0 && node.right == null){
                found.value = node.value;
                return null;
            }
            if (isBlack(node.right) && isBlack(node.right.left)){
                node = moveRedRight(node);
            }
            if (key.compareTo(node.key) == 0){
                found.value = node.value;
                Node min = new Node(null, null);
                node.right = deleteMin(node.right, min);
                node.key = min.key;
                node.value = min.value;
            }
            else {
                node.right = remove(key, node.right, found);
            }
        }
        recalcHeight(node);
        return balanceTree(node);
    }
    private Node deleteMin(Node node, Node min){
        if (node.left == null){
            min.key = node.key;
            min.value = node.value;
            return null;
        }
        if (isBlack(node.left) && isBlack(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left, min);
        return  balanceTree(node);
    }
    private Node balanceTree(Node node){
        if (isRed(node.right)){
            node = pivotLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = pivotRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }
    private Node pivotLeft(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        child.color = node.color;
        node.color = Color.RED;
        recalcHeight(node);
        recalcHeight(child);
        return child;
    }
    private Node pivotRight(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        child.color = node.color;
        node.color = Color.RED;
        recalcHeight(node);
        recalcHeight(child);
        return child;
    }
    private boolean isRed(Node node){
        return  node != null && node.color == Color.RED;
    }
    private boolean isBlack(Node node){
        return node == null || node.color == Color.BLACK;
    }
    private void flipColor(Node node){
        switch (node.color){
            case RED:
                node.color = Color.BLACK;
                break;
            case BLACK:
                node.color = Color.RED;
                break;
        }
    }
    private void flipColors(Node node){
        flipColor(node);
        flipColor(node.right);
        flipColor(node.left);
    }
    private Node moveRedLeft(Node node){
        flipColors(node);
        if (isRed(node.right.left)){
            node.right = pivotRight(node.right);
            node = pivotLeft(node);
        }
        return node;
    }
    private Node moveRedRight(Node node){
        flipColors(node);
        if (isRed(node.left.left)){
            node = pivotRight(node);
        }
        return node;
    }
    private void recalcHeight(Node node) {
        node.height = 1 + Math.max(node.left == null ? 0 : (node.left).height,
                node.right == null ? 0 : (node.right).height);
    }
}
