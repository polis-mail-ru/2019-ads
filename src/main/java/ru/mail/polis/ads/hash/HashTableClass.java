package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HashTableClass<Key, Value> implements HashTable<Key, Value> {

    private static final int BASE_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Node[] table;
    private int size;
    private int capacity = BASE_CAPACITY;

    public HashTableClass(){
        this.size = 0;
        this.table = new Node[BASE_CAPACITY];
    }

    private class Node<Key, Value> {
        final Key key;
        final int hash;
        Value value;
        Node next;

        Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        @Override
        public boolean equals(Object o) {
                return Objects.equals(this.key, ((Node) o).key) &&
                        Objects.equals(this.value, ((Node) o).value);
        }
    }

    private int hash(Key key) {
        int h;
        return (key == null) ? 0 : (((h = key.hashCode()) ^ (h >>> 16)) % table.length);
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        if (table[hash]  == null)
            return null;
        else{
            Node entry = table[hash];
            while (entry!=null && !entry.key.equals(key))
                entry = entry.next;
            if (entry == null)
                return null;
            else
                return (Value)entry.value;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node(key, value, hash(key)));
        resize();
    }

    private void put(Node node) {
       int hash = node.hash;
       if (table[hash] == null)
        {
            table[hash] = node;
            size++;
        }
       else{
            Node entry = table[hash];
           while (entry.next != null && !entry.key.equals(node.key))
               entry = entry.next;
           if (entry.key.equals(node.key))
               entry.value = node.value;
           else {
               entry.next = node;
               size++;
           }
        }
    }

    private void resize() {
        if ((capacity * LOAD_FACTOR) < size) {
            capacity = capacity * 2;
            Node[] prevTable = table;
            table = new Node[capacity];
            for (Node node : prevTable) {
                put(node);
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        if (table[hash] != null){
            Node prevEntry = null;
            Node entry = table[hash];
            while(entry.next != null && !entry.key.equals(key)){
                prevEntry = entry;
                entry = entry.next;
            }
            if (entry.key.equals(key)){
                size--;
                if (prevEntry == null) {
                    table[hash] = entry.next;
                    return (Value) entry.value;
                }
                else {
                    prevEntry.next = entry.next;
                    return (Value) entry.value;
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
