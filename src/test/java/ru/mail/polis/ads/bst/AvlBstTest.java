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

        assertEquals(avlBst.remove("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.size(), --size);

        assertNull(avlBst.remove("testStringKey1"), "testStringValue1");
        assertEquals(avlBst.size(), size);
        assertFalse(avlBst.empty());

        assertEquals(avlBst.remove("testStringKey3"), "testStringValue3");
        assertEquals(avlBst.size(), --size);

        assertEquals(avlBst.remove("testStringKey0"), "testStringValue0");
        assertEquals(avlBst.size(), --size);

        assertEquals(avlBst.remove("testStringKey2"), "testStringValue2");
        assertEquals(avlBst.size(), --size);

        assertEquals(avlBst.remove("testStringKey5"), "testStringValue5");
        assertEquals(avlBst.size(), --size);

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
        assertFalse(avlBst.contains("testStringKey"));
        assertFalse(avlBst.contains("testStringKey1"));

        avlBst.put("testStringKey", "testStringValue");
        assertTrue(avlBst.contains("testStringKey"));
        assertFalse(avlBst.contains("testStringKey1"));

        avlBst.put("testStringKey1", "testStringValue1");
        assertTrue(avlBst.contains("testStringKey1"));
        assertTrue(avlBst.contains("testStringKey"));

        avlBst.remove("testStringKey");
        assertTrue(avlBst.contains("testStringKey1"));
        assertFalse(avlBst.contains("testStringKey"));

        avlBst.remove("testStringKey1");
        assertFalse(avlBst.contains("testStringKey"));
        assertFalse(avlBst.contains("testStringKey1"));
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
