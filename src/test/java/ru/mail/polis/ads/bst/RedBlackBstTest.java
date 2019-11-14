package ru.mail.polis.ads.bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

//        redBlackBst.remove("testStringKey0");
//        assertNull(redBlackBst.get("testStringKey0"));
//
//        redBlackBst.remove("testStringKey1");
//        assertNull(redBlackBst.get("testStringKey1"));
//
//        redBlackBst.remove("testStringKey2");
//        assertNull(redBlackBst.get("testStringKey2"));
//
//        redBlackBst.remove("testStringKey3");
//        assertNull(redBlackBst.get("testStringKey3"));
//
//        redBlackBst.remove("testStringKey4");
//        assertNull(redBlackBst.get("testStringKey4"));
//
//        redBlackBst.remove("testStringKey5");
//        assertNull(redBlackBst.get("testStringKey5"));
//
//        assertEquals(redBlackBst.size(), 0);
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
    void testWorkingRemove() {
        assertThrows(UnsupportedOperationException.class, () -> redBlackBstInterface.remove("fefwefs"));
//        assertNull(redBlackBst.remove("case when bst is empty"));
//        assertTrue(redBlackBst.empty());
//
//        redBlackBst.put("testStringKey3", "testStringValue3");
//        redBlackBst.put("testStringKey4", "testStringValue4");
//        redBlackBst.put("testStringKey2", "testStringValue2");
//        redBlackBst.put("testStringKey5", "testStringValue5");
//        redBlackBst.put("testStringKey1", "testStringValue1");
//        redBlackBst.put("testStringKey0", "testStringValue0");
//
//        assertFalse(redBlackBst.empty());
//        int size = redBlackBst.size();
//
//        assertEquals(redBlackBst.remove("testStringKey4"), "testStringValue4");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey4"));
//
//        assertEquals(redBlackBst.remove("testStringKey1"), "testStringValue1");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey1"));
//
//        assertNull(redBlackBst.remove("testStringKey1"), "testStringValue1");
//        assertEquals(redBlackBst.size(), size);
//        assertFalse(redBlackBst.empty());
//        assertFalse(redBlackBst.containsKey("testStringKey1"));
//
//        assertEquals(redBlackBst.remove("testStringKey3"), "testStringValue3");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey3"));
//
//        assertEquals(redBlackBst.remove("testStringKey0"), "testStringValue0");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey0"));
//
//        assertEquals(redBlackBst.remove("testStringKey2"), "testStringValue2");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey2"));
//
//        assertEquals(redBlackBst.remove("testStringKey5"), "testStringValue5");
//        assertEquals(redBlackBst.size(), --size);
//        assertFalse(redBlackBst.containsKey("testStringKey5"));
//
//        assertTrue(redBlackBst.empty());
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

//        redBlackBstInterface.remove("testStringKey5");
//
//        assertEquals(redBlackBstInterface.max(), "testStringKey7");
//        assertEquals(redBlackBstInterface.maxValue(), "testStringValue7");
//
//        redBlackBstInterface.remove("testStringKey7");
//
//        assertEquals(redBlackBstInterface.max(), "testStringKey2");
//        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");
//
//        redBlackBstInterface.remove("testStringKey0");
//
//        assertEquals(redBlackBstInterface.max(), "testStringKey2");
//        assertEquals(redBlackBstInterface.maxValue(), "testStringValue2");
//
//        redBlackBstInterface.remove("testStringKey2");
//
//        assertNull(redBlackBstInterface.max());
//        assertNull(redBlackBstInterface.maxValue());
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

//        redBlackBst.remove("testStringKey5");
//
//        assertEquals(redBlackBst.min(), "testStringKey0");
//        assertEquals(redBlackBst.minValue(), "testStringValue0");
//
//        redBlackBst.remove("testStringKey0");
//
//        assertEquals(redBlackBst.min(), "testStringKey3");
//        assertEquals(redBlackBst.minValue(), "testStringValue3");
//
//        redBlackBst.remove("testStringKey9");
//
//        assertEquals(redBlackBst.min(), "testStringKey3");
//        assertEquals(redBlackBst.minValue(), "testStringValue3");
//
//        redBlackBst.remove("testStringKey3");
//
//        assertNull(redBlackBst.min());
//        assertNull(redBlackBst.minValue());
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

//        redBlackBst.remove("testStringKey");
//        assertTrue(redBlackBst.containsKey("testStringKey1"));
//        assertFalse(redBlackBst.containsKey("testStringKey"));
//        assertTrue(redBlackBst.containsValue("testStringValue1"));
//        assertFalse(redBlackBst.containsValue("testStringValue"));
//
//        redBlackBst.remove("testStringKey1");
//        assertFalse(redBlackBst.containsKey("testStringKey"));
//        assertFalse(redBlackBst.containsKey("testStringKey1"));
//        assertFalse(redBlackBst.containsValue("testStringValue"));
//        assertFalse(redBlackBst.containsValue("testStringValue1"));
    }

    @Test
    void testWorkingEmpty() {
        assertTrue(redBlackBstInterface.empty());

        redBlackBstInterface.put("testStringKey", "testStringValue");
        assertFalse(redBlackBstInterface.empty());

        redBlackBstInterface.put("testStringKey1", "testStringValue1");
        assertFalse(redBlackBstInterface.empty());

//        redBlackBst.remove("testStringKey");
//        assertFalse(redBlackBst.empty());
//
//        redBlackBst.remove("testStringKey1");
//        assertTrue(redBlackBst.empty());
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
        redBlackBstInterface.put("900", "testStringValue0");
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
        redBlackBstInterface.put("400", "testStringValue0");
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
//        assertEquals(redBlackBst.remove("testStringKey1"), "testStringValue1");
//        assertEquals(redBlackBst.size(), --size);
//        assertNull(redBlackBst.remove("not exist key"));
//        assertEquals(redBlackBst.size(), size);
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