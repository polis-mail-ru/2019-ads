package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MyHashTable<Key, Value> implements HashTable<Key, Value> {

    private Node<Key,Value>[] hashTable;
    private final int DEFAULT_CAPACITY = 16;
    private final double LOAD_FACTOR = 0.75;
    private int size;

    public MyHashTable(){
        this.size = 0;
        this.hashTable = new Node[DEFAULT_CAPACITY];
    }

    private static class Node<Key,Value>{
        private Key key;
        private Value value;
        private int hash;
        Node<Key,Value> next;

        Node(Key key, Value value, int hash){
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        Node<Key, Value> setHash(int hash) {
            this.hash = hash;
            return this;
        }
    }

    private int hash(Key key){
        int h;
        return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) % hashTable.length;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node<Key, Value> node = hashTable[hash(key)];

        while (node != null && !key.equals(node.key)){
            node = node.next;
        }

        return node == null ? null : node.value;
    }

    private void rehash(){
        if (hashTable.length*LOAD_FACTOR < size ){
            Node<Key, Value>[] oldHashTable = hashTable;
            hashTable = new Node[hashTable.length*2];
            for (Node<Key, Value> node: oldHashTable) {
                for (;node != null; node = node.next) {
                    put(node.setHash(hash(node.key)));
                }
            }
        }
    }

    private int put(Node<Key,Value> newNode) {
        Node<Key, Value> node = hashTable[newNode.hash];
        Node<Key,Value> prevNode = null;

        while (node != null && !node.key.equals(newNode.key)) {
            prevNode = node;
            node = node.next;
        }

        if (node == null) {
            if (prevNode == null) {
                hashTable[newNode.hash] = newNode;
            } else {
                prevNode.next = newNode;
            }
        } else {
            node.value = newNode.value;
            return 0;
        }

        return 1;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        size += put(new Node<>(key, value, hash(key)));
        rehash();
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        Node<Key, Value> node = hashTable[hash];
        Node<Key, Value> prevNode = null;

        while (node != null && !node.key.equals(key)) {
            prevNode = node;
            node = node.next;
        }

        if (node != null) {
            if (prevNode == null){
                hashTable[hash] = node.next;
            } else {
                prevNode.next = node.next;
            }
            size--;
        }

        return node == null ? null : node.value;
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
