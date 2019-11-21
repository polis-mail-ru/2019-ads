package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class implements a hash table, which maps keys to values. <p>
 *
 * To successfully store and retrieve objects from a hashtable, the
 * objects used as keys must implement the {@code hashCode}
 * method and the {@code equals} method. <p>
 *
 * An instance of {@code Hashtable} has two parameters that affect its
 * performance: <i>initial capacity</i> and <i>load factor</i>.  The
 * <i>capacity</i> is the number of <i>buckets</i> in the hash table, and the
 * <i>initial capacity</i> is simply the capacity at the time the hash table
 * is created. Note that the hash table is <i>open</i>: in the case of a "hash
 * collision", a single bucket stores multiple entries, which must be searched
 * sequentially.  The <i>load factor</i> is a measure of how full the hash
 * table is allowed to get before its capacity is automatically increased. <p>
 *
 * Generally, the default load factor (0.75) offers a good tradeoff between
 * time and space costs.  Higher values decrease the space overhead but
 * increase the time cost to look up an entry (which is reflected in most
 * {@code OwnHashTable} operations, including {@code get} and {@code put}).<p>
 *
 * The initial capacity controls a tradeoff between wasted space and the
 * need for {@code resize} operations, which are time-consuming.
 * No {@code resize} operations will <i>ever</i> occur if the initial
 * capacity is greater than the maximum number of entries the
 * {@code OwnHashTable} will contain divided by its load factor. However,
 * setting the initial capacity too high can waste space.<p>
 *
 * If many entries are to be made into a {@code Hashtable},
 * creating it with a sufficiently large capacity may allow the
 * entries to be inserted more efficiently than letting it perform
 * automatic rehashing as needed to grow the table. <p>
 *
 * @param <Key> the type of keys maintained by this map
 * @param <Value> the type of mapped values
 *
 * @author  Maschenko Bogdan
 * @see     Object#equals(java.lang.Object)
 * @see     Object#hashCode()
 * @see     OwnHashTable#resize()
 * @see     Collection
 * @see     Map
 * @see     HashMap
 * @see     HashTable
 */
public class OwnHashTable<Key, Value>
    implements HashTable<Key, Value> {

    /**
     * Basic hash bin node, used for entries.
     *
     * @param <Key> the type of keys maintained by this map
     * @param <Value> the type of mapped values
     */
    static class Node<Key, Value> implements Map.Entry<Key, Value> {
        final int hash;
        final Key key;
        Value value;
        Node<Key, Value> next;

        Node(int hash, Key key, Value value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public final Key getKey() {
            return key;
        }

        public final Value getValue() {
            return value;
        }

        public final String toString() {
            return key + " : " + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final Value setValue(Value value) {
            Value tempValue = this.value;
            this.value = value;
            return tempValue;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }

    /*------------------------- Static fields -------------------------*/

    /**
     * The default initial capacity - MUST be a power of two.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     */
    private static final int MAX_CAPACITY = (int)Math.pow(2, 30);

    /**
     * The load factor used when none specified in constructor.
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /*---------------------------- Fields ----------------------------*/

    /**
     * The default initial capacity - MUST be a power of two.
     */
    private int size;

    /**
     * The number of busy baskets in the hashtable.
     */
    private int capacity;

    /**
     * The total number of entries in the hash table.
     */
    private int count;

    /**
     * The load factor for the hashtable.
     */
    private final float loadFactor;

    /**
     * The hash table data.
     */
    private Node<Key, Value>[] table;

    /* ---------------- Static utilities -------------- */

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     */
    private static int hash(Object key) {
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    /*------------------------- Public operations -------------------------*/

    /**
     * Constructs an empty {@code OwnHashTable} with the specified initial
     * capacity and load factor.
     *
     * @param  capacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public OwnHashTable(int capacity, float loadFactor) {
        if (capacity < 0 || Float.isNaN(loadFactor) || loadFactor < 0 || capacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("Incorrect data");
        }
        if (capacity == 0) {
            this.capacity = 1;
        } else {
            this.capacity = capacity;
        }
        this.loadFactor = loadFactor;
        this.count = 0;
        this.table = (Node<Key, Value>[])new Node[capacity];
    }

    /**
     * Constructs an empty {@code OwnHashTable} with the default initial
     * capacity (16) and load factor.
     *
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial load factor is nonpositive
     */
    public OwnHashTable(float loadFactor) {
        this(DEFAULT_INITIAL_CAPACITY, loadFactor);
    }

    /**
     * Constructs an empty {@code OwnHashTable} with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  capacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public OwnHashTable(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty {@code OwnHashTable} with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public OwnHashTable() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this table contains no mapping for the key.
     *
     * <p>More formally, if this table contains a mapping from a key
     * {@code key} to a value {@code value} such that {@code (key.equals(node.key))},
     * then this method returns {@code value}; otherwise it returns
     * {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this table contains no mapping for the key
     *
     * @see #put(Key, Value)
     */
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hash(key);
        int index = hash % (table.length - 1);
        Node<Key, Value> node = table[index];
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * Maps the specified {@code key} to the specified
     * {@code value} in this hashtable. Neither the key nor the
     * value can be {@code null}. <p>
     *
     * The value can be retrieved by calling the {@code get} method
     * with a key that is equal to the original key.
     *
     * @param key the hashtable key
     * @param value the value

     * @see Key#equals(Key)
     * @see #get(Key)
     */
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key);
        int index = hash % (table.length - 1);
        Node<Key, Value> node = table[index];
        Node<Key, Value> tempNode = null;
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                node.value = value;
                return;
            }
            tempNode = node;
            node = node.next;
        }
        count++;
        if (tempNode == null) {
            table[index] = new Node<>(hash, key, value);
            size++;
            checkOverLoad();
        } else {
            tempNode.next = new Node<>(hash, key, value);
        }
    }

    /**
     * Removes the mapping for the specified key from this table if present.
     *
     * @param  key key whose mapping is to be removed from the table
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hash(key);
        int index = hash % (table.length - 1);
        Node<Key, Value> removedNode = remove(hash, key, index);
        return removedNode != null ? removedNode.value : null;
    }

    /**
     * Returns the number of key-value mappings in this table.
     *
     * @return the number of key-value mappings in this table
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns {@code true} if this table contains no key-value mappings.
     *
     * @return {@code true} if this table contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Removes all of the mappings from this table.
     * The table will be empty after this call returns.
     */
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        count = 0;
    }

    /*------------------------- Private operations -------------------------*/

    /**
     * Maps the specified {@code key} to the specified
     * {@code value} in this hashtable during {@code resize}.
     *
     * @param node added node from old table
     */
    private void putDuringResize(Node<Key, Value> node) {
        int index = node.hash % (table.length - 1);
        node.next = table[index];
        if (node.next == null) {
            size++;
        }
        table[index] = node;
    }

    /**
     * Implements HashTable.remove and related methods.
     *
     * @param hash hash for key
     * @param key the key which necessary remove
     * @param index number of the bucket in which may to be {@code key}
     * @return removed node or {@code null} if this {@code key} is absent
     */
    private Node<Key, Value> remove(int hash, Key key, int index) {
        Node<Key, Value> node = table[index];
        if (node == null) {
            return null;
        }
        Node<Key, Value> tempNode = null;

        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                count--;
                if (tempNode != null) {
                    tempNode.next = node.next;
                    node.next = null;
                    return node;
                } else {
                    tempNode = node.next;
                    node.next = null;
                    table[index] = tempNode;

                    if (tempNode == null) {
                        size--;
                    }
                    return node;
                }
            }
            tempNode = node;
            node = node.next;
        }
        return null;
    }

    /**
     * Checking table on overload and call {@code resize} if need.
     */
    private void checkOverLoad() {
        if (capacity * loadFactor < size) {
            resize();
        }
    }

    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     */
    private void resize() {
        if (capacity == MAX_CAPACITY) {
            return;
        }

        size = 0;
        if (capacity * 2 > MAX_CAPACITY) {
            capacity = MAX_CAPACITY;
        } else {
            capacity *= 2;
        }

        Node<Key, Value>[] oldTable = table;
        table = (Node<Key, Value>[])new Node[capacity];
        for (Node<Key, Value> node : oldTable) {
            while (node != null) {
                Node<Key, Value> tempNode = node.next;
                putDuringResize(node);
                node = tempNode;
            }
        }
    }
}
