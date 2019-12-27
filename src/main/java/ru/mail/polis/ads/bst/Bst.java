package ru.mail.polis.ads.bst;

<<<<<<< HEAD
=======
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

>>>>>>> upstream/master
/**
 * Binary search tree with ordered operations support.
 */
public interface Bst<Key extends Comparable<Key>, Value> {
<<<<<<< HEAD
    Value get(Key key);

    void put(Key key, Value value);

    void remove(Key key);

    Key min();

    Value minValue();

    Key max();

    Value maxValue();

    Key floor(Key key);

    Key ceil(Key key);
    
    int size();
    
=======
    @Nullable Value get(@NotNull Key key);

    void put(@NotNull Key key, @NotNull Value value);

    @Nullable Value remove(@NotNull Key key);

    @Nullable Key min();

    @Nullable Value minValue();

    @Nullable Key max();

    @Nullable Value maxValue();

    @Nullable Key floor(@NotNull Key key);

    @Nullable Key ceil(@NotNull Key key);

    int size();

>>>>>>> upstream/master
    int height();
}
