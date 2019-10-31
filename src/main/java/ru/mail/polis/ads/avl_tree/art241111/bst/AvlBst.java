package ru.mail.polis.ads.avl_tree.art241111.bst;

/**
 * AVL implementation of binary search tree.
 *
 * Create by Artem Gerasimov
 * Date 30.10.2019
 * GIT: art241111
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height = 1;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "\n" + "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    ", height=" + height +
                    '}';
        }
    }

    private Node rootNode;

    @Override
    public Value get(Key key) {
        return get(rootNode, key);
    }

        private Value get(Node node, Key key) {
            if (node == null) return null;

            if (key.compareTo(node.key) < 0) return get(node.left, key);
            if (key.compareTo(node.key) > 0) return get(node.right, key);

            return node.value;
        }

    @Override
    public void put(Key key, Value value) {
        rootNode = put(rootNode, key, value);
    }

        private Node put(Node node, Key key, Value value) {
            if (node == null) return new Node(key, value);

            if (key.compareTo(node.key) < 0) node.left = put(node.left, key, value);
            else if (key.compareTo(node.key) > 0) node.right = put(node.right, key, value);
            else node.value = value;

            fixHeight(node);
            node = balance(node);
            return node;
        }

        private Node balance(Node node) {
            if (factor(node) == 2) {
                if (factor(node.left) < 0) node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (factor(node) == -2) {
                if (factor(node.right) > 0)  node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
            return node;
        }

            private int factor(Node node) {
                return height(node.left) - height(node.right);
            }

            private Node rotateRight(Node node) {
                Node left = node.left;
                node.left = left.right;
                left.right = node;

                fixHeight(node);
                fixHeight(left);

                return left;
            }

            private Node rotateLeft(Node node) {
                Node right = node.right;
                node.right = right.left;
                right.left = node;

                fixHeight(node);
                fixHeight(right);

                return right;
            }

    @Override
    public Value remove(Key key) {
        return remove(rootNode, key).value;
    }

        private Node remove(Node node, Key key) {
            if (node == null) return null;

            if (key.compareTo(node.key) < 0) node.left = remove(node.left, key);
            else if (key.compareTo(node.key) > 0) node.right = remove(node.right, key);
            else node = innerDelete(node);

            fixHeight(node);
            balance(node);

            return node;
        }

            private Node innerDelete(Node node) {
                if (node.right == null) return node.left;
                if (node.left == null) return node.right;

                Node t = node;

                node = minNode(t.right);
                node.right = deleteMin(t.right);
                node.left = t.left;

                return node;
            }

                private Node minNode(Node node) {
                    if (node.left == null) return node;
                    return minNode(node.left);
                }

                private Node deleteMin(Node node) {
                    if (node.left == null) {
                        return node.right;
                    }
                    node.left = deleteMin(node.left);
                    return node;
                }

    @Override
    public Key min() {
        return rootNode != null ? min(rootNode).key: null;
    }

        private Node min(Node node) {
            if (node == null) return null;

            if (node.left == null) return node;
            return min(node.left);
        }

    @Override
    public Value minValue() {
        return rootNode != null ? min(rootNode).value: null;
    }

    @Override
    public Key max() {
        return rootNode != null ? max(rootNode).key: null;
    }

        private Node max(Node node) {
            if (node == null) return null;

            if (node.right == null) return node;
            return max(node.right);
        }

    @Override
    public Value maxValue() {
        return rootNode != null ? max(rootNode).value: null;
    }


    @Override
    public Key floor(Key key) {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search value
        Node node = rootNode;
        Key min = null;
        while(node != null){
            if (key.compareTo(node.key) == 0) return node.key;
            else if (key.compareTo(node.key) > 0) {
                if ((node.right == null) ||
                        (node.right.key.compareTo(key) > 0)){
                    min = node.key;
                }
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                node = node.left;
            }
        }
        return min;
    }

    @Override
    // min max
    public Key ceil(Key key) {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search value
        Node node = rootNode;
        Key min = null;

        while(node != null){
            if (key.compareTo(node.key) == 0) return node.key;
            else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else if (key.compareTo(node.key) < 0) {
                if ((node.left == null) ||
                        (node.left.key.compareTo(key) < 0)){
                    min = node.key;
                }
                node = node.left;
            }
        }
        return min;
    }


    @Override
    public int size() {
        return size(rootNode);
    }

        private int size(Node node) {
            if (node == null) return 0;
            return size(node.left) + size(node.right) + 1;
        }

    @Override
    public int height() {
        return height(rootNode);
    }

        private int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private void fixHeight(Node node) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
}