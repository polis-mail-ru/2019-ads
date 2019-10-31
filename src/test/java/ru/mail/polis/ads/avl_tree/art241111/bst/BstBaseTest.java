package ru.mail.polis.ads.avl_tree.art241111.bst;

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
    void put() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");

        assertEquals("bar", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
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

    @Test
    void remove() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.put("foo2", "bee");

        assertEquals("bar", bst.remove("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }


    @Test
    void floor() {
        Bst<String, String> bst = newBst();
        bst.put("foo1", "bee");
        bst.put("foo6", "bee");
        bst.put("foo3", "bee");
        bst.put("foo7", "bee");
        bst.put("foo2", "bee");
        bst.put("foo4", "bee");
        bst.put("foo9", "bee");

        assertEquals("foo4", bst.floor("foo5"));
        assertEquals("foo7", bst.floor("foo7"));
        assertEquals("foo9", bst.floor("foo999"));
        assertEquals("foo2", bst.floor("foo2"));
        assertEquals("foo1", bst.floor("foo1"));
        assertEquals("foo7", bst.floor("foo8"));
        assertNull(bst.floor("foo"));
    }

    @Test
    void ceil() {
        Bst<String, String> bst = newBst();
        bst.put("foo1", "bee");
        bst.put("foo6", "bee");
        bst.put("foo3", "bee");
        bst.put("foo7", "bee");
        bst.put("foo2", "bee");
        bst.put("foo4", "bee");
        bst.put("foo9", "bee");

        assertEquals("foo6", bst.ceil("foo5"));
        assertEquals("foo7", bst.ceil("foo7"));
        assertNull(bst.ceil("foo999"));
        assertEquals("foo2", bst.ceil("foo2"));
        assertEquals("foo1", bst.ceil("foo1"));
        assertEquals("foo9", bst.ceil("foo8"));
        assertEquals("foo1", bst.ceil("foo"));
    }
}