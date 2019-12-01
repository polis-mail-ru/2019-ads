package ru.mail.polis.ads.hash;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {
  @Override
  public Value get(Key key) {
    final LinkedList<HashTableItem> items = buckets[getBucketIndex(key.hashCode())];
    if (!items.isEmpty())
      for (HashTableItem item : items)
        if (key.equals(item.key))
          return item.value;
    return null;
  }


  @Override
  public void put(Key key, Value value) {
    final LinkedList<HashTableItem> items = buckets[getBucketIndex(key.hashCode())];
    if (!containsKey(key)) {
      ++size;
      if ((double) size / primes[currentPrimeIndex] > LOAD_FACTOR && currentPrimeIndex + 1 < MAX_DEGREE)
        rehash();
    }
    for (HashTableItem item : items)
      if (key.equals(item.key)) {
        item.value = value;
        return;
      }
    items.addLast(new HashTableItem(key, value));
  }

  private void rehash() {
    LinkedList<HashTableItem>[] currentBuckets = buckets;
    getNextPrime();
    buckets = new LinkedList[primes[++currentPrimeIndex]];
    for (int i = 0; i < currentBuckets.length; ++i) {
      LinkedList<HashTableItem> bucket = currentBuckets[i];
      if (bucket != null)
        for (HashTableItem item : bucket)
          put(item.key, item.value);
    }
  }


  @Override
  public Value remove(Key key) {
    final LinkedList<HashTableItem> items = buckets[getBucketIndex(key.hashCode())];
    if (items.isEmpty())
      return null;
    Iterator<HashTableItem> it  = items.iterator();
    HashTableItem item = null;
    while (it.hasNext()) {
      item = it.next();
      if (key.equals(item.key)) {
        it.remove();
        --size;
        break;
      }
    }
    return item == null ? null : item.value;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  private class HashTableItem {
    Key key;
    Value value;

    HashTableItem(Key key, Value value) {
      this.key = key;
      this.value = value;
    }
  }

  private static final double LOAD_FACTOR = 0.75;
  private static final int INITIAL_DEGREE = 4;
  private static final int MAX_DEGREE = 28;
  private void getNextPrime() {
    //test for 0 added to escape recalculation for each object of class if it have already been initialized
    if (primes[currentPrimeIndex] == 0 && currentPrimeIndex <= MAX_DEGREE) {
      int currentNum;
      currentNum = (int) Math.pow(2, INITIAL_DEGREE + currentPrimeIndex) + 1;
      while (!isPrime(currentNum))
        ++currentNum;
      primes[currentPrimeIndex] = currentNum;
    }
  }

  private static boolean isPrime(int n) {
    if (n <= 1)
      return false;
    int limit = (int) Math.sqrt(n);
    for (int i = 2; i <= limit; ++i)
      if (n % i == 0)
        return false;
    return true;
  }

  private int getBucketIndex(int hash) {
    return (hash & 0x7fffffff) % primes[currentPrimeIndex];
  }

  private final static int[] primes = new int[MAX_DEGREE];
  private int size;
  private int currentPrimeIndex;
  private LinkedList<HashTableItem>[] buckets;

  public HashTableImpl() {
    currentPrimeIndex = 0;
    size = 0;
    getNextPrime();
    buckets = new LinkedList[primes[currentPrimeIndex]];
    for (int i = 0; i < buckets.length; ++i)
      buckets[i] = new LinkedList<>();
  }
}
