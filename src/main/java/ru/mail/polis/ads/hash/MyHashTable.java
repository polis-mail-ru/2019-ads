package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    private class Pair {
        Key key;
        Value value;
        Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private double loadMax = 0.75;
    private int size;
    private final int[] prime = {13, 29, 59, 113, 223, 443, 887, 1759, 3449};
    private int indexPrime;
    private List<Pair>[] array;



    MyHashTable() {
        indexPrime = 0;
        array = new List[prime[indexPrime]];
    }


    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        for (int i = 0; i < prime[indexPrime]; ++i) {
            if (array[i] != null) {
                for (Pair pair : array[i]) {
                    if (pair.key.equals(key))
                        return pair.value;
                }
            }
        }

        return null;
    }

    void resize() {
        List<Pair>[] tmp_array = new LinkedList[prime[indexPrime]];
        for (int i = 0; i < prime[indexPrime-1]; ++i) {
            if (array[i] != null) {
                for (Pair pair : array[i]) {
                    final int index = (pair.key.hashCode() & 0x7fffffff) % prime[indexPrime];
                    tmp_array[index] = new LinkedList<>();
                    tmp_array[index].add(new Pair(pair.key, pair.value));
                }
            }
        }
        array = tmp_array;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {

        int index = (key.hashCode() & 0x7fffffff) % prime[indexPrime];

        if (array[index] == null) {
            if (size / prime[indexPrime] > loadMax) {
                indexPrime++;
                resize();
            }
            ++size;
            array[index] = new LinkedList<>();
        } else {
            for (Pair pair : array[index]) {
                if (pair.key.equals(key)) {
                    pair.value = value;
                    return;
                }
            }

            if (size / prime[indexPrime] > loadMax) {
                indexPrime++;
                resize();
            }
            ++size;
            index = (key.hashCode() & 0x7fffffff) % prime[indexPrime];
        }
        array[index].add(new Pair(key, value));
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        for (int i = 0; i < prime[indexPrime]; ++i) {
            if (array[i] != null) {
                for (Pair pair : array[i]) {
                    if (pair.key == key) {
                        array[i].remove(pair);
                        size--;
                        return pair.value;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
