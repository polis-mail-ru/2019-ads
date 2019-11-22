package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;
    private int size;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.height = 1;
        }

    }

    public RedBlackBst() {
        this.root = null;
        this.size = 0;
    }

    public RedBlackBst(Node root) {
        this.root = root;
        this.size = 1;
    }

    public RedBlackBst(Map<? extends Key, ? extends Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<? extends Key, ? extends Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return findNode(key, root);
    }

    private Value findNode(Key key, Node node) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            return findNode(key, node.right);
        }
        else if (key.compareTo(node.key) < 0) {
            return findNode(key, node.left);
        }
        else if (key == node.key) {
            return node.value;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        return null;
    }

    @Nullable
    @Override
    public Key min() {
        return null;
    }

    @Nullable
    @Override
    public Value minValue() {
        return null;
    }

    @Nullable
    @Override
    public Key max() {
        return null;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return null;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }
}
