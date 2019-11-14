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
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, boolean color) {
          this.key = key;
          this.value = value;
          this.color = color;
        }
    }

    private Node node;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
      node = put(node, key, value);
      node.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {

    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
    }

    @Nullable
    @Override
    public Key min() {
    }

    @Nullable
    @Override
    public Value minValue() {
    }

    @Nullable
    @Override
    public Key max() {
    }

    @Nullable
    @Override
    public Value maxValue() {
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
    }

    @Override
    public int size() {
    }

    @Override
    public int height() {
    }

    private Node rotate(Node node, boolean left) {
        Node tmp = null;

        if (left) {
            tmp = node.right;
            node.right = node.left;
            node.left = node;
        } else {
            tmp = node.left;
            node.left = node.right;
            node.right = node;
        }

        tmp.color = node.color;
        node.color = RED;

        updateHeight(node);
        updateHeight(tmp);

        return tmp;
    }
}
