package ru.mail.polis.ads.hash;

import com.sun.jdi.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.Key;

/**
 * Associative array based on hashing.
 */
public interface HashTable<Key, Value> {
    @Nullable Value get(@NotNull Key key);

    default boolean containsKey(@NotNull Key key) {
        return get(key) != null;
    }

    void put(@NotNull Key key, @NotNull Value value);

    @Nullable Value remove(@NotNull Key key);

    @Nullable com.sun.jdi.Value get(@NotNull java.security.Key key);

    boolean containsKey(@NotNull java.security.Key k);

    void put(@NotNull java.security.Key key, @NotNull com.sun.jdi.Value value);

    @Nullable com.sun.jdi.Value remove(@NotNull java.security.Key key);

    int size();

    boolean isEmpty();
}