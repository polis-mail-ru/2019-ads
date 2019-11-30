package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    private class Element {
        Key key;
        Value value;
        Element(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private final static int[] primeNumbers = {
            17, 37, 67, 131, 257, 521, 1031, 2053, 4099, 8209, 16411, 32771
    };
    private final static float maxLoadFactor = 0.75f;
    private final static float minLoadFactor = 0.25f;

    private int size;
    private int primeIndex;

    private ArrayList<LinkedList<Element>> array;

    MyHashTable() {
        primeIndex = 0;
        array = new ArrayList<>(primeNumbers[primeIndex]);
        for (int i = 0; i < primeNumbers[primeIndex]; ++i) {
            array.add(new LinkedList<>());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        final int hashCode = key.hashCode();
        final int index = (hashCode & 0x7fffff) % primeNumbers[primeIndex];
        final LinkedList<Element> elementsList = array.get(index);
        for (Element element: elementsList) {
            if (element.key == key) {
                return element.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (!containsKey(key)) {
            ++size;
            if ((float) size / primeNumbers[primeIndex] > maxLoadFactor && primeIndex + 1 < primeNumbers.length) {
                ++primeIndex;
                rehash();
            }
        }
        final int hashCode = key.hashCode();
        final int index = (hashCode & 0x7fffff) % primeNumbers[primeIndex];
        final LinkedList<Element> elementsList = array.get(index);
        for (Element element: elementsList) {
            if (element.key == key) {
                element.value = value;
                return;
            }
        }
        elementsList.add(new Element(key, value));
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        final int hashCode = key.hashCode();
        final int index = (hashCode & 0x7fffff) % primeNumbers[primeIndex];
        final LinkedList<Element> elementsList = array.get(index);
        for (Element element: elementsList) {
            if (element.key == key) {
                elementsList.remove(element);
                --size;
                if ((float) size / primeNumbers[primeIndex] < minLoadFactor && primeIndex > 0) {
                    --primeIndex;
                    rehash();
                }
                return element.value;
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

    private void rehash() {
        ArrayList<LinkedList<Element>> tmp = array;
        array = new ArrayList<>(primeNumbers[primeIndex]);
        for (int i = 0; i < primeNumbers[primeIndex]; ++i) {
            array.add(new LinkedList<>());
        }
        for (LinkedList<Element> elementsList: tmp) {
            for (Element element: elementsList) {
                put(element.key, element.value);
            }
        }
    }
}
