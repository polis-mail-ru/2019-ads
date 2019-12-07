package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class MyHashTable implements HashTable {

    final static double loadFactor = 0.75;
    int capacity;
    int size;

    List<Entry>[] table;

    public MyHashTable() {
        size = 0;
        capacity = 16;
        table = new LinkedList[capacity];
        initTable();
    }

    private void initTable() {
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Nullable
    @Override
    public Object get(@NotNull Object key) {
        for (Entry en : table[getIndex(key)]) {
            if (en.getKey().equals(key)) {
                return en.getValue();
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Object key, @NotNull Object value) {
        needResize();
        List<Entry> tmpList = table[getIndex(key)];
        for (Entry en : tmpList) {
            if (en.getKey().equals(key)) {
                en.setValue(value);
                return;
            }
        }
        size++;
        tmpList.add(new Entry(key, value));
    }

    private void put(Entry entry) {
        put(entry.getKey(), entry.getValue());
    }

    @Nullable
    @Override
    public Object remove(@NotNull Object key) {
        for (Entry en : table[getIndex(key)]) {
            if (en.getKey().equals(key)) {
                size--;
                table[getIndex(key)].remove(en);
                return en.getValue();
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

    private boolean needResize() {
        if (size > capacity * loadFactor) {
            resize();
            return true;
        }
        return false;
    }

    private void resize() {
        if (size() > capacity * loadFactor) {
            capacity *= 2;
            List<Entry>[] temp = table;
            table = new List[capacity];
            initTable();
            for (int i = 0; i < temp.length; i++) {
                for (Entry ent : temp[i]) {
                    put(ent);
                }
            }
        }
    }

    private int getIndex(Object o) {
        return hash(o) & (capacity - 1);
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
