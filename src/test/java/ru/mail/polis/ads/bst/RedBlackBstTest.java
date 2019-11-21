package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedBlackBstTest {

    private static Bst<String, String> redBlackBstInterface;
    private static RedBlackBst<String, String> redBlackBst;

    @BeforeAll
    static void createInstance() {
        redBlackBstInterface = new RedBlackBst<>();
        redBlackBst = new RedBlackBst<>();
    }

    @Test
    void testWorkingClear() {
        redBlackBstInterface.put("testStringKey1", "testStringValue1");
        redBlackBstInterface.put("testStringKey2", "testStringValue2");

        assertEquals(redBlackBstInterface.size(), 2);
        assertFalse(redBlackBstInterface.empty());

        redBlackBstInterface.clear();

        assertEquals(redBlackBstInterface.size(), 0);
        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingGet() {
        assertNull(redBlackBstInterface.get("case when bst is empty"));

        redBlackBstInterface.put("testStringKey3", "testStringValue3");
        redBlackBstInterface.put("testStringKey4", "testStringValue4");
        redBlackBstInterface.put("testStringKey2", "testStringValue2");
        redBlackBstInterface.put("testStringKey5", "testStringValue5");
        redBlackBstInterface.put("testStringKey1", "testStringValue1");
        redBlackBstInterface.put("testStringKey0", "testStringValue0");

        int size = redBlackBstInterface.size();

        assertEquals(redBlackBstInterface.get("testStringKey0"), "testStringValue0");
        assertEquals(redBlackBstInterface.get("testStringKey1"), "testStringValue1");
        assertEquals(redBlackBstInterface.get("testStringKey2"), "testStringValue2");
        assertEquals(redBlackBstInterface.get("testStringKey3"), "testStringValue3");
        assertEquals(redBlackBstInterface.get("testStringKey4"), "testStringValue4");
        assertEquals(redBlackBstInterface.get("testStringKey5"), "testStringValue5");
        assertNull(redBlackBstInterface.get("case when this element doesn't exist"));

        assertEquals(redBlackBstInterface.size(), size);

        redBlackBstInterface.remove("testStringKey0");
        assertNull(redBlackBstInterface.get("testStringKey0"));

        redBlackBstInterface.remove("testStringKey1");
        assertNull(redBlackBstInterface.get("testStringKey1"));

        redBlackBstInterface.remove("testStringKey2");
        assertNull(redBlackBstInterface.get("testStringKey2"));

        redBlackBstInterface.remove("testStringKey3");
        assertNull(redBlackBstInterface.get("testStringKey3"));

        redBlackBstInterface.remove("testStringKey4");
        assertNull(redBlackBstInterface.get("testStringKey4"));

        redBlackBstInterface.remove("testStringKey5");
        assertNull(redBlackBstInterface.get("testStringKey5"));

        assertEquals(redBlackBstInterface.size(), 0);
    }

    @Test
    void testWorkingPut() {
        int size = 0;
        assertEquals(redBlackBstInterface.size(), size);
        assertNull(redBlackBstInterface.max());
        assertNull(redBlackBstInterface.min());
        assertNull(redBlackBstInterface.get("testStringKey1"));

        redBlackBstInterface.put("testStringKey1", "testStringValue1");

        assertEquals(redBlackBstInterface.size(), ++size);
        assertEquals(redBlackBstInterface.min(), "testStringKey1");
        assertEquals(redBlackBstInterface.max(), "testStringKey1");
        assertEquals(redBlackBstInterface.get("testStringKey1"), "testStringValue1");

        redBlackBstInterface.put("testStringKey2", "testStringValue2");

        assertEquals(redBlackBstInterface.size(), ++size);
        assertEquals(redBlackBstInterface.min(), "testStringKey1");
        assertEquals(redBlackBstInterface.max(), "testStringKey2");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");
        assertEquals(redBlackBstInterface.get("testStringKey2"), "testStringValue2");

        redBlackBstInterface.put("testStringKey2", "case with same value");

        assertEquals(redBlackBstInterface.size(), size);
        assertEquals(redBlackBstInterface.min(), "testStringKey1");
        assertEquals(redBlackBstInterface.max(), "testStringKey2");
        assertEquals(redBlackBstInterface.maxValue(), "case with same value");
        assertEquals(redBlackBstInterface.get("testStringKey2"), "case with same value");

        redBlackBstInterface.put("testStringKey3", "testStringValue3");

        assertEquals(redBlackBstInterface.size(), ++size);
        assertEquals(redBlackBstInterface.min(), "testStringKey1");
        assertEquals(redBlackBstInterface.max(), "testStringKey3");
        assertEquals(redBlackBstInterface.get("testStringKey3"), "testStringValue3");

        redBlackBstInterface.put("testStringKey", "testStringValue");

        assertEquals(redBlackBstInterface.size(), ++size);
        assertEquals(redBlackBstInterface.min(), "testStringKey");
        assertEquals(redBlackBstInterface.max(), "testStringKey3");
        assertEquals(redBlackBstInterface.get("testStringKey"), "testStringValue");
    }

    @Test
    void testWorkingDeleteMin() {
        assertNull(redBlackBstInterface.deleteMin());
        assertTrue(redBlackBstInterface.empty());

        redBlackBstInterface.put("1", "testStringValue1");
        redBlackBstInterface.put("3", "testStringValue3");
        redBlackBstInterface.put("8", "testStringValue8");
        redBlackBstInterface.put("2", "testStringValue2");
        redBlackBstInterface.put("9", "testStringValue9");
        redBlackBstInterface.put("4", "testStringValue4");

        assertFalse(redBlackBstInterface.empty());
        int size = redBlackBstInterface.size();

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue1");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("1"));

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue2");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("2"));

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue3");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("3"));

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue4");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("4"));

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue8");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("8"));

        assertEquals(redBlackBstInterface.deleteMin(), "testStringValue9");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("9"));

        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingDeleteMax() {
        assertNull(redBlackBstInterface.deleteMax());
        assertTrue(redBlackBstInterface.empty());

        redBlackBstInterface.put("1", "testStringValue1");
        redBlackBstInterface.put("3", "testStringValue3");
        redBlackBstInterface.put("8", "testStringValue8");
        redBlackBstInterface.put("2", "testStringValue2");
        redBlackBstInterface.put("9", "testStringValue9");
        redBlackBstInterface.put("4", "testStringValue4");

        assertFalse(redBlackBstInterface.empty());
        int size = redBlackBstInterface.size();

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue9");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("9"));

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue8");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("8"));

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue4");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("4"));

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue3");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("3"));

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue2");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("2"));

        assertEquals(redBlackBstInterface.deleteMax(), "testStringValue1");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("1"));

        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingRemove() {
        assertNull(redBlackBstInterface.remove("case when bst is empty"));
        assertTrue(redBlackBstInterface.empty());

        redBlackBstInterface.put("3", "testStringValue3");
        redBlackBstInterface.put("4", "testStringValue4");
        redBlackBstInterface.put("2", "testStringValue2");
        redBlackBstInterface.put("5", "testStringValue5");
        redBlackBstInterface.put("1", "testStringValue1");
        redBlackBstInterface.put("0", "testStringValue0");

        assertFalse(redBlackBstInterface.empty());
        int size = redBlackBstInterface.size();

        assertEquals(redBlackBstInterface.remove("4"), "testStringValue4");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("4"));

        assertEquals(redBlackBstInterface.remove("1"), "testStringValue1");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("1"));

        assertNull(redBlackBstInterface.remove("1"), "testStringValue1");
        assertEquals(redBlackBstInterface.size(), size);
        assertFalse(redBlackBstInterface.empty());
        assertFalse(redBlackBstInterface.containsKey("1"));

        assertEquals(redBlackBstInterface.remove("3"), "testStringValue3");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("3"));

        assertEquals(redBlackBstInterface.remove("0"), "testStringValue0");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("0"));

        assertEquals(redBlackBstInterface.remove("2"), "testStringValue2");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("2"));

        assertEquals(redBlackBstInterface.remove("5"), "testStringValue5");
        assertEquals(redBlackBstInterface.size(), --size);
        assertFalse(redBlackBstInterface.containsKey("5"));

        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingMax() {
        assertNull(redBlackBstInterface.max());
        assertNull(redBlackBstInterface.maxValue());

        redBlackBstInterface.put("testStringKey2", "testStringValue2");

        assertEquals(redBlackBstInterface.max(), "testStringKey2");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");

        redBlackBstInterface.put("testStringKey5", "testStringValue5");

        assertEquals(redBlackBstInterface.max(), "testStringKey5");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue5");

        redBlackBstInterface.put("testStringKey0", "testStringValue0");

        assertEquals(redBlackBstInterface.max(), "testStringKey5");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue5");

        redBlackBstInterface.put("testStringKey7", "testStringValue7");

        assertEquals(redBlackBstInterface.max(), "testStringKey7");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue7");

        redBlackBstInterface.remove("testStringKey5");

        assertEquals(redBlackBstInterface.max(), "testStringKey7");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue7");

        redBlackBstInterface.remove("testStringKey7");

        assertEquals(redBlackBstInterface.max(), "testStringKey2");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");

        redBlackBstInterface.remove("testStringKey0");

        assertEquals(redBlackBstInterface.max(), "testStringKey2");
        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");

        redBlackBstInterface.remove("testStringKey2");

        assertNull(redBlackBstInterface.max());
        assertNull(redBlackBstInterface.maxValue());
    }

    @Test
    void testWorkingMin() {
        assertNull(redBlackBstInterface.min());
        assertNull(redBlackBstInterface.minValue());

        redBlackBstInterface.put("testStringKey5", "testStringValue5");

        assertEquals(redBlackBstInterface.min(), "testStringKey5");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue5");

        redBlackBstInterface.put("testStringKey3", "testStringValue3");

        assertEquals(redBlackBstInterface.min(), "testStringKey3");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue3");

        redBlackBstInterface.put("testStringKey9", "testStringValue9");

        assertEquals(redBlackBstInterface.min(), "testStringKey3");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue3");

        redBlackBstInterface.put("testStringKey0", "testStringValue0");

        assertEquals(redBlackBstInterface.min(), "testStringKey0");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue0");

        redBlackBstInterface.remove("testStringKey5");

        assertEquals(redBlackBstInterface.min(), "testStringKey0");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue0");

        redBlackBstInterface.remove("testStringKey0");

        assertEquals(redBlackBstInterface.min(), "testStringKey3");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue3");

        redBlackBstInterface.remove("testStringKey9");

        assertEquals(redBlackBstInterface.min(), "testStringKey3");
        assertEquals(redBlackBstInterface.minValue(), "testStringValue3");

        redBlackBstInterface.remove("testStringKey3");

        assertNull(redBlackBstInterface.min());
        assertNull(redBlackBstInterface.minValue());
    }

    @Test
    void testWorkingContains() {
        assertFalse(redBlackBstInterface.containsKey("testStringKey"));
        assertFalse(redBlackBstInterface.containsKey("testStringKey1"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue1"));

        redBlackBstInterface.put("testStringKey", "testStringValue");
        assertTrue(redBlackBstInterface.containsKey("testStringKey"));
        assertFalse(redBlackBstInterface.containsKey("testStringKey1"));
        assertTrue(redBlackBstInterface.containsValue("testStringValue"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue1"));

        redBlackBstInterface.put("testStringKey1", "testStringValue1");
        assertTrue(redBlackBstInterface.containsKey("testStringKey1"));
        assertTrue(redBlackBstInterface.containsKey("testStringKey"));
        assertTrue(redBlackBstInterface.containsValue("testStringValue"));
        assertTrue(redBlackBstInterface.containsValue("testStringValue1"));

        redBlackBstInterface.remove("testStringKey");
        assertTrue(redBlackBstInterface.containsKey("testStringKey1"));
        assertFalse(redBlackBstInterface.containsKey("testStringKey"));
        assertTrue(redBlackBstInterface.containsValue("testStringValue1"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue"));

        redBlackBstInterface.remove("testStringKey1");
        assertFalse(redBlackBstInterface.containsKey("testStringKey"));
        assertFalse(redBlackBstInterface.containsKey("testStringKey1"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue"));
        assertFalse(redBlackBstInterface.containsValue("testStringValue1"));
    }

    @Test
    void testWorkingEmpty() {
        assertTrue(redBlackBstInterface.empty());

        redBlackBstInterface.put("testStringKey", "testStringValue");
        assertFalse(redBlackBstInterface.empty());

        redBlackBstInterface.put("testStringKey1", "testStringValue1");
        assertFalse(redBlackBstInterface.empty());

        redBlackBstInterface.remove("testStringKey");
        assertFalse(redBlackBstInterface.empty());

        redBlackBstInterface.remove("testStringKey1");
        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingCeil() {
        redBlackBstInterface.put("10", "testStringValue10");
        redBlackBstInterface.put("30", "testStringValue30");
        redBlackBstInterface.put("50", "testStringValue50");
        redBlackBstInterface.put("70", "testStringValue70");
        redBlackBstInterface.put("80", "testStringValue80");
        redBlackBstInterface.put("90", "testStringValue90");
        redBlackBstInterface.put("20", "testStringValue20");

        assertEquals(redBlackBstInterface.min(), "10");
        assertEquals(redBlackBstInterface.max(), "90");
        assertEquals(redBlackBstInterface.ceil("45"), "50");
        assertEquals(redBlackBstInterface.ceil("16"), "20");
        assertEquals(redBlackBstInterface.ceil("80"), "80");
        assertEquals(redBlackBstInterface.ceil("0"), "10");
        assertEquals(redBlackBstInterface.ceil("87"), "90");
        assertNull(redBlackBstInterface.ceil("990"));
    }

    @Test
    void testWorkingFloor() {
        redBlackBstInterface.put("10", "testStringValue10");
        redBlackBstInterface.put("30", "testStringValue30");
        redBlackBstInterface.put("50", "testStringValue50");
        redBlackBstInterface.put("70", "testStringValue70");
        redBlackBstInterface.put("80", "testStringValue80");
        redBlackBstInterface.put("90", "testStringValue90");
        redBlackBstInterface.put("20", "testStringValue20");

        assertEquals(redBlackBstInterface.min(), "10");
        assertEquals(redBlackBstInterface.max(), "90");
        assertEquals(redBlackBstInterface.floor("55"), "50");
        assertEquals(redBlackBstInterface.floor("40"), "30");
        assertEquals(redBlackBstInterface.floor("87"), "80");
        assertEquals(redBlackBstInterface.floor("12"), "10");
        assertEquals(redBlackBstInterface.floor("999"), "90");
        assertNull(redBlackBstInterface.floor(""));
    }

    @Test
    void testWorkingReplaceValues() {
        assertNull(redBlackBstInterface.get("1"));

        redBlackBstInterface.put("1", "testStringValue3");
        assertEquals(redBlackBstInterface.get("1"), "testStringValue3");

        redBlackBstInterface.put("1", "testStringValue4");
        assertEquals(redBlackBstInterface.get("1"), "testStringValue4");

        redBlackBstInterface.put("1", "testStringValue2");
        assertEquals(redBlackBstInterface.get("1"), "testStringValue2");

        redBlackBstInterface.put("7", "testStringValue5");
        assertEquals(redBlackBstInterface.get("7"), "testStringValue5");
        assertEquals(redBlackBstInterface.get("1"), "testStringValue2");
    }

    @Test
    void testWorkingHeight() {
        /*
        rb bst visualisation :
        root ---> null
        height = 0
         */
        assertEquals(redBlackBstInterface.height(), 0);

        /*
        rb bst visualisation :
        root ---> 100(B)
        height = 1
         */
        redBlackBstInterface.put("100", "testStringValue100");
        assertEquals(redBlackBstInterface.height(), 1);

        /*
        rb bst visualisation :
        root ---> 800(B)
                 /
            100(R)
        height = 2
         */
        redBlackBstInterface.put("800", "testStringValue800");
        assertEquals(redBlackBstInterface.height(), 2);

        /*
        rb bst visualisation :
        root ---> 500(B)
                 /     \
            100(B)     800(B)
        height = 2
         */
        redBlackBstInterface.put("500", "testStringValue500");
        assertEquals(redBlackBstInterface.height(), 2);

        /*
        rb bst visualisation :
        root ---> 500(B)
                 /     \
            100(B)     800(B)
        height = 2
         */
        redBlackBstInterface.put("500", "same key");
        assertEquals(redBlackBstInterface.height(), 2);

        /*
        rb bst visualisation :
        root ---> 500(B)
                 /     \
            100(B)     800(B)
                       /
                  600(R)
        height = 3
         */
        redBlackBstInterface.put("600", "testStringValue600");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root ---> 500(B)
                 /     \
            200(B)     800(B)
            /          /
        100(R)      600(R)
        height = 3
         */
        redBlackBstInterface.put("200", "testStringValue200");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 800(B)
                      /     \
                 500(R)     900(B)
                 /  \
             200(B) 600(B)
             /
        100(R)
        height = 4
         */
        redBlackBstInterface.put("900", "testStringValue900");
        assertEquals(redBlackBstInterface.height(), 4);

        /*
        rb bst visualisation :
        root -----> 500(B)
                   /     \
             200(B)      800(B)
            /    \       /    \
        100(B)  400(B) 600(B)  900(B)
        height = 4
         */
        redBlackBstInterface.put("400", "testStringValue400");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 500(B)
                      /     \
                200(B)      800(B)
               /    \       /    \
           100(B)  400(B) 600(B)  900(B)
           /
        0(R)
        height = 4
         */
        redBlackBstInterface.put("0", "testStringValue0");
        assertEquals(redBlackBstInterface.height(), 4);

        /*
        rb bst visualisation :
        root --------> 500(B)
                      /     \
                100(B)      800(B)
               /    \       /    \
             0(B)  400(B) 600(B)  900(B)
        height = 3
         */
        assertEquals(redBlackBstInterface.remove("200"), "testStringValue200");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 600(B)
                      /     \
                100(R)      900(B)
               /    \       /
             0(B)  400(B) 800(R)
        height = 3
         */
        assertEquals(redBlackBstInterface.remove("500"), "same key");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 800(B)
                      /     \
                100(R)      900(B)
               /    \
             0(B)  400(B)
        height = 3
         */
        assertEquals(redBlackBstInterface.remove("600"), "testStringValue600");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 100(B)
                      /     \
                   0(B)      900(B)
                             /
                        400(R)
        height = 3
         */
        assertEquals(redBlackBstInterface.remove("800"), "testStringValue800");
        assertEquals(redBlackBstInterface.height(), 3);

        /*
        rb bst visualisation :
        root --------> 400(B)
                      /     \
                 100(B)      900(B)
        height = 2
         */
        assertEquals(redBlackBstInterface.remove("0"), "testStringValue0");
        assertEquals(redBlackBstInterface.height(), 2);

        /*
        rb bst visualisation :
        root --------> 900(B)
                      /
                 400(R)
        height = 2
         */
        assertEquals(redBlackBstInterface.remove("100"), "testStringValue100");
        assertEquals(redBlackBstInterface.height(), 2);

        /*
        rb bst visualisation :
        root --------> 400(B)
        height = 1
         */
        assertEquals(redBlackBstInterface.remove("900"), "testStringValue900");
        assertEquals(redBlackBstInterface.height(), 1);

        /*
        rb bst visualisation :
        root --------> null
        height = 0
         */
        assertEquals(redBlackBstInterface.remove("400"), "testStringValue400");
        assertEquals(redBlackBstInterface.height(), 0);
        assertTrue(redBlackBstInterface.empty());
    }

    @Test
    void testWorkingBuildingRedBlackTree() {
        /*
        rb bst visualisation :
        root ---> null
        height = 0
         */
        assertTrue(redBlackBst.empty());
        int size = 0;
        assertEquals(redBlackBst.height(), 0);
        assertEquals(redBlackBst.size(), size);
        RedBlackBst.Node tempNode = redBlackBst.getRoot();
        assertNull(tempNode);

        /*
        rb bst visualisation :
        root ---> 300(B)
        height = 1
         */
        redBlackBst.put("300", "testStringValue300");
        assertEquals(redBlackBst.height(), 1);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("300"), "testStringValue300");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "300", "testStringValue300", false, 1);
        assertNull(tempNode.left);
        assertNull(tempNode.right);

        /*
        rb bst visualisation :
        root ---> 300(B)
                 /
             250(R)
        height = 2
         */
        redBlackBst.put("250", "testStringValue250");
        assertEquals(redBlackBst.height(), 2);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("250"), "testStringValue250");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "300", "testStringValue300", false, 2);
        testNode(tempNode.left, "250", "testStringValue250", true, 1);
        assertNull(tempNode.right);

        /*
        rb bst visualisation :
        root ---> 250(B)
                 /      \
             220(B)   300(B)
        height = 2
         */
        redBlackBst.put("220", "testStringValue220");
        assertEquals(redBlackBst.height(), 2);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("300"), "testStringValue300");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 2);
        testNode(tempNode.right, "300", "testStringValue300", false, 1);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);

        /*
        rb bst visualisation :
        root ---> 250(B)
                 /      \
             230(B)    300(B)
              /
          220(R)
        height = 3
         */
        redBlackBst.put("230", "testStringValue230");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("230"), "testStringValue230");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 3);
        testNode(tempNode.right, "300", "testStringValue300", false, 1);
        tempNode = tempNode.left;
        testNode(tempNode, "230", "testStringValue230", false, 2);
        testNode(tempNode.left, "220", "testStringValue220", true, 1);

        /*
        rb bst visualisation :
        root ---> 250(B)
                 /      \
             230(R)    300(B)
              /    \
          220(B)  240(B)
        height = 3
         */
        redBlackBst.put("240", "testStringValue240");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("240"), "testStringValue240");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 3);
        testNode(tempNode.right, "300", "testStringValue300", false, 1);
        tempNode = tempNode.left;
        testNode(tempNode, "230", "testStringValue230", true, 2);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "240", "testStringValue240", false, 1);

        /*
        rb bst visualisation :
        root ---> 250(B)
                 /      \
             230(R)      400(B)
           /      \       /
        220(B)  240(B)  300(R)
        height = 3
         */
        redBlackBst.put("400", "testStringValue400");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("400"), "testStringValue400");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 3);
        testNode(tempNode.right, "400", "testStringValue400", false, 2);
        testNode(tempNode.right.left, "300", "testStringValue300", true, 1);
        tempNode = tempNode.left;
        testNode(tempNode, "230", "testStringValue230", true, 2);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "240", "testStringValue240", false, 1);

        /*
        rb bst visualisation :
        root ---> 250(B)
                 /      \
             230(R)      400(B)
           /      \       /
        220(B)  242(B)  300(R)
                  /
               240(R)
        height = 4
         */
        redBlackBst.put("242", "testStringValue242");
        assertEquals(redBlackBst.height(), 4);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("242"), "testStringValue242");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 4);
        testNode(tempNode.right, "400", "testStringValue400", false, 2);
        testNode(tempNode.right.left, "300", "testStringValue300", true, 1);
        tempNode = tempNode.left;
        testNode(tempNode, "230", "testStringValue230", true, 3);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "242", "testStringValue242", false, 2);
        testNode(tempNode.right.left, "240", "testStringValue240", true, 1);

        /*
        rb bst visualisation :
        root ----> 241(B)
                  /     \
             230(B)     250(B)
             /   \      /    \
        220(B) 240(B) 242(B) 400(B)
                             /
                         300(R)
        height = 4
         */
        redBlackBst.put("241", "testStringValue241");
        assertEquals(redBlackBst.height(), 4);
        assertEquals(redBlackBst.size(), ++size);
        assertEquals(redBlackBst.get("241"), "testStringValue241");
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "241", "testStringValue241", false, 4);
        tempNode = tempNode.right;
        testNode(tempNode, "250", "testStringValue250", false, 3);
        testNode(tempNode.left, "242", "testStringValue242", false, 1);
        testNode(tempNode.right, "400", "testStringValue400", false, 2);
        testNode(tempNode.right.left, "300", "testStringValue300", true, 1);
        tempNode = redBlackBst.getRoot().left;
        testNode(tempNode, "230", "testStringValue230", false, 2);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "240", "testStringValue240", false, 1);

        /*
        rb bst visualisation :
        root ----> 241(B)
                  /     \
             230(B)     250(B)
             /   \      /    \
        220(B) 240(B) 242(B) 300(B)
        height = 3
         */
        assertEquals(redBlackBst.remove("400"), "testStringValue400");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "241", "testStringValue241", false, 3);
        tempNode = tempNode.right;
        testNode(tempNode, "250", "testStringValue250", false, 2);
        testNode(tempNode.left, "242", "testStringValue242", false, 1);
        testNode(tempNode.right, "300", "testStringValue300", false, 1);
        tempNode = redBlackBst.getRoot().left;
        testNode(tempNode, "230", "testStringValue230", false, 2);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "240", "testStringValue240", false, 1);
        assertFalse(redBlackBst.containsKey("400"));

        /*
        rb bst visualisation :
        root ---->     250(B)
                      /     \
                  241(R)     300(B)
                 /     \
            240(B)      242(B)
            /
        220(R)
        height = 4
        */
        assertEquals(redBlackBst.remove("230"), "testStringValue230");
        assertEquals(redBlackBst.height(), 4);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 4);
        tempNode = tempNode.right;
        testNode(tempNode, "300", "testStringValue300", false, 1);
        tempNode = redBlackBst.getRoot().left;
        testNode(tempNode, "241", "testStringValue241", true, 3);
        testNode(tempNode.left, "240", "testStringValue240", false, 2);
        testNode(tempNode.right, "242", "testStringValue242", false, 1);
        testNode(tempNode.left.left, "220", "testStringValue220", true, 1);
        assertFalse(redBlackBst.containsKey("230"));

        /*
        rb bst visualisation :
        root ---->     250(B)
                      /     \
                  240(R)     300(B)
                 /     \
            220(B)      242(B)
        height = 3
        */
        assertEquals(redBlackBst.remove("241"), "testStringValue241");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "250", "testStringValue250", false, 3);
        tempNode = tempNode.right;
        testNode(tempNode, "300", "testStringValue300", false, 1);
        tempNode = redBlackBst.getRoot().left;
        testNode(tempNode, "240", "testStringValue240", true, 2);
        testNode(tempNode.left, "220", "testStringValue220", false, 1);
        testNode(tempNode.right, "242", "testStringValue242", false, 1);
        assertFalse(redBlackBst.containsKey("241"));

        /*
        rb bst visualisation :
        root ----> 240(B)
                  /     \
             220(B)     300(B)
                        /
                    242(R)
        height = 3
        */
        assertEquals(redBlackBst.remove("250"), "testStringValue250");
        assertEquals(redBlackBst.height(), 3);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "240", "testStringValue240", false, 3);
        tempNode = tempNode.right;
        testNode(tempNode, "300", "testStringValue300", false, 2);
        testNode(tempNode.left, "242", "testStringValue242", true, 1);
        tempNode = redBlackBst.getRoot().left;
        testNode(tempNode, "220", "testStringValue220", false, 1);
        assertFalse(redBlackBst.containsKey("250"));

        /*
        rb bst visualisation :
        root ----> 242(B)
                  /     \
             240(B)     300(B)
        height = 2
        */
        assertEquals(redBlackBst.remove("220"), "testStringValue220");
        assertEquals(redBlackBst.height(), 2);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "242", "testStringValue242", false, 2);
        testNode(tempNode.right, "300", "testStringValue300", false, 1);
        testNode(tempNode.left, "240", "testStringValue240", false, 1);
        assertFalse(redBlackBst.containsKey("220"));

        /*
        rb bst visualisation :
        root ----> 300(B)
                  /
             240(R)
        height = 2
        */
        assertEquals(redBlackBst.remove("242"), "testStringValue242");
        assertEquals(redBlackBst.height(), 2);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "300", "testStringValue300", false, 2);
        testNode(tempNode.left, "240", "testStringValue240", true, 1);
        assertFalse(redBlackBst.containsKey("242"));

        /*
        rb bst visualisation :
        root ----> 240(B)
        height = 1
        */
        assertEquals(redBlackBst.remove("300"), "testStringValue300");
        assertEquals(redBlackBst.height(), 1);
        assertEquals(redBlackBst.size(), --size);
        tempNode = redBlackBst.getRoot();
        testNode(tempNode, "240", "testStringValue240", false, 1);
        assertFalse(redBlackBst.containsKey("300"));

         /*
        rb bst visualisation :
        root ----> null
        height = 0
        */
        assertEquals(redBlackBst.remove("240"), "testStringValue240");
        assertEquals(redBlackBst.height(), 0);
        assertEquals(redBlackBst.size(), --size);
        assertFalse(redBlackBst.containsKey("240"));
        assertTrue(redBlackBst.empty());

    }

    @Test
    void testWorkingSize() {
        int size = 0;
        assertEquals(redBlackBstInterface.size(), size);

        redBlackBstInterface.put("testStringKey1", "testStringValue1");

        assertEquals(redBlackBstInterface.size(), ++size);

        redBlackBstInterface.put("testStringKey2", "testStringValue2");

        assertEquals(redBlackBstInterface.size(), ++size);

        redBlackBstInterface.put("testStringKey2", "testStringValue3");

        assertEquals(redBlackBstInterface.size(), size);
        assertEquals(redBlackBstInterface.remove("testStringKey1"), "testStringValue1");
        assertEquals(redBlackBstInterface.size(), --size);
        assertNull(redBlackBstInterface.remove("not exist key"));
        assertEquals(redBlackBstInterface.size(), size);
        assertEquals(redBlackBstInterface.get("testStringKey2"), "testStringValue3");
        assertEquals(redBlackBstInterface.size(), size);
        assertNull(redBlackBstInterface.get("not exist key"));
        assertEquals(redBlackBstInterface.size(), size);
    }

    @AfterEach
    void clear() {
        redBlackBstInterface.clear();
        redBlackBst.clear();
    }

    void testNode(RedBlackBst.Node node, String key, String value, boolean color, int height) {
        assertEquals(node.key, key);
        assertEquals(node.value, value);
        assertEquals(node.color, color);
        assertEquals(node.height, height);
    }
}