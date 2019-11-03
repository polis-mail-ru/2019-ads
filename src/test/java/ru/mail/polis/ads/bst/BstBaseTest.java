package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic binary search tree invariants.
 */
class BstBaseTest {
    
    Bst<String, String> newBst() {
        return new AvlBst<>();
    } 
    
    @Test
    void emptyBst() {
        Bst<String, String> bst = newBst();
        assertNull(bst.get(""));
        assertNull(bst.get("some key"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }

    @Test
    void orderedOnEmpty() {
        Bst<String, String> bst = newBst();
        assertNull(bst.ceil("some key"));
        assertNull(bst.floor("some key"));

        assertNull(bst.min());
        assertNull(bst.max());

        assertNull(bst.minValue());
        assertNull(bst.maxValue());
    }

    @Test
    void orderedOnNotEmpty() {
        Bst<String, String> bst = newBst();

        bst.put("key1", "value1");
        bst.put("key2", "value2");
        bst.put("key3", "value3");
        bst.put("key4", "value4");
        bst.put("key5", "value5");

        assertEquals("key2", bst.ceil("key2"));
        assertEquals("key4", bst.ceil("key32"));
        assertEquals("key1", bst.ceil("key0"));
        assertEquals("key3", bst.ceil("key21"));
        assertEquals("key5", bst.ceil("key45"));
        assertNull(bst.ceil("key68"));

        assertEquals("key5", bst.floor("key5"));
        assertEquals("key3", bst.floor("key32"));
        assertEquals("key4", bst.floor("key45"));
        assertEquals("key5", bst.floor("key68"));
        assertEquals("key2", bst.floor("key21"));
        assertNull(bst.floor("key0"));

        assertEquals("key1", bst.min());
        assertEquals("key5", bst.max());

        assertEquals("value1", bst.minValue());
        assertEquals("value5", bst.maxValue());
    }

    @Test
    void put() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        
        assertEquals("bar", bst.get("foo"));
        
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());

        bst.put("key1", "value1");
        bst.put("key2", "value2");
        bst.put("key2", "value3");

        assertNull(bst.get("value1"));
        assertNull(bst.get("value3"));

        assertEquals("value1", bst.get("key1"));
        assertEquals("value3", bst.get("key2"));

        assertEquals(3, bst.size());
        assertEquals(2, bst.height());

        bst.put("key3", "value4");
        bst.put("key4", "value5");

        assertNull(bst.get("value4"));
        assertNull(bst.get("value5"));

        assertEquals("value4", bst.get("key3"));
        assertEquals("value5", bst.get("key4"));

        assertEquals(5, bst.size());
        assertEquals(3, bst.height());
    }

    @Test
    void replace() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.put("foo", "bee");

        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }
}