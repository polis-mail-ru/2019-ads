package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
            height = 1;
        }
    }
    private Node rootNode;

    /*public AvlBst(Key key, Value value){
        rootNode = new Node(key, value);
    }*/

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(key, rootNode);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        rootNode = put(rootNode, key, value);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value oldValue = get(key);
        rootNode = remove(key, rootNode);
        return oldValue;
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

    @Override
    public int height() {
        if (rootNode == null){
            return 0;
        }
        else {
            return rootNode.height;
        }
    }

    private Value get(Key key, Node node){
        while (node != null){
            int cmp = key.compareTo(node.key);
            if (cmp < 0){
                node = node.left;
            }
            if (cmp > 0){
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
        if (key.compareTo(rootNode.key) == 0){
            return node.key;
        }
        if (rootNode.key.compareTo(key) < 0){
            return ceil(node.right, key);
        }
        Key ceilValue = ceil(node.left, key);
        return (ceilValue.compareTo(key) >= 0) ? ceilValue : node.key;
    }
    private Key floor(Node node, Key key){
        if (node == null){
            return null;
        }
        if (key.compareTo(rootNode.key) == 0){
            return node.key;
        }
        if (rootNode.key.compareTo(key) > 0){
            return ceil(node.left, key);
        }
        Key ceilValue = ceil(node.right, key);
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
    private Node put(Node currentNode, Key key, Value value){
        if (currentNode == null) {
            return new Node(key, value);
        }
        else {
            if (key.compareTo(currentNode.key) < 0){
                currentNode.left = put(currentNode.left, key, value);
            }
            else if (key.compareTo(currentNode.key) > 0){
                currentNode.right = put(currentNode.right,key,value);
            }
            else{
                currentNode.value = value;
                return currentNode;
            }
            if (Math.abs((currentNode.left == null ? 0 : (currentNode.left).height) -
                    (currentNode.right == null ? 0 : (currentNode.right).height)) == 2) {
                currentNode = balanceTree(currentNode);
            }
            recalcHeight(currentNode);
            return currentNode;
        }
    }
    private Node remove(Key key, Node currentNode) {
        if (currentNode == null) {
            return null;
        }
        else if (key.compareTo(currentNode.key) == 0) {
            if (currentNode.left == null) {
                return currentNode.right;
            }
            else if (currentNode.right == null) {
                return currentNode.left;
            }
            else {
                Node current = currentNode.right;
                while (current.left != null) {
                    current = current.left;
                }
                currentNode.key = current.key;
                currentNode.value = current.value;
                currentNode.right = remove(current.key, currentNode.right);
            }
        }
        else if (key.compareTo(currentNode.key) < 0) {
            currentNode.left = remove(key, currentNode.left);
        }
        else {
            currentNode.right = remove(key, currentNode.right);
        }
        if (Math.abs((currentNode.left == null ? 0 : (currentNode.left).height) -
                (currentNode.right == null ? 0 : (currentNode.right).height)) == 2) {
            currentNode = balanceTree(currentNode);
        }
        recalcHeight(currentNode);
        return currentNode;
    }
    private Node balanceTree(Node currentNode){
        int heightLeft = currentNode.left == null ? 0 : (currentNode.left).height;
        int heightRight = currentNode.right == null ? 0 : (currentNode.right).height;
        if (heightLeft > heightRight) {
            Node child = currentNode.left;
            if ((child.left == null ? 0 : (child.left).height) <
                    (child.right == null ? 0 : (child.right).height)) {
                currentNode.left = pivotRight(child);
            }
            return pivotLeft(currentNode);
        }
        else {
            Node child = currentNode.right;
            if ((child.left == null ? 0 : (child.left).height) >
                    (child.right == null ? 0 : (child.right).height)) {
                currentNode.right = pivotLeft(child);
            }
            return pivotRight(currentNode);
        }
    }
    private Node pivotLeft(Node node) {
        Node child = node.left;
        node.left = child.right;
        child.right = node;
        recalcHeight(node);
        recalcHeight(child);
        return child;
    }
    private Node pivotRight(Node node) {
        Node child = node.right;
        node.right = child.left;
        child.left = node;
        recalcHeight(node);
        recalcHeight(child);
        return child;
    }
    private void recalcHeight(Node node) {
        node.height = 1 + Math.max(node.left == null ? 0 : (node.left).height,
                node.right == null ? 0 : (node.right).height);
    }
}
