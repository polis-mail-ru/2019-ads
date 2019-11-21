package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;

public class MyHashTable<Key, Value> implements HashTable<Key, Value> {
    int capacity = 16;
    ArrayList<ArrayList<Pair>> arr = new ArrayList<>();
    int n = 0;
    double loadFactor = 0;

    MyHashTable(){
        for (int i = 0; i < capacity; i++){
            arr.add(new ArrayList<Pair>());
        }
    }
    class Pair{
        Key key;
        Value value;
        int h;
        Pair(Key key, Value value){
            this.key = key;
            this.value = value;
            h = key.hashCode();
        }
    }
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int h = key.hashCode();
        ArrayList<Pair> lst = arr.get(hash(h) & (capacity-1));
        if (lst == null){
            return null;
        }
        for (Pair pair : lst) {
            if (pair != null && pair.h == h) {
                return pair.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int h = key.hashCode();
        ArrayList<Pair> lst= arr.get(hash(h)& (capacity-1));

        for (int i = 0; i < lst.size(); i++){
            if (lst.get(i).h == h){
                arr.get(hash(h)& (capacity-1)).set(i, new Pair(key, value));
                return;
            }

        }
        arr.get(hash(h)& (capacity-1)).add(new Pair(key, value));
        n+=1;

        loadFactor = (double)n/capacity;

        if (loadFactor >= 0.75){
            resize();
        }

    }


    private void resize() {
        capacity = capacity >> 1;
        ArrayList<ArrayList<Pair>> arr_new = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++){
            arr_new.add(new ArrayList<Pair>());
        }
        for (int i = 0; i < arr.size(); i++){
            for (Pair j: arr.get(i)) {
                int h = j.key.hashCode();
                arr_new.get(hash(h) & (capacity-1)).add(j);
            }
        }
        loadFactor = (double)n/capacity;
        arr = arr_new;

    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int h = key.hashCode();
        ArrayList<Pair> lst = arr.get(hash(h) & (capacity-1));
        if (lst == null){
            return null;
        }
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i).h == h){
                Value res = lst.get(i).value;
                arr.get(hash(h) & (capacity-1)).remove(i);
                n-=1;
                return res;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }
    static int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
}
