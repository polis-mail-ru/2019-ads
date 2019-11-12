package ru.mail.polis.ads.bst;

/**
 * AVL implementation of binary search tree.
 */
public class BstAvlImpl<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private Node node;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(Key key) {
        return getValue(node, key);
    }

    @Override
    public void put(Key key, Value value) {
        node = putNode(node, key, value);
    }

    @Override
    public Value remove(Key key) {
        return removeKey(node, key).value;
    }

    @Override
    public Key min() {
        return minKey(node);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return maxKey(node);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(Key key) {
        Key maxKey = null;
        return floorKey(node, key, maxKey);
    }

    @Override
    public Key ceil(Key key) {
        Key minKey = null;
        return ceilKey(node, key, minKey);
    }

    @Override
    public int size() {
        return sizeNode(node);
    }

    @Override
    public int height() {
        return node != null ? node.height : 0;
    }

    private Value getValue(Node node, Key key) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                return getValue(node.left, key);
            }
            if (key.compareTo(node.key) > 0) {
                return getValue(node.right, key);
            }
            return node.value;
        }
        return null;
    }

    private Node putNode(Node node, Key key, Value value) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                node.left = putNode(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = putNode(node.right, key, value);
            } else {
                node.value = value;
            }
            fixedNodeHeight(node);
            return balanceNode(node);
        }
        return new Node(key, value, 1);
    }

    private Node removeKey(Node node, Key key) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                node.left = removeKey(node.left, key);
            } else if (key.compareTo(node.key) > 0) {
                node.right = removeKey(node.right, key);
            } else {
                node = deleteInnerNode(node);
            }
            fixedNodeHeight(node);
            return balanceNode(node);
        }
        return null;
    }

    private Key minKey(Node node) {
        if (node != null) {
            if (node.left == null) {
                return minKey(node.left);
            }
            return node.key;
        }
        return null;
    }

    private Key maxKey(Node node) {
        if (node != null) {
            if (node.right != null) {
                return maxKey(node.right);
            }
            return node.key;
        }
        return null;
    }

    private Key floorKey(Node node, Key key, Key maxKey) {
        if (node != null) {
            if (key.compareTo(node.key) < 0) {
                maxKey = floorKey(node.left, key, maxKey);
            } else if (key.compareTo(node.key) > 0) {
                if (maxKey == null || maxKey.compareTo(node.key) < 0) {
                    maxKey = node.key;
                }
                maxKey = floorKey(node.right, key, maxKey);
            } else {
                maxKey = node.key;
            }
        }
        return maxKey;
    }

    private Key ceilKey(Node node, Key key, Key minKey) {
        if (node != null) {
            if (key.compareTo(node.key) > 0) {
                minKey = ceilKey(node.right, key, minKey);
            } else if (key.compareTo(node.key) < 0) {
                if (minKey == null || minKey.compareTo(node.key) > 0) {
                    minKey = node.key;
                }
                minKey = ceilKey(node.left, key, minKey);
            } else {
                minKey = node.key;
            }
        }
        return minKey;
    }

    private int sizeNode(Node node) {
        if (node != null) {
            int size = sizeNode(node.left);
            size += sizeNode(node.right);
            return size + 1;
        }
        return 0;
    }

    private Node balanceNode(Node node) {
        if (factorNode(node) == 2) {
            if (factorNode(node.left) < 0) {
                node.left = rotateLeftNode(node.left);
            }
            return rotateRightNode(node);
        }
        if (factorNode(node) == -2) {
            if (factorNode(node.right) > 0) {
                node.right = rotateRightNode(node.right);
            }
            return rotateLeftNode(node);
        }
        return node;
    }

    private int factorNode(Node node) {
        int leftHeight = node.left != null ? node.left.height : 0;
        int rightHeight = node.right != null ? node.right.height : 0;
        return leftHeight - rightHeight;
    }

    private Node rotateRightNode(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixedNodeHeight(node);
        fixedNodeHeight(left);
        return left;
    }

    private Node rotateLeftNode(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixedNodeHeight(node);
        fixedNodeHeight(right);
        return right;
    }

    private void fixedNodeHeight(Node node) {
        int leftHeight = node.left != null ? node.left.height : 0;
        int rightHeight = node.right != null ? node.right.height : 0;
        node.height = Math.max(leftHeight, rightHeight) + 1;
    }

    private Node deleteInnerNode(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node inner = node;
        node = minNode(inner.right);
        node.right = deleteMinNode(inner.right);
        node.left = inner.left;
        return node;
    }

    private Node minNode(Node node) {
        if (node.left != null) {
            return minNode(node.left);
        }
        return node;
    }

    private Node deleteMinNode(Node node) {
        if (node.left != null) {
            node.left = deleteMinNode(node.left);
            return node;
        }
        return node.right;
    }
}
