package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {
    private static final int[] CAPACITY_LIST = {17, 31, 61, 127, 227, 347, 433, 617, 859, 1153, 1669, 1153};
    private static final int DEFAULT_CAPACITY = CAPACITY_LIST[0];
    private static final int NOT_FOUND = -1;

    private int capacityIndex = 0;
    private double LOAD_FACTOR = 0.75;
    private int amountOfItems = 0;
    private int currentCapacity = DEFAULT_CAPACITY;

    private ArrayList<Node>[] baskets = new ArrayList[DEFAULT_CAPACITY];;

    MyHashTable(){
        for (int i = 0; i < baskets.length; i++) {
            baskets[i] = new ArrayList<>();
        }
    }

    private class Node {
        Key key;
        Value value;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

    }

    private final int hashFunction(Key key) {
        return key.hashCode() % currentCapacity;
    }

    private int getIndex(int hashCode) {
        if (!isEmpty()) {
            for (int i = 0; i < baskets[hashCode].size(); i++) {
                if (hashFunction(baskets[hashCode].get(i).key) == hashCode) {
                    return i;
                }
            }
        }
        return NOT_FOUND;
    }

    private void increaseCapacity() {
        // Do not have time to write a function for primer numbers
        if (capacityIndex + 1 >= CAPACITY_LIST.length) {
            return;
        }

        currentCapacity = CAPACITY_LIST[++capacityIndex];
        ArrayList<Node>[] temp = new ArrayList[currentCapacity];

        for (int i = 0; i < baskets.length; i++) {
            for (int j = 0; j < baskets[i].size(); j++) {

                Node element = baskets[i].get(j);
                int hashedIndex = hashFunction(element.key);
                temp[hashedIndex].add(element);
            }
        }

        baskets = temp;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        if(isEmpty()) {return null;}

        int hashedIndex = hashFunction(key);

        int listIndex = getIndex(hashedIndex);

        return listIndex == NOT_FOUND
                ? null
                : baskets[hashedIndex].get(listIndex).value;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hashedIndex = hashFunction(key);

        int listIndex = getIndex(hashedIndex);

        return listIndex != NOT_FOUND;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hashedIndex = hashFunction(key);

        int listIndex = getIndex(hashedIndex);

        if ((double) amountOfItems / currentCapacity >= LOAD_FACTOR) {
            increaseCapacity();
        }

        if (listIndex == NOT_FOUND) {
            baskets[hashedIndex].add(new Node(key, value));
            amountOfItems++;
        }
        else {
            baskets[hashedIndex].set(listIndex, new Node(key, value));
        }

    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hashedIndex = hashFunction(key);

        int listIndex = getIndex(hashedIndex);

        if (listIndex != NOT_FOUND) {
            Value value = baskets[hashedIndex].get(listIndex).value;
            baskets[hashedIndex].remove(listIndex);

            amountOfItems--;

            return value;
        }
        else {
            return null;
        }

    }

    @Override
    public int size() {
        return amountOfItems;
    }

    @Override
    public boolean isEmpty() {
        return amountOfItems == 0;
    }
}
