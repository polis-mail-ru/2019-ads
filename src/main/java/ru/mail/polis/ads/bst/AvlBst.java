package ru.mail.polis.ads.bst;

<<<<<<< HEAD
=======
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

>>>>>>> upstream/master
/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
<<<<<<< HEAD
    
=======

>>>>>>> upstream/master
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

<<<<<<< HEAD
    @Override
    public Value get(Key key) {
=======
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
>>>>>>> upstream/master
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
<<<<<<< HEAD
    public void put(Key key, Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void remove(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

=======
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
>>>>>>> upstream/master
    @Override
    public Key min() {
        throw new UnsupportedOperationException("Implement me");
    }

<<<<<<< HEAD
=======
    @Nullable
>>>>>>> upstream/master
    @Override
    public Value minValue() {
        throw new UnsupportedOperationException("Implement me");
    }

<<<<<<< HEAD
=======
    @Nullable
>>>>>>> upstream/master
    @Override
    public Key max() {
        throw new UnsupportedOperationException("Implement me");
    }

<<<<<<< HEAD
=======
    @Nullable
>>>>>>> upstream/master
    @Override
    public Value maxValue() {
        throw new UnsupportedOperationException("Implement me");
    }

<<<<<<< HEAD
    @Override
    public Key floor(Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public Key ceil(Key key) {
=======
    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
>>>>>>> upstream/master
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
