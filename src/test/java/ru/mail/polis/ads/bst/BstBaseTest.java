package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic binary search tree invariants.
 */
class BstBaseTest {

    private Bst<String, String> bst;

    @BeforeEach
    void setUp() {
        bst = new AvlBst<>();
    }

    @Test
    void emptyBst() {
        assertNull(bst.get(""));
        assertNull(bst.get("some key"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }

    @Test
    void orderedOnEmpty() {
        assertNull(bst.ceil("some key"));
        assertNull(bst.floor("some key"));

        assertNull(bst.min());
        assertNull(bst.max());

        assertNull(bst.minValue());
        assertNull(bst.maxValue());
    }

    @Test
    void put() {
        bst.put("foo", "bar");
        
        assertEquals("bar", bst.get("foo"));
        assertEquals(1, bst.size());

        bst.put("key1", "value1");

        assertEquals("bar", bst.get("foo"));
        assertEquals("value1", bst.get("key1"));
        assertEquals(2, bst.size());

        bst.put("key2", "value2");

        assertEquals("bar", bst.get("foo"));
        assertEquals("value1", bst.get("key1"));
        assertEquals("value2", bst.get("key2"));
        assertEquals(3, bst.size());

        bst.put("key3", "value3");

        assertEquals("bar", bst.get("foo"));
        assertEquals("value1", bst.get("key1"));
        assertEquals("value2", bst.get("key2"));
        assertEquals("value3", bst.get("key3"));
        assertEquals(4, bst.size());
    }

    @Test
    void put_TreeBalancesItself() {
        bst.put("key1", "value");
        assertEquals(1, bst.height());

        bst.put("key2", "value");
        assertEquals(2, bst.height());

        bst.put("key3", "value");
        assertEquals(2, bst.height());

        bst.put("key4", "value");
        assertEquals(3, bst.height());

        bst.remove("key2");
        assertEquals(2, bst.height());
    }

    @Test
    void get() {
        assertNull(bst.get("non-existent"));

        bst.put("key", "value");

        assertNull(bst.get("non-existent"));
        assertEquals("value", bst.get("key"));
    }

    @Test
    void replace() {
        bst.put("foo", "bar");
        bst.put("foo", "bee");

        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
    }

    @Test
    void remove() {
        assertNull(bst.remove("non-existent"));

        bst.put("key1", "value1");
        bst.put("key2", "value2");

        assertEquals("value2", bst.remove("key2"));
        assertEquals(1, bst.size());
        assertNull(bst.get("key2"));
        assertEquals("value1", bst.get("key1"));

        bst.put("key2", "value2");

        assertEquals("value1", bst.remove("key1"));
        assertEquals(1, bst.size());
        assertNull(bst.get("key1"));
        assertEquals("value2", bst.get("key2"));

        assertEquals("value2", bst.remove("key2"));
        assertEquals(0, bst.size());

        bst.put("key2", "value2");
        bst.put("key1", "value1");

        assertEquals("value2", bst.remove("key2"));
        assertEquals(1, bst.size());
        assertNull(bst.get("key2"));
        assertEquals("value1", bst.get("key1"));

        assertEquals("value1", bst.remove("key1"));
        assertEquals(0, bst.size());
        assertNull(bst.get("key1"));

    }

    @Test
    void min() {
        assertNull(bst.min());

        bst.put("key3", "value3");

        assertEquals("key3", bst.min());
        assertEquals("value3", bst.minValue());

        bst.put("key1", "value1");

        assertEquals("key1", bst.min());
        assertEquals("value1", bst.minValue());

        bst.put("key2", "value2");

        assertEquals("key1", bst.min());
        assertEquals("value1", bst.minValue());
    }

    @Test
    void max() {
        assertNull(bst.max());

        bst.put("key1", "value1");

        assertEquals("key1", bst.max());
        assertEquals("value1", bst.maxValue());

        bst.put("key3", "value3");

        assertEquals("key3", bst.max());
        assertEquals("value3", bst.maxValue());

        bst.put("key2", "value2");

        assertEquals("key3", bst.max());
        assertEquals("value3", bst.maxValue());
    }

    @Test
    void floor() {
        assertNull(bst.floor("key"));

        bst.put("key1", "value1");
        bst.put("key2", "value2");

        assertNull(bst.floor("key0"));
        assertEquals("key1", bst.floor("key1"));
        assertEquals("key2", bst.floor("key3"));
    }

    @Test
    void ceil() {
        assertNull(bst.ceil("key"));

        bst.put("key1", "value1");
        bst.put("key2", "value2");

        assertNull(bst.ceil("key3"));
        assertEquals("key2", bst.ceil("key2"));
        assertEquals("key1", bst.ceil("key0"));
    }
}