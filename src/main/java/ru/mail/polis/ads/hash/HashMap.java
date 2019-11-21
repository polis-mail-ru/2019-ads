package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashMap<Key extends Comparable<Key>, Value> implements HashTable<Key, Value> {
    private Cell[] hash; // Array of list
    private int degree = 4; // The size of the array according to the degree
    private int sizeHash = theConstructionOfDegree(degree); // Size of array
    private double loadFactor = 0.75;
    private int size = 0; // Number of elements in the array


    /**
     * Constructor - create an array and populate it
     */
    public HashMap() {
        this.hash = new Cell[sizeHash];

        for (int i = 0; i < sizeHash; i++) {
            this.hash[i] = new Cell();
        }
    }

    /**
     * @return True - if load factor more than size/sizeHash
     *         False - of load factor less
     */
    private boolean isLoadFactorUp(){
        return ((double)size/sizeHash) > loadFactor;
    }

    /**
     * @return True - if load factor less than size/sizeHash
     *         False - of load factor more
     */
    private boolean isLoadFactorDown(){
        return ((double)size/sizeHash) < (1 - loadFactor);
    }

    /**
     *  @param isUp - refers to increase the size or decrease
     *  This method is designed to increase the size
     *  of the array - elements were rebuilt to the new volume
     */
    private void reHash(boolean isUp){
        if (isUp) degree++;
        else degree--;

        int newSizeHash = theConstructionOfDegree(degree);

        Cell[] newHash = new Cell[newSizeHash];
        for (int i = 0; i < newSizeHash; i++)
            newHash[i] = new Cell();


        for (Cell cell : hash) {
            for (int j = 0; j < cell.size(); j++) {
                Key key = (Key) cell.getKeyFromPosition(j);
                int position = positionOfElement(key, newSizeHash);
                newHash[position].put(key, cell.getValueFromPosition(j));
            }
        }
        hash = newHash;
        sizeHash = newSizeHash;
    }

    /**
     * @param degree - the degree to which we raise 2
     * @return degree of 2
     */
    private int theConstructionOfDegree(int degree){
        return (int) Math.pow(2,degree);
    }

    /**
     * @param key
     * @return hashing key
     */
    private int hashingKey(Key key) {
        int h = 0;
        return (key == null)?0:(h = (key.hashCode())^(h >>> 16));
    }

    /**
     * @param key - key of element
     * @param size - the size of the array in which we will make values
     * @return Cached key
     */
    private int positionOfElement(Key key, int size){
        return hashingKey(key) % (size - 1);
    }


    /**
     * @param key - the address of the cell in which the value is located
     * @return a value that is located at a given address
     */
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        if (size == 0) return null;
        return getFromHash(key);
        }

        /**
         * @param key - key of value
         * @return The value that is on this key
         */
        private Value getFromHash(Key key) {
                int position = positionOfElement(key,sizeHash);
                return (Value) hash[position].get(key);
            }

    /**
      * @param key - the key by which we should add values
     *  @param value - the value we need to add
     */
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int position = positionOfElement(key,sizeHash);
        if(this.hash[position].put(key,value)) size++;
        if(isLoadFactorUp()) reHash(true);
    }

    /**
     * @param key - the key by which we should remove values
     * @return The value that we deleted by the given key
     */
    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int position = positionOfElement(key,sizeHash);
        Value value = (Value)this.hash[position].remove(key);

        if (value != null){
            size--;
            if(isLoadFactorDown()) reHash(false);
        }

        return value;
    }

    /**
     * @return Cardinality of hash table
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * @return returns a boolean value indicating: hash map is empty or not
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }
}
