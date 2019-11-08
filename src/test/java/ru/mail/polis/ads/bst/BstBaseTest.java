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

    Bst<Integer, String> newIntBst() {
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
    void putMany() {
        Bst<String, String> bst = newBst();
        bst.put("1", "tar");
        bst.put("2", "bar");
        bst.put("3", "var");
        bst.put("0", "sar");
        bst.put("5", "zar");
        bst.put("66", "car");
        bst.put("36", "par");


        assertEquals(7, bst.size());
        assertEquals("sar", bst.get("0"));
        assertEquals(4, bst.height());

        bst.remove("0");
        assertNull(bst.get("0"));

    }


    @Test
    void balancing() {
        Bst<Integer, String> bst = newIntBst();
        for (int i = 1; i <= 15; i++) {
            bst.put(i, "smth");
        }

        assertEquals(4, bst.height());
    }

    @Test
    void remove() {
        Bst<String, String> bst = newBst();
        for (int i = 1; i <= 5; i++) {
            bst.put(String.valueOf(i), "s" + i);
        }

        bst.remove("2");
        assertEquals("s5", bst.get("5"));
    }
    
}