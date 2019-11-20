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
    void balance() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i);
        }
        assertTrue(Math.abs(Math.log(elementsCount)/Math.log(2) - bst.height()) <= 1.0001);
    }

    @Test
    void remove() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 4;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i);
        }
        for (int i = 1; i <= elementsCount; i += 2) {
            assertEquals(i, bst.get(i));
            assertEquals(i, bst.remove(i));
            assertNull(bst.get(i));
            assertEquals(i + 1, bst.get(i + 1));
        }
        assertEquals(elementsCount / 2, bst.size());

    }
    
    @Test
    void max() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i * 1000);
        }
        assertEquals(elementsCount, bst.max());
        assertEquals(elementsCount * 1000, bst.maxValue());
    }

    @Test
    void min() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i * 1000);
        }
        assertEquals(1, bst.min());
        assertEquals(1 * 1000, bst.minValue());
    }
    
}