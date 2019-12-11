package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic binary search tree invariants.
 */
class RedBlackBstTest {

    Bst<String, String> newBst() {
        return new RedBlackBst<>();
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
        
        assertNull(bst.remove("not stored key"));
        assertTrue(bst.isEmpty());

        bst.put("foo3", "bar3");
        bst.put("foo4", "bar4");
        bst.put("foo2", "bar2");
        bst.put("foo5", "bar5");
        bst.put("foo1", "bar1");
        bst.put("foo0", "bar0");

        assertFalse(bst.isEmpty());
        int size = bst.size();

        assertEquals(bst.remove("foo4"), "bar4");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo4"));

        assertEquals(bst.remove("foo1"), "bar1");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo1"));

        assertNull(bst.remove("foo1"), "bar1");
        assertEquals(bst.size(), size);
        assertFalse(bst.isEmpty());
        assertFalse(bst.containsKey("foo1"));

        assertEquals(bst.remove("foo3"), "bar3");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo3"));

        assertEquals(bst.remove("foo0"), "bar0");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo0"));

        assertEquals(bst.remove("foo2"), "bar2");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo2"));

        assertEquals(bst.remove("foo5"), "bar5");
        assertEquals(bst.size(), --size);
        assertFalse(bst.containsKey("foo5"));

        assertTrue(bst.isEmpty());
    }

    @Test
    void max() {
        Bst<String, String> bst = newBst();

        assertNull(bst.max());
        assertNull(bst.maxValue());

        bst.put("foo2", "bar2");

        assertEquals(bst.max(), "foo2");
        assertEquals(bst.maxValue(), "bar2");

        bst.put("foo5", "bar5");

        assertEquals(bst.max(), "foo5");
        assertEquals(bst.maxValue(), "bar5");

        bst.put("foo0", "bar0");

        assertEquals(bst.max(), "foo5");
        assertEquals(bst.maxValue(), "bar5");

        bst.put("foo7", "bar7");

        assertEquals(bst.max(), "foo7");
        assertEquals(bst.maxValue(), "bar7");

        bst.remove("foo5");

        assertEquals(bst.max(), "foo7");
        assertEquals(bst.maxValue(), "bar7");

        bst.remove("foo7");

        assertEquals(bst.max(), "foo2");
        assertEquals(bst.maxValue(), "bar2");

        bst.remove("foo0");

        assertEquals(bst.max(), "foo2");
        assertEquals(bst.maxValue(), "bar2");

        bst.remove("foo2");

        assertNull(bst.max());
        assertNull(bst.maxValue());
    }

    @Test
    void min() {
        Bst<String, String> bst = newBst();

        assertNull(bst.min());
        assertNull(bst.minValue());

        bst.put("foo5", "bar5");

        assertEquals(bst.min(), "foo5");
        assertEquals(bst.minValue(), "bar5");

        bst.put("foo3", "bar3");

        assertEquals(bst.min(), "foo3");
        assertEquals(bst.minValue(), "bar3");

        bst.put("foo9", "bar9");

        assertEquals(bst.min(), "foo3");
        assertEquals(bst.minValue(), "bar3");

        bst.put("foo0", "bar0");

        assertEquals(bst.min(), "foo0");
        assertEquals(bst.minValue(), "bar0");

        bst.remove("foo5");

        assertEquals(bst.min(), "foo0");
        assertEquals(bst.minValue(), "bar0");

        bst.remove("foo0");

        assertEquals(bst.min(), "foo3");
        assertEquals(bst.minValue(), "bar3");

        bst.remove("foo9");

        assertEquals(bst.min(), "foo3");
        assertEquals(bst.minValue(), "bar3");

        bst.remove("foo3");

        assertNull(bst.min());
        assertNull(bst.minValue());
    }

    @Test
    void contains() {
        Bst<String, String> bst = newBst();

        assertFalse(bst.containsKey("foo"));
        assertFalse(bst.containsKey("foo1"));

        bst.put("foo", "bar");
        assertTrue(bst.containsKey("foo"));
        assertFalse(bst.containsKey("foo1"));

        bst.put("foo1", "bar1");
        assertTrue(bst.containsKey("foo1"));
        assertTrue(bst.containsKey("foo"));

        bst.remove("foo");
        assertTrue(bst.containsKey("foo1"));
        assertFalse(bst.containsKey("foo"));

        bst.remove("foo1");
        assertFalse(bst.containsKey("foo"));
        assertFalse(bst.containsKey("foo1"));
    }

    @Test
    void ceil() {
        Bst<String, String> bst = newBst();

        bst.put("1", "bar3");
        bst.put("3", "bar4");
        bst.put("5", "bar2");
        bst.put("7", "bar5");
        bst.put("8", "bar1");
        bst.put("9", "bar0");
        bst.put("2", "bar0");

        assertEquals(bst.min(), "1");
        assertEquals(bst.max(), "9");

        assertEquals(bst.ceil("5"), "5");
        assertEquals(bst.ceil("2"), "2");
        assertEquals(bst.ceil("8"), "8");
        assertEquals(bst.ceil("0"), "1");
        assertEquals(bst.ceil("9"), "9");
        assertNull(bst.ceil("99"));
    }

    @Test
    void floor() {
        Bst<String, String> bst = newBst();

        bst.put("1", "bar3");
        bst.put("3", "bar4");
        bst.put("5", "bar2");
        bst.put("7", "bar5");
        bst.put("8", "bar1");
        bst.put("9", "bar0");
        bst.put("2", "bar0");

        assertEquals(bst.min(), "1");
        assertEquals(bst.max(), "9");

        assertEquals(bst.floor("5"), "5");
        assertEquals(bst.floor("4"), "3");
        assertEquals(bst.floor("8"), "8");
        assertEquals(bst.floor("1"), "1");
        assertEquals(bst.floor("99"), "9");
        assertNull(bst.floor(""));
    }
}