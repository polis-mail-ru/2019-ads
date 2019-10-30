package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvlBstTest {

    private static Bst<String, String> avlBst;

    @BeforeAll
    static void createInstance() {
        avlBst = new AvlBst<>();
    }

    @Test
    void testWorkingClear() {
        avlBst.put("gsgdggr", "vtfgtxgrgdrg");
        avlBst.put("gsrgrg", "srgtrhj");

        assertEquals(avlBst.size(), 2);
        assertFalse(avlBst.empty());

        avlBst.clear();

        assertEquals(avlBst.size(), 0);
        assertTrue(avlBst.empty());
    }


    @Test
    void testWorkingPut() {

    }

    @Test
    void testWorkingSize() {
        int size = 0;
        assertEquals(avlBst.size(), size);
        avlBst.put("testStringKey1", "testStringValue1");
        assertEquals(avlBst.size(), ++size);
        avlBst.put("testStringKey2", "testStringValue2");
        assertEquals(avlBst.size(), ++size);
        avlBst.put("testStringKey2", "testStringValue3");
        assertEquals(avlBst.size(), size);
        assertEquals(avlBst.remove("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.size(), --size);
        assertNull(avlBst.remove("not exist key"));
        assertEquals(avlBst.size(), size);
        assertEquals(avlBst.get("testStringKey2"), "testStringValue3");
        assertEquals(avlBst.size(), size);
        assertNull(avlBst.get("not exist key"));
        assertEquals(avlBst.size(), size);
    }

    @AfterEach
    void clear() {
        avlBst.clear();
    }
}
