package test.java.ru.mail.polis.ads.bst;

import main.java.ru.mail.polis.ads.bst.AvlBst;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic binary search tree invariants.
 */

class BstBaseTest {

    main.java.ru.mail.polis.ads.bst.AvlBst<String, String> newBst() {
        return new AvlBst<>();
    } 
    
    @Test
    void emptyBst() {
        main.java.ru.mail.polis.ads.bst.Bst<String, String> bst = newBst();
        assertNull(bst.get(""));
        assertNull(bst.get("some key"));
        assertEquals(0, bst.size());
        assertEquals(0, bst.height());
    }

    @Test
    void orderedOnEmpty() {
        main.java.ru.mail.polis.ads.bst.Bst<String, String> bst = newBst();
        assertNull(bst.ceil("some key"));
        assertNull(bst.floor("some key"));

        assertNull(bst.min());
        assertNull(bst.max());

        assertNull(bst.minValue());
        assertNull(bst.maxValue());
    }

    @Test
    void put() {
        main.java.ru.mail.polis.ads.bst.Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        
        assertEquals("bar", bst.get("foo"));
        
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }

    @Test
    void replace() {
        main.java.ru.mail.polis.ads.bst.Bst<String, String> bst = newBst();
        bst.put("foo", "bar");
        bst.put("foo", "bee");

        assertEquals("bee", bst.get("foo"));

        assertEquals(1, bst.size());
        assertEquals(1, bst.height());
    }
}