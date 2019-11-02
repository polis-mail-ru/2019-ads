package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private Node head;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }


    private Node getNode(Key key) {
        Node curNode = head;

        while (curNode != null && curNode.key.compareTo(key) != 0) {
            curNode = (key.compareTo(curNode.key) > 0) ? curNode.right : curNode.left;
        }

        return curNode;
    }


    @Override
    public Value get(Key key) {
        Node soughtNode = getNode(key);
        return soughtNode == null ? null : soughtNode.value;
    }


    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node.height;
    }


    private Node leftRotation(Node node) {
        Node nodeParent = getParent(node.key);
        Node nodeChild = node.right;

        node.right = nodeChild.left;
        nodeChild.left = node;

        if (nodeParent != null) {
            if (nodeParent.left != null && nodeParent.left.key.compareTo(node.key) == 0) {
                nodeParent.left = nodeChild;
            } else {
                nodeParent.right = nodeChild;
            }
        }

        return (nodeParent == null) ? nodeChild : nodeParent;
    }


    private Node rightRotation(Node node) {
        Node nodeParent = getParent(node.key);
        Node nodeChild = node.left;

        node.left = nodeChild.right;
        nodeChild.right = node;

        if (nodeParent != null) {
            if (nodeParent.left != null && nodeParent.left.key.compareTo(node.key) == 0) {
                nodeParent.left = nodeChild;
            } else {
                nodeParent.right = nodeChild;
            }
        }

        return (nodeParent == null) ? nodeChild : nodeParent;
    }


    private void rotation() {
        // высота правого поддерева больше
        if ( head.right != null &&
                ( (head.left == null  && head.right.height > 1)  ||
                        (head.left != null  && head.right.height - head.left.height > 1 )) ){

            if (head.right.left != null &&
                    (head.right.right == null ||
                            head.right.left.height - head.right.right.height > 1)) {
                head = rightRotation(head.right);
                head = leftRotation(head);
            } else {
                head = leftRotation(head);
            }

            // высота левого поддерева больше
        } else if (head.left != null &&
                ((head.right == null && head.left.height > 1) ||
                        (head.right != null && head.left.height - head.right.height > 1)) ) {

            if (head.left.right != null &&
                    (head.left.left == null ||
                            head.left.right.height - head.left.left.height > 1)) {
                head = leftRotation(head.left);
                head = rightRotation(head);
            } else {
                head = rightRotation(head);
            }
        }
    }


    @Override
    public void put(Key key, Value value) {
        Node curNode;
        Node nextNode;

        if (head == null) {
            head = new Node(key, value, 1);
            return;
        }

        if ( (curNode = getNode(key)) != null) {
            curNode.value = value;
            return;
        }

        curNode = head;
        nextNode = curNode;

        while (nextNode != null) {
            curNode = nextNode;
            nextNode = (key.compareTo(curNode.key) > 0) ? curNode.right : curNode.left;
        }

        if (key.compareTo(curNode.key) > 0) {
            curNode.right = new Node(key, value, 1);
        } else {
            curNode.left = new Node(key, value, 1);
        }

        height(head);
        rotation();
        height(head);
    }


    private Node getParent(Key childKey) {
        Node curNode = head;
        Node nextNode = curNode;

        if (curNode == null || curNode.key.compareTo(childKey) == 0) {
            return null;
        }

        while (nextNode.key.compareTo(childKey) != 0) {
            curNode = nextNode;
            nextNode = (childKey.compareTo(nextNode.key) > 0) ? nextNode.right : nextNode.left;
        }

        return curNode;
    }


    private void removeNodeWithNoChildren(Node removingNode) {
        Node removingNodeParent = getParent(removingNode.key);

        if (removingNodeParent == null) {
            head = null;
            return;
        }

        if (removingNodeParent.left.key.compareTo(removingNode.key) == 0) {
            removingNodeParent.left = null;
        } else {
            removingNodeParent.right = null;
        }
    }


    private void removeNodeWithOneChild(Node removingNode) {
        Node removingNodeParent = getParent(removingNode.key);

        if (removingNodeParent == null) {
            head = (removingNode.left == null) ? removingNode.right : removingNode.left;
            return;
        }

        if (removingNodeParent.left.key.compareTo(removingNode.key) == 0) {
            removingNodeParent.left = (removingNode.left == null) ? removingNode.right : removingNode.left;
        } else {
            removingNodeParent.right = (removingNode.left == null) ? removingNode.right : removingNode.left;
        }
    }


    private void removeNodeWithTwoChildren(Node removingNode) {
        Node removingNodeParent = getParent(removingNode.key);
        Node deputyNode = getNodeWithMinKey(removingNode.right); //заместитель
        Node deputyNodeParent = getParent(deputyNode.key);

        // отсоединяем заместителя
        if (deputyNodeParent.left.key.compareTo(deputyNode.key) == 0) {
            deputyNodeParent.left = null;
        } else {
            deputyNodeParent.right = null;
        }
        // подсоединяем заместителя
        if (removingNodeParent == null) {
            head = deputyNode;
        } else {
            if (removingNodeParent.left.key.compareTo(removingNode.key) == 0) {
                removingNodeParent.left = deputyNode;
            } else {
                removingNodeParent.right = deputyNode;
            }
        }

        deputyNode.left = removingNode.left;
        deputyNode.right = removingNode.right;
    }


    @Override
    public void remove(Key key) {
        Node removingNode = getNode(key);

        if (removingNode == null) {
            return;
        }

        if (removingNode.left == null && removingNode.right == null) {
            removeNodeWithNoChildren(removingNode);
            rotation();
            height(head);
            return;
        }

        if (removingNode.left == null || removingNode.right == null) {
            removeNodeWithOneChild(removingNode);
            rotation();
            height(head);
            return;
        }

        removeNodeWithTwoChildren(removingNode);
        rotation();
        height(head);
    }


    private Node getNodeWithMinKey(Node start) {
        Node curNode = start;

        while (curNode != null && curNode.left != null) {
            curNode = curNode.left;
        }

        return curNode;
    }


    @Override
    public Key min() {
        Node nodeWithMinKey = getNodeWithMinKey(head);
        return nodeWithMinKey == null ? null : nodeWithMinKey.key;
    }

    @Override
    public Value minValue() {
        Node nodeWithMinKey = getNodeWithMinKey(head);
        return nodeWithMinKey == null ? null : nodeWithMinKey.value;
    }


    private Node getNodeWithMaxKey(Node start) {
        Node curNode = start;

        while (curNode != null && curNode.right != null) {
            curNode = curNode.right;
        }

        return curNode;
    }


    @Override
    public Key max() {
        Node nodeWithMaxKey = getNodeWithMaxKey(head);
        return nodeWithMaxKey == null ? null : nodeWithMaxKey.key;
    }

    @Override
    public Value maxValue() {
        Node nodeWithMaxKey = getNodeWithMaxKey(head);
        return nodeWithMaxKey == null ? null : nodeWithMaxKey.value;
    }

    @Override
    public Key floor(Key key) {
        Key result = null;
        Node curNode = head;

        while (curNode != null) {
            if (curNode.key.compareTo(key) == 0) {
                return curNode.key;
            }

            if ( (result == null && curNode.key.compareTo(key) < 0) ||
                    (result != null && curNode.key.compareTo(key) < 0 && curNode.key.compareTo(result) > 0)) {
                result = curNode.key;
            }

            curNode = (key.compareTo(curNode.key) > 0) ? curNode.right : curNode.left;
        }

        return result;
    }

    @Override
    public Key ceil(Key key) {
        Key result = null;
        Node curNode = head;

        while (curNode != null) {
            if (curNode.key.compareTo(key) == 0) {
                return key;
            }

            if ( (result == null && curNode.key.compareTo(key) > 0) ||
                    ( result != null && curNode.key.compareTo(key) > 0 && curNode.key.compareTo(result) < 0)) {
                result = curNode.key;
            }

            curNode = (key.compareTo(curNode.key) > 0) ? curNode.right : curNode.left;
        }

        return result;
    }

    private int size(Node node) {
        return (node == null) ? 0 : ( size(node.left) + size(node.right) + 1 );
    }

    @Override
    public int size() {
        return size(head);
    }

    @Override
    public int height() {
        return (head == null) ? 0 : head.height;
    }
}
