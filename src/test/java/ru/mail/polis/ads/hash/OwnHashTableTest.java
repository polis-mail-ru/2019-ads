package ru.mail.polis.ads.hash;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OwnHashTableTest {

    private static HashTable<Integer, String> hashTable;

    @BeforeAll
    static void createInstance() {
        hashTable = new OwnHashTable<>();
    }

    @Test
    void testWorkingPut() {
        assertTrue(hashTable.isEmpty());
        assertEquals(hashTable.size(), 0);

        for (int i = 1; i <= 10; i++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.get(i), Integer.toString(i));
            assertEquals(hashTable.size(), i);
        }

        int size = hashTable.size();
        for (int i = 17, j = 1; i < 26; i++, j++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.get(i), Integer.toString(i));
            assertEquals(hashTable.size(), size + j);
        }

        size = hashTable.size();
        for (int i = 1; i <= 10; i++) {
            hashTable.put(i, i + " same key case");
            assertEquals(hashTable.get(i), i + " same key case");
            assertEquals(hashTable.size(), size);
        }

        //after resize
        for (int i = 11; i <= 16; i++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.get(i), Integer.toString(i));
        }
        for (int i = 1; i <= 10; i++) {
            hashTable.put(i, i + " same key case");
            assertEquals(hashTable.get(i), i + " same key case");
        }
        for (int i = 17; i < 26; i++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.get(i), Integer.toString(i));
        }
    }

    @Test
    void testWorkingRemove() {
        assertTrue(hashTable.isEmpty());
        assertEquals(hashTable.size(), 0);
        assertNull(hashTable.remove(1));

        for (int i = 1; i <= 12; i++) {
            hashTable.put(i, Integer.toString(i));
        }
        for (int i = 17; i <= 28; i++) {
            hashTable.put(i, Integer.toString(i));
        }

        int size = hashTable.size();
        for (int i = 17; i <= 25; i++) {
            assertEquals(hashTable.remove(i), Integer.toString(i));
            assertNull(hashTable.get(i));
            assertEquals(hashTable.size(), --size);
        }

        //after resize
        for (int i = 13; i <= 16; i++) {
            hashTable.put(i, Integer.toString(i));
        }
        size = hashTable.size();
        for (int i = 1; i <= 16; i++) {
            assertEquals(hashTable.remove(i), Integer.toString(i));
            assertNull(hashTable.get(i));
            assertEquals(hashTable.size(), --size);
        }
        for (int i = 26; i <= 28; i++) {
            assertEquals(hashTable.get(i), Integer.toString(i));
        }
    }

    @Test
    void testWorkingGet() {
        assertTrue(hashTable.isEmpty());
        assertEquals(hashTable.size(), 0);
        assertNull(hashTable.get(1));

        for (int i = 0; i < 25; i++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.get(i), Integer.toString(i));
        }

        for (int i = 0; i < 25; i++) {
            hashTable.put(i, i + " same key case");
            assertEquals(hashTable.get(i), i + " same key case");
        }

        for (int i = 0; i < 25; i++) {
            hashTable.remove(i);
            assertNull(hashTable.get(i));
        }

        assertEquals(hashTable.size(), 0);
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void testWorkingSize() {
        int size = 0;
        assertEquals(hashTable.size(), size);

        for (int i = 0; i < 25; i++) {
            hashTable.put(i, Integer.toString(i));
            assertEquals(hashTable.size(), ++size);
        }

        for (int i = 0; i < 25; i++) {
            hashTable.remove(i);
            assertEquals(hashTable.size(), --size);
        }

        assertEquals(hashTable.size(), 0);
    }

    @Test
    void testWorkingEmpty() {
        assertTrue(hashTable.isEmpty());

        for (int i = 0; i < 25; i++) {
            hashTable.put(i, Integer.toString(i));
            assertFalse(hashTable.isEmpty());
        }

        for (int i = 0; i < 24; i++) {
            hashTable.remove(i);
            assertFalse(hashTable.isEmpty());
        }

        hashTable.remove(24);
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void testWorkingClear() {
        assertTrue(hashTable.isEmpty());
        assertEquals(hashTable.size(), 0);

        for (int i = 0; i < 25; i++) {
            hashTable.put(i, Integer.toString(i));
            assertFalse(hashTable.isEmpty());
        }

        assertFalse(hashTable.isEmpty());
        assertEquals(hashTable.size(), 25);

        hashTable.clear();

        assertTrue(hashTable.isEmpty());
        assertEquals(hashTable.size(), 0);

        for (int i = 0; i < 25; i++) {
            assertNull(hashTable.get(i));
        }
    }

    @AfterEach
    void clear() {
        hashTable.clear();
    }
}