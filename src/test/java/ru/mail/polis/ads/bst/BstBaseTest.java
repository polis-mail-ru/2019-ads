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
    void rightRotation() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(50, "50");
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());

        bst.put(25, "25");
        assertEquals(2, bst.height());
        assertEquals(2, bst.size());

        bst.put(12, "12");
        assertEquals(2, bst.height());
        assertEquals(3, bst.size());

        bst.put(6, "6");
        bst.put(3, "3");;
        assertEquals(3, bst.height());
        assertEquals(5, bst.size());
    }

    @Test
    void leftRotation() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(50, "50");
        assertEquals(1, bst.size());
        assertEquals(1, bst.height());

        bst.put(75, "75");
        assertEquals(2, bst.height());
        assertEquals(2, bst.size());

        bst.put(87, "87");
        assertEquals(2, bst.height());
        assertEquals(3, bst.size());

        bst.put(94, "94");
        bst.put(97, "97");
        assertEquals(3, bst.height());
        assertEquals(5, bst.size());
    }

    @Test
    void leftRightRotation() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(50, "50");
        bst.put(25, "25");
        bst.put(37, "37");
        assertEquals(2, bst.height());
        assertEquals(3, bst.size());

        bst.put(26, "26");
        bst.put(27, "27");
        assertEquals(3, bst.height());
        assertEquals(5, bst.size());
    }

    @Test
    void rightLeftRotation() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(50, "50");
        bst.put(75, "75");
        bst.put(62, "62");
        assertEquals(3, bst.size());
        assertEquals(2, bst.height());

        bst.put(74, "74");
        bst.put(73, "73");
        assertEquals(5, bst.size());
        assertEquals(3, bst.height());
    }
    
    @Test
    void remove() {
        Bst<Integer, String> bst = new AvlBst<>();
        bst.put(50, "s");
        bst.put(25, "r");
        bst.put(75,"w");
        bst.put(12,"y");
        bst.put(36,"u");
        bst.put(62,"t");
        bst.put(97,"v");

        //no children
        bst.remove(97);
        assertNull(bst.get(97));
        assertEquals(6, bst.size());
        // one child
        bst.remove(75);
        assertNull(bst.get(75));
        assertEquals(5, bst.size());
        //two children
        bst.remove(25);
        assertNull(bst.get(25));
        assertEquals(4, bst.size());
        //head
        bst.remove(50);
        assertNull(bst.get(50));
        assertEquals(3, bst.size());
        assertEquals(2, bst.height());
    }

    @Test
    void min() {
        Bst<Integer, String> bst = new AvlBst<>();

        assertNull(bst.min());

        bst.put(10, "10");
        assertEquals(10, bst.min());

        bst.put(20, "20");
        bst.put(9, "9");
        assertEquals(9, bst.min());
    }

    @Test
    void minValue() {
        Bst<Integer, String> bst = new AvlBst<>();

        assertNull(bst.min());

        bst.put(10, "100");
        bst.put(11, "110");
        bst.put(9, "0");
        assertEquals("0", bst.minValue());
    }

    @Test
    void max() {
        Bst<Integer, String> bst = new AvlBst<>();

        assertNull(bst.max());

        bst.put(10, "10");
        assertEquals(10, bst.max());

        bst.put(5, "5");
        bst.put(20, "20");
        assertEquals(20, bst.max());

        bst.put(30, "30");
        assertEquals(30, bst.max());
    }

    @Test
    void maxValue() {
        Bst<Integer, String> bst = new AvlBst<>();

        assertNull(bst.maxValue());

        bst.put(30, "3");
        bst.put(40, "1");
        assertEquals("1", bst.maxValue());

        bst.put(20, "400");
        assertEquals("1", bst.maxValue());

        bst.put(50, "0");
        assertEquals("0", bst.maxValue());
    }

    @Test
    void ceil() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(10, "10");
        bst.put(13, "13");
        bst.put(15, "15");
        bst.put(17, "17");
        bst.put(20, "20");
        bst.put(21, "21");
        bst.put(25, "25");
        bst.put(30, "30");
        bst.put(35, "35");
        bst.put(40, "40");

        assertEquals(25, bst.ceil(23));
        assertEquals(10, bst.ceil(10));
        assertEquals(40, bst.ceil(36));
        assertNull(bst.ceil(41));
    }

    @Test
    void floor() {
        Bst<Integer, String> bst = new AvlBst<>();

        bst.put(10, "10");
        bst.put(13, "13");
        bst.put(15, "15");
        bst.put(17, "17");
        bst.put(20, "20");
        bst.put(21, "21");
        bst.put(25, "25");
        bst.put(30, "30");
        bst.put(35, "35");
        bst.put(40, "40");

        assertEquals(10, bst.floor(11));
        assertEquals(13, bst.floor(13));
        assertEquals(40, bst.floor(50));
        assertNull(bst.floor(0));
    }
}