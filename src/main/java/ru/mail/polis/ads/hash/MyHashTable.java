package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class MyHashTable<Key extends Comparable<Key>,Value> implements HashTable<Key,Value>{

    private Node<Key,Value>[] hashTable;
    private final int DEFAULT_CAPASITY = 16;
    private final double LOAD_FACTOR = 0.75;
    private int size;

    MyHashTable(){
        this.size = 0;
        this.hashTable = new Node[DEFAULT_CAPASITY];
    }

    private class Node<Key,Value>{
        private Key key;
        private Value value;
        private int hash;
        Node<Key,Value> next;

        Node(Key key, Value value, int hash){
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    private int hash(Key key){
        int h;
        return ((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)) % hashTable.length;
    }

    private void rehash(){
        if (hashTable.length*LOAD_FACTOR < size ){
            Node<Key, Value>[] oldHashTable = hashTable;
            hashTable = new Node[hashTable.length*2];
            for (Node<Key, Value> node: oldHashTable) {
                while (node != null){
                    Node<Key, Value> next = node.next;
                    put(node);
                    size--;
                    node = next;
                }
            }
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        Node<Key, Value> node = hashTable[hash];

        while (node != null){
            if (key.equals(node.key))
                return node.value;
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node(key, value, hash(key)));
        rehash();
    }

    private void put(Node<Key,Value> nodeIn) {
        Node<Key, Value> node = hashTable[nodeIn.hash];

        if (node == null){
            hashTable[nodeIn.hash] = nodeIn;
            size++;
            return;
        }
        Node<Key,Value> prevNode;
        do {
            if (nodeIn.key.equals(node.key)){
                node.value = nodeIn.value;
                return;
            }
            prevNode = node;
            node = node.next;
        } while (prevNode.next != null);
        prevNode.next = nodeIn;
        size++;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        Node<Key, Value> delNode = hashTable[hash];
        Node<Key, Value> prevNode = null;

        while (delNode != null) {
            if (delNode.key.equals(key)){
                size--;
                if (prevNode == null){
                    hashTable[hash] = delNode.next;
                    return delNode.value;
                } else {
                    prevNode.next = delNode.next;
                    return delNode.value;
                }
            }
            prevNode = delNode;
            delNode = delNode.next;
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

    @Override
    public int len(){
        return hashTable.length;
    }
}
