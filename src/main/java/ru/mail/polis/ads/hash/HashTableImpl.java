package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private class Element {
        Key key;
        Value value;

        Element(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private float maxFactor = 0.75f;
    private int size;
    private int capacity;
    private ArrayList<LinkedList<Element>> array;

    HashTableImpl() {
        this(11);
    }

    HashTableImpl(int capacity) {
        this.capacity = capacity;
        this.array = new ArrayList<>(capacity);
        this.size = 0;

        for (int i = 0; i < capacity; i++) {
            array.add(i, new LinkedList<>());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        LinkedList<Element> l = array.get(hash(key));
        if (l.isEmpty()) {
            return null;
        } else {
            for (Element el : l) {
                if (el.key.equals(key)) {
                    return el.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        LinkedList<Element> l = array.get(hash(key));
        if (l.isEmpty()) {
            return false;
        } else {
            for (Element el : l) {
                if (el.key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        LinkedList<Element> l = array.get(hash(key));
        if (l.isEmpty()) {
            l.add(new Element(key, value));
            size++;
        } else {
            for (Element el : l) {
                if (el.key.equals(key)) {
                    el.value = value;
                    return;
                }
                l.add(new Element(key, value));
                size++;
            }
        }

        checkFactor();
    }

    private void checkFactor() {
        if ((float) size / capacity > maxFactor) {
            ArrayList<LinkedList<Element>> temp = array;
            capacity = nextPrimeNumber();
            array = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i++) {
                if (temp.size() <= array.size()) {
                    array.set(i, temp.get(i));
                    continue;
                }
                array.set(i, new LinkedList<>());
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        LinkedList<Element> l = array.get(hash(key));
        if (l.isEmpty()) {
            return null;
        } else {
            for (Element el : l) {
                if (el.key.equals(key)) {
                    Value v = el.value;
                    l.remove(el);
                    size--;
                    checkFactor();
                    return v;
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

    private int hash(Key key) {
        return (key.hashCode() & Integer.MAX_VALUE) % capacity;
    }

    private int nextPrimeNumber() {
        int v = capacity << 1;
        while (isNotPrime(v)) {
            v++;
        }
        return v;
    }

    private boolean isNotPrime(int number) {
        if (number % 2 == 0) {
            return true;
        }
        int sqrt = (int) Math.sqrt(number) + 1;
        for (int i = 3; i < sqrt; i += 2) {
            if (number % i == 0) {
                return true;
            }
        }
        return false;
    }
}
