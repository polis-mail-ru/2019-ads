package ru.mail.polis.ads.art241111.bst;

/**
 * Binary search tree with ordered operations support.
 *
 *  Create by Artem Gerasimov
 *  Date 30.10.2019
 *  GIT: art241111
 */

public interface Bst<Key extends Comparable<Key>, Value> {
    Value get(Key key);

    void put(Key key, Value value);

    Value remove(Key key);

    Key min();

    Value minValue();

    Key max();

    Value maxValue();

    Key floor(Key key);

    Key ceil(Key key);

    int size();

    int height();
}
