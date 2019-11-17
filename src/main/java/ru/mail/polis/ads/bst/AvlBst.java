package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
    implements Bst<Key, Value> {

    private class Node {

        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int height;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        private int getHeight() {
            return height;
        }

        private void fixHeight() {
            height = 1 + Math.max((left != null ? left.height : 0), (right != null ? right.height : 0));
        }
    }

    private Node top;
    private int size;

    @Override
    public Value get(Key key) {
        Node node = findNode(top, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node findNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return findNode(node.left, key);
        }
        return findNode(node.right, key);
    }

    @Override
    public void put(Key key, Value value) {
        top = put(top, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.fixHeight();
        return balance(node);
    }

    @Override
    public Value remove(Key key) {

        // Ищем элемент для удаления
        Node deletedParent = null;
        Node deleted = top;
        while (deleted != null && key.compareTo(deleted.key) != 0) {
            deletedParent = deleted;
            if (key.compareTo(deleted.key) < 0) {
                deleted = deleted.left;
            } else {
                deleted = deleted.right;
            }
        }

        // Элемент не найден
        if (deleted == null) {
            return null;
        }

        // Элемент найден

        size--;

        // Если нет правого, то просто перекидываем ссылки
        if (deleted.right == null) {
            if (deletedParent == null) {
                top = deleted.left;
            } else {
                if (deletedParent.left == deleted) {
                    deletedParent.left = deleted.left;
                } else {
                    deletedParent.right = deleted.left;
                }
            }
            balance(deletedParent);
            return deleted.value;
        }

        // Ищем наименьший элемент справа
        Node leastRightParent = deleted;
        Node leastRight = deleted.right;

        while (leastRight.left != null) {
            leastRightParent = leastRight;
            leastRight = leastRight.left;
        }

        // Удаляем наименьший элемент справа и вешаем на него детей удаляемого элемента
        leastRightParent.left = leastRight.right;
        leastRight.left = deleted.left;
        if (deleted.right != leastRight) {
            leastRight.right = deleted.right;
        }

        // Привязываем новый элемент к дереву
        if (deletedParent == null) {
            top = leastRight;
        } else if (deletedParent.left == deleted) {
            deletedParent.left = leastRight;
        } else {
            deletedParent.right = leastRight;
        }
        leastRight.fixHeight();
        return deleted.value;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }


    @Override
    public Key min() {
        if (top == null) {
            return null;
        }
        return min(top).key;
    }

    @Override
    public Value minValue() {
        if (top == null) {
            return null;
        }
        return min(top).value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public Key max() {
        if (top == null) {
            return null;
        }
        return max(top).key;
    }

    @Override
    public Value maxValue() {
        if (top == null) {
            return null;
        }
        return max(top).value;
    }

    private Node floor(Node node, Key key) {
        if (node.right != null && key.compareTo(node.right.key) > 0) {
            return floor(node.right, key);
        }
        if (node.left != null && key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        if (key.compareTo(node.key) < 0) {
            return null;
        }
        return node;
    }

    @Override
    public Key floor(Key key) {
        if (top == null) {
            return null;
        }
        Node floor = floor(top, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node ceil(Node node, Key key) {
        if (node.left != null && key.compareTo(node.left.key) < 0) {
            return ceil(node.left, key);
        }
        if (node.right != null && key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        if (key.compareTo(node.key) > 0) {
            return null;
        }
        return node;
    }

    @Override
    public Key ceil(Key key) {
        if (top == null) {
            return null;
        }
        Node ceil = ceil(top, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        if (top == null) {
            return 0;
        }
        return top.getHeight();
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        node.fixHeight();
        leftNode.fixHeight();
        return leftNode;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        node.fixHeight();
        rightNode.fixHeight();
        return rightNode;
    }

    private int factor(Node node) {
        return (node.left == null ? 0 : node.left.getHeight()) - (node.right == null ? 0 : node.right.getHeight());
    }

    private Node balance(Node node) {
        if (node == null) {
            return null;
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }
}
