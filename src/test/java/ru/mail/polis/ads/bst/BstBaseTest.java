package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Basic binary search tree invariants.
 */
class BstBaseTest {
    
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
        //assertEquals(1, bst.height());
    }

    @Test
    void replace() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.put("foo", "bee");


        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
        //assertEquals(1, bst.height());
    }

    @Test
    void remove() {
        Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.remove("foo");


        assertEquals(null, bst.get("foo"));

        assertEquals(0, bst.size());

        bst.put("foo", "bar");
        bst.put("foo1", "bar1");
        bst.put("foo2", "bar2");
        bst.put("foo3", "bar3");
        bst.put("foo4", "bar4");
        bst.put("foo5", "bar5");
        bst.put("foo6", "bar6");

        assertEquals(7, bst.size());



        //assertEquals(5, bst.size());
        //assertEquals(1, bst.height());
    }

}