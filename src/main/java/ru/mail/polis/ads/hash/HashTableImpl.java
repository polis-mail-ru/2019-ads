package ru.mail.polis.ads.hash;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    class Pair{
        Key key;
        Value value;
        Pair(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    ArrayList<ArrayList<Pair>> array = new ArrayList();
    int size = 32;
    float load = 0;
    int count = 0;

    HashTableImpl(){
        for (int i = 0; i < size; i++) {
            array.add(new ArrayList<>());
        }
    }
    static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
    private void resize(){
        size = size >> 1;
        ArrayList<ArrayList<Pair>> new_array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            new_array.add(new ArrayList<>());
        }
        for (ArrayList<Pair> pairs : array){
            for(Pair j : pairs){
                new_array.get(hash(j.key.hashCode()) & (size - 1)).add(j);
            }
        }
        load = (float)count/size;
        array = new_array;
        return;
    }
    @Override
    @Nullable
    public Value get(@NotNull Key key){
        ArrayList <Pair> chain = array.get(hash(key.hashCode()) & (size - 1));
        if (chain == null){
            return null;
        }
        for(Pair pair : chain){
            if (pair != null && pair.key.equals(key)){
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value){
        ArrayList<Pair> chain = array.get(hash(key.hashCode()) & (size - 1));
        if (chain != null){
            for (int i = 0; i < chain.size(); i++) {
                if(chain.get(i).key.equals(key)){
                    array.get(hash(key.hashCode()) & (size - 1)).set(i, new Pair(key, value));
                    return;
                }
            }
        }
        array.get(hash(key.hashCode()) & (size - 1)).add(new Pair(key, value));
        count++;
        load = (float)count/size;
        if (load >= 0.75){
            resize();
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key){
        ArrayList<Pair> chain = array.get(hash(key.hashCode()) & (size - 1));
        if (chain == null){
            return null;
        }
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key.equals(key)){
                Value value = chain.get(i).value;
                array.get(hash(key.hashCode()) & (size - 1)).remove(i);
                count--;
                return value;
            }
        }
        return null;
    }
    @Override
    public int size() { return count; }
    @Override
    public boolean isEmpty() { return count == 0; }
}
