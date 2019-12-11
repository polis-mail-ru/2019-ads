package ru.mail.polis.ads.bst.hash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.mail.polis.ads.hash.HashTableImpl;

import static org.junit.jupiter.api.Assertions.*;

class HashTableImplTest {

    private static HashTableImpl<String, String> t;

    @BeforeAll
    static void newTable() {
        t = new HashTableImpl<>();
    }

    @AfterEach
    void clearTable() {
        t.clear();
    }

    @Test
    void emptyTable() {
        assertNull(t.get(""));
        assertEquals(0, t.size());
    }

    @Test
    void put() {
        int size = 0;
        assertEquals(t.size(), size);
        assertNull(t.get("foo1"));

        t.put("foo1", "bar1");

        assertEquals(t.size(), ++size);
        assertEquals(t.get("foo1"), "bar1");

        t.put("foo2", "bar2");

        assertEquals(t.size(), ++size);
        assertEquals(t.get("foo2"), "bar2");

        t.put("foo3", "bar3");

        assertEquals(t.size(), ++size);
        assertEquals(t.get("foo3"), "bar3");

        t.put("foo", "bar");

        assertEquals(t.size(), ++size);
        assertEquals(t.get("foo"), "bar");
    }

    @Test
    void remove() {
        assertNull(t.remove("key that not stored in Hashtable"));
        assertTrue(t.isEmpty());

        t.put("foo2", "bar2");
        t.put("foo3", "bar3");
        t.put("foo4", "bar4");
        t.put("foo5", "bar5");
        t.put("foo1", "bar1");
        t.put("foo0", "bar0");

        assertFalse(t.isEmpty());
        int size = t.size();

        assertEquals(t.remove("foo4"), "bar4");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo4"));

        assertEquals(t.remove("foo1"), "bar1");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo1"));

        assertNull(t.remove("foo1"), "bar1");
        assertEquals(t.size(), size);
        assertFalse(t.isEmpty());
        assertFalse(t.containsKey("foo1"));

        assertEquals(t.remove("foo3"), "bar3");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo3"));

        assertEquals(t.remove("foo0"), "bar0");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo0"));

        assertEquals(t.remove("foo2"), "bar2");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo2"));

        assertEquals(t.remove("foo5"), "bar5");
        assertEquals(t.size(), --size);
        assertFalse(t.containsKey("foo5"));

        assertTrue(t.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(t.containsKey("foo"));
        assertFalse(t.containsKey("foo1"));

        t.put("foo", "bar");
        assertTrue(t.containsKey("foo"));
        assertFalse(t.containsKey("foo1"));

        t.put("foo1", "bar1");
        assertTrue(t.containsKey("foo1"));
        assertTrue(t.containsKey("foo"));

        t.remove("foo");
        assertTrue(t.containsKey("foo1"));
        assertFalse(t.containsKey("foo"));

        t.remove("foo1");
        assertFalse(t.containsKey("foo"));
        assertFalse(t.containsKey("foo1"));
    }

    @Test
    void empty() {
        assertTrue(t.isEmpty());

        t.put("foo", "bar");
        assertFalse(t.isEmpty());

        t.put("foo1", "bar1");
        assertFalse(t.isEmpty());

        t.remove("foo");
        assertFalse(t.isEmpty());

        t.remove("foo1");
        assertTrue(t.isEmpty());
    }
}
