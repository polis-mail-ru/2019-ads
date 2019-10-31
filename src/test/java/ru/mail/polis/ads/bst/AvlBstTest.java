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
        avlBst.put("testStringKey1", "testStringValue1");
        avlBst.put("testStringKey2", "testStringValue2");

        assertEquals(avlBst.size(), 2);
        assertFalse(avlBst.empty());

        avlBst.clear();

        assertEquals(avlBst.size(), 0);
        assertTrue(avlBst.empty());
    }

    @Test
    void testWorkingGet() {
        assertNull(avlBst.get("case when bst is empty"));

        avlBst.put("testStringKey3", "testStringValue3");
        avlBst.put("testStringKey4", "testStringValue4");
        avlBst.put("testStringKey2", "testStringValue2");
        avlBst.put("testStringKey5", "testStringValue5");
        avlBst.put("testStringKey1", "testStringValue1");
        avlBst.put("testStringKey0", "testStringValue0");

        int size = avlBst.size();

        assertEquals(avlBst.get("testStringKey0"), "testStringValue0");
        assertEquals(avlBst.get("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.get("testStringKey2"), "testStringValue2");
        assertEquals(avlBst.get("testStringKey3"), "testStringValue3");
        assertEquals(avlBst.get("testStringKey4"), "testStringValue4");
        assertEquals(avlBst.get("testStringKey5"), "testStringValue5");
        assertNull(avlBst.get("case when this element doesn't exist"));

        assertEquals(avlBst.size(), size);

        avlBst.remove("testStringKey0");
        assertNull(avlBst.get("testStringKey0"));

        avlBst.remove("testStringKey1");
        assertNull(avlBst.get("testStringKey1"));

        avlBst.remove("testStringKey2");
        assertNull(avlBst.get("testStringKey2"));

        avlBst.remove("testStringKey3");
        assertNull(avlBst.get("testStringKey3"));

        avlBst.remove("testStringKey4");
        assertNull(avlBst.get("testStringKey4"));

        avlBst.remove("testStringKey5");
        assertNull(avlBst.get("testStringKey5"));

        assertEquals(avlBst.size(), 0);
    }

    @Test
    void testWorkingPut() {
        int size = 0;
        assertEquals(avlBst.size(), size);
        assertNull(avlBst.max());
        assertNull(avlBst.min());
        assertNull(avlBst.get("testStringKey1"));

        avlBst.put("testStringKey1", "testStringValue1");

        assertEquals(avlBst.size(), ++size);
        assertEquals(avlBst.min(), "testStringKey1");
        assertEquals(avlBst.max(), "testStringKey1");
        assertEquals(avlBst.get("testStringKey1"), "testStringValue1");

        avlBst.put("testStringKey2", "testStringValue2");

        assertEquals(avlBst.size(), ++size);
        assertEquals(avlBst.min(), "testStringKey1");
        assertEquals(avlBst.max(), "testStringKey2");
        assertEquals(avlBst.maxValue(), "testStringValue2");
        assertEquals(avlBst.get("testStringKey2"), "testStringValue2");

        avlBst.put("testStringKey2", "case with same value");

        assertEquals(avlBst.size(), size);
        assertEquals(avlBst.min(), "testStringKey1");
        assertEquals(avlBst.max(), "testStringKey2");
        assertEquals(avlBst.maxValue(), "case with same value");
        assertEquals(avlBst.get("testStringKey2"), "case with same value");

        avlBst.put("testStringKey3", "testStringValue3");

        assertEquals(avlBst.size(), ++size);
        assertEquals(avlBst.min(), "testStringKey1");
        assertEquals(avlBst.max(), "testStringKey3");
        assertEquals(avlBst.get("testStringKey3"), "testStringValue3");

        avlBst.put("testStringKey", "testStringValue");

        assertEquals(avlBst.size(), ++size);
        assertEquals(avlBst.min(), "testStringKey");
        assertEquals(avlBst.max(), "testStringKey3");
        assertEquals(avlBst.get("testStringKey"), "testStringValue");
    }

    @Test
    void testWorkingRemove() {
        assertNull(avlBst.remove("case when bst is empty"));
        assertTrue(avlBst.empty());

        avlBst.put("testStringKey3", "testStringValue3");
        avlBst.put("testStringKey4", "testStringValue4");
        avlBst.put("testStringKey2", "testStringValue2");
        avlBst.put("testStringKey5", "testStringValue5");
        avlBst.put("testStringKey1", "testStringValue1");
        avlBst.put("testStringKey0", "testStringValue0");

        assertFalse(avlBst.empty());
        int size = avlBst.size();

        assertEquals(avlBst.remove("testStringKey4"), "testStringValue4");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey4"));

        assertEquals(avlBst.remove("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey1"));

        assertNull(avlBst.remove("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.size(), size);
        assertFalse(avlBst.empty());
        assertFalse(avlBst.containsKey("testStringKey1"));

        assertEquals(avlBst.remove("testStringKey3"), "testStringValue3");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey3"));

        assertEquals(avlBst.remove("testStringKey0"), "testStringValue0");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey0"));

        assertEquals(avlBst.remove("testStringKey2"), "testStringValue2");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey2"));

        assertEquals(avlBst.remove("testStringKey5"), "testStringValue5");
        assertEquals(avlBst.size(), --size);
        assertFalse(avlBst.containsKey("testStringKey5"));

        assertTrue(avlBst.empty());
    }

    @Test
    void testWorkingMax() {
        assertNull(avlBst.max());
        assertNull(avlBst.maxValue());

        avlBst.put("testStringKey2", "testStringValue2");

        assertEquals(avlBst.max(), "testStringKey2");
        assertEquals(avlBst.maxValue(), "testStringValue2");

        avlBst.put("testStringKey5", "testStringValue5");

        assertEquals(avlBst.max(), "testStringKey5");
        assertEquals(avlBst.maxValue(), "testStringValue5");

        avlBst.put("testStringKey0", "testStringValue0");

        assertEquals(avlBst.max(), "testStringKey5");
        assertEquals(avlBst.maxValue(), "testStringValue5");

        avlBst.put("testStringKey7", "testStringValue7");

        assertEquals(avlBst.max(), "testStringKey7");
        assertEquals(avlBst.maxValue(), "testStringValue7");

        avlBst.remove("testStringKey5");

        assertEquals(avlBst.max(), "testStringKey7");
        assertEquals(avlBst.maxValue(), "testStringValue7");

        avlBst.remove("testStringKey7");

        assertEquals(avlBst.max(), "testStringKey2");
        assertEquals(avlBst.maxValue(), "testStringValue2");

        avlBst.remove("testStringKey0");

        assertEquals(avlBst.max(), "testStringKey2");
        assertEquals(avlBst.maxValue(), "testStringValue2");

        avlBst.remove("testStringKey2");

        assertNull(avlBst.max());
        assertNull(avlBst.maxValue());
    }

    @Test
    void testWorkingMin() {
        assertNull(avlBst.min());
        assertNull(avlBst.minValue());

        avlBst.put("testStringKey5", "testStringValue5");

        assertEquals(avlBst.min(), "testStringKey5");
        assertEquals(avlBst.minValue(), "testStringValue5");

        avlBst.put("testStringKey3", "testStringValue3");

        assertEquals(avlBst.min(), "testStringKey3");
        assertEquals(avlBst.minValue(), "testStringValue3");

        avlBst.put("testStringKey9", "testStringValue9");

        assertEquals(avlBst.min(), "testStringKey3");
        assertEquals(avlBst.minValue(), "testStringValue3");

        avlBst.put("testStringKey0", "testStringValue0");

        assertEquals(avlBst.min(), "testStringKey0");
        assertEquals(avlBst.minValue(), "testStringValue0");

        avlBst.remove("testStringKey5");

        assertEquals(avlBst.min(), "testStringKey0");
        assertEquals(avlBst.minValue(), "testStringValue0");

        avlBst.remove("testStringKey0");

        assertEquals(avlBst.min(), "testStringKey3");
        assertEquals(avlBst.minValue(), "testStringValue3");

        avlBst.remove("testStringKey9");

        assertEquals(avlBst.min(), "testStringKey3");
        assertEquals(avlBst.minValue(), "testStringValue3");

        avlBst.remove("testStringKey3");

        assertNull(avlBst.min());
        assertNull(avlBst.minValue());
    }

    @Test
    void testWorkingContains() {
        assertFalse(avlBst.containsKey("testStringKey"));
        assertFalse(avlBst.containsKey("testStringKey1"));
        assertFalse(avlBst.containsValue("testStringValue"));
        assertFalse(avlBst.containsValue("testStringValue1"));

        avlBst.put("testStringKey", "testStringValue");
        assertTrue(avlBst.containsKey("testStringKey"));
        assertFalse(avlBst.containsKey("testStringKey1"));
        assertTrue(avlBst.containsValue("testStringValue"));
        assertFalse(avlBst.containsValue("testStringValue1"));

        avlBst.put("testStringKey1", "testStringValue1");
        assertTrue(avlBst.containsKey("testStringKey1"));
        assertTrue(avlBst.containsKey("testStringKey"));
        assertTrue(avlBst.containsValue("testStringValue"));
        assertTrue(avlBst.containsValue("testStringValue1"));

        avlBst.remove("testStringKey");
        assertTrue(avlBst.containsKey("testStringKey1"));
        assertFalse(avlBst.containsKey("testStringKey"));
        assertTrue(avlBst.containsValue("testStringValue1"));
        assertFalse(avlBst.containsValue("testStringValue"));

        avlBst.remove("testStringKey1");
        assertFalse(avlBst.containsKey("testStringKey"));
        assertFalse(avlBst.containsKey("testStringKey1"));
        assertFalse(avlBst.containsValue("testStringValue"));
        assertFalse(avlBst.containsValue("testStringValue1"));
    }

    @Test
    void testWorkingEmpty() {
        assertTrue(avlBst.empty());

        avlBst.put("testStringKey", "testStringValue");
        assertFalse(avlBst.empty());

        avlBst.put("testStringKey1", "testStringValue1");
        assertFalse(avlBst.empty());

        avlBst.remove("testStringKey");
        assertFalse(avlBst.empty());

        avlBst.remove("testStringKey1");
        assertTrue(avlBst.empty());
    }

    @Test
    void testWorkingCeil() {
        avlBst.put("1", "testStringValue3");
        avlBst.put("3", "testStringValue4");
        avlBst.put("5", "testStringValue2");
        avlBst.put("7", "testStringValue5");
        avlBst.put("8", "testStringValue1");
        avlBst.put("9", "testStringValue0");
        avlBst.put("2", "testStringValue0");

        assertEquals(avlBst.min(), "1");
        assertEquals(avlBst.max(), "9");
        assertEquals(avlBst.ceil("5"), "5");
        assertEquals(avlBst.ceil("2"), "2");
        assertEquals(avlBst.ceil("8"), "8");
        assertEquals(avlBst.ceil("0"), "1");
        assertEquals(avlBst.ceil("9"), "9");
        assertNull(avlBst.ceil("99"));
    }

    @Test
    void testWorkingFloor() {
        avlBst.put("1", "testStringValue3");
        avlBst.put("3", "testStringValue4");
        avlBst.put("5", "testStringValue2");
        avlBst.put("7", "testStringValue5");
        avlBst.put("8", "testStringValue1");
        avlBst.put("9", "testStringValue0");
        avlBst.put("2", "testStringValue0");

        assertEquals(avlBst.min(), "1");
        assertEquals(avlBst.max(), "9");
        assertEquals(avlBst.floor("5"), "5");
        assertEquals(avlBst.floor("4"), "3");
        assertEquals(avlBst.floor("8"), "8");
        assertEquals(avlBst.floor("1"), "1");
        assertEquals(avlBst.floor("99"), "9");
        assertNull(avlBst.floor(""));
    }

    @Test
    void testWorkingReplaceValues() {
        assertNull(avlBst.get("1"));

        avlBst.put("1", "testStringValue3");
        assertEquals(avlBst.get("1"), "testStringValue3");

        avlBst.put("1", "testStringValue4");
        assertEquals(avlBst.get("1"), "testStringValue4");

        avlBst.put("1", "testStringValue2");
        assertEquals(avlBst.get("1"), "testStringValue2");

        avlBst.put("7", "testStringValue5");
        assertEquals(avlBst.get("7"), "testStringValue5");
        assertEquals(avlBst.get("1"), "testStringValue2");
    }

    @Test
    void testWorkingHeight() {
        assertEquals(avlBst.height(), 0);

        avlBst.put("1", "testStringValue3");
        assertEquals(avlBst.height(), 1);

        avlBst.put("8", "testStringValue4");
        assertEquals(avlBst.height(), 2);

        avlBst.put("5", "testStringValue2");
        assertEquals(avlBst.height(), 2);

        avlBst.put("5", "same key");
        assertEquals(avlBst.height(), 2);

        avlBst.put("6", "testStringValue5");
        assertEquals(avlBst.height(), 3);

        avlBst.put("2", "testStringValue1");
        assertEquals(avlBst.height(), 3);

        avlBst.put("9", "testStringValue0");
        assertEquals(avlBst.height(), 3);

        avlBst.put("4", "testStringValue0");
        assertEquals(avlBst.height(), 3);

        avlBst.put("0", "testStringValue0");
        assertEquals(avlBst.height(), 4);
    }

    @Test
    void testWorkingBalance() {

        /*
        avl bst visualisation :
        root ---> null
        height = 0
         */
        assertTrue(avlBst.empty());
        int size = 0;
        assertEquals(avlBst.height(), 0);
        assertEquals(avlBst.size(), size);

        /*
        avl bst visualisation :
        root ---> 1
        height = 1
         */
        avlBst.put("1", "testStringValue3");
        assertEquals(avlBst.height(), 1);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root ---> 1
                   \
                    5
        height = 2
         */
        avlBst.put("5", "testStringValue4");
        assertEquals(avlBst.height(), 2);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root ---> 5
                 / \
                1   9
        height = 2
         */
        avlBst.put("9", "testStringValue2");
        assertEquals(avlBst.height(), 2);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root ---> 5
                 / \
                1   9
                     \
                     13
        height = 3
         */
        avlBst.put("13", "testStringValue5");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root ---> 5
                 / \
                1  11
                  /  \
                 9   13
        height = 3
         */
        avlBst.put("11", "testStringValue1");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root --->   9
                   / \
                  5  11
                /   /  \
               1   10  13
        height = 3
         */
        avlBst.put("10", "testStringValue0");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root --->   9
                   / \
                 1   11
                /\   / \
               0 5  10 13
        height = 3
         */
        avlBst.put("0", "testStringValue0");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root --->   9
                   / \
                 1   11
                /\   / \
               0 5  10 13
                       /
                      12
        height = 4
         */
        avlBst.put("12", "testStringValue0");
        assertEquals(avlBst.height(), 4);
        assertEquals(avlBst.size(), ++size);

        /*
        avl bst visualisation :
        root --->   9
                   / \
                 5   11
                /    / \
               0    10 13
                       /
                      12
        height = 4
         */
        avlBst.remove("1");
        assertEquals(avlBst.height(), 4);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 11
                 /  \
                9   13
               /\   /
              0 10 12
        height = 3
         */
        avlBst.remove("5");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 11
                 /  \
                9   13
                \   /
                10 12
        height = 3
         */
        avlBst.remove("0");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 11
                 /  \
                9   12
                \
                10
        height = 3
         */
        avlBst.remove("13");
        assertEquals(avlBst.height(), 3);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 10
                 /  \
                9   11
        height = 2
         */
        avlBst.remove("12");
        assertEquals(avlBst.height(), 2);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 11
                 /
                9
        height = 2
         */
        avlBst.remove("10");
        assertEquals(avlBst.height(), 2);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> 9
        height = 1
         */
        avlBst.remove("11");
        assertEquals(avlBst.height(), 1);
        assertEquals(avlBst.size(), --size);

        /*
        avl bst visualisation :
        root ---> null
        height = 0
         */
        avlBst.remove("9");
        assertEquals(avlBst.height(), 0);
        assertEquals(avlBst.size(), --size);

        assertTrue(avlBst.empty());
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
