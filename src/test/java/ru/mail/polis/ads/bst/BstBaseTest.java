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
    void remove() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i);
        }
        for (int i = 1; i <= elementsCount; i += 2) {
            assertEquals(i, bst.get(i));
            bst.remove(i);
            assertNull(bst.get(i));
            assertEquals(i + 1, bst.get(i + 1));
        }
        assertEquals(elementsCount / 2, bst.size());
        assertTrue(Math.abs(Math.log(elementsCount / 2)/Math.log(2) - bst.height()) <= 1.0001);
    }

    @Test
    void floor() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i);
        }
        bst.remove(elementsCount / 2);
        assertEquals(elementsCount / 2 - 1, bst.floor(elementsCount / 2));
    }

    @Test
    void ceil() {
        Bst<Integer, Integer> bst = new AvlBst<>();
        final int elementsCount = 1024;
        for (int i = 1; i <= elementsCount; ++i) {
            bst.put(i, i);
        }
        bst.remove(elementsCount / 2);
        assertEquals(elementsCount / 2 + 1, bst.ceil(elementsCount / 2));
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