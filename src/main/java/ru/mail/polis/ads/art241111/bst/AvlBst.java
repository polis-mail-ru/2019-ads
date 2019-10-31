package ru.mail.polis.ads.art241111.bst;

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
            return '\n' + "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", left=" + left +
                    ", right=" + right+
                    ", height=" + height +
                    '}';
        }
    }

    private Node rootNode;

    // Height block
        private int height(Node x) {
            return x == null ? 0 : x.height;
        }

        private void fixHeight(Node x) {
            x.height = 1 + Math.max(height(x.left), height(x.right));
        }
    // End height block

    // Balance block
    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0)  x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
    }

        private int factor(Node x) {
            return height(x.left) - height(x.right);
        }

        private Node rotateRight(Node x) {
            Node left = x.left;
            x.left = left.right;
            left.right = x;

            fixHeight(x);
            fixHeight(left);

            return left;
        }

        private Node rotateLeft(Node x) {
            Node right = x.right;
            x.right = right.left;
            right.left = x;

            fixHeight(x);
            fixHeight(right);

            return right;
        }
    // End balance block

    // Get value
    @Override
    public Value get(Key key) {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search value
        Node node = get(rootNode,key);
        return node == null ? null : node.value;
    }

        private Node get(Node node, Key key) {
            while(node.key != null){
                if(node.key == key) return node;
                else if (key.compareTo(node.key) > 0) node = node.right;
                else node = node.left;
            }
            return null;
        }
    // End of block "get value"

    // Put value
    @Override
    public void put(Key key, Value value) {
        // Finding the right place
        rootNode = put(rootNode,key, value);

    }

    private Node put(Node node, Key key, Value value) {
        if(node == null){ return new Node(key, value);}
        else if (node.key.compareTo(key) > 0) node.left = put(node.left,key,value);
        else if (node.key.compareTo(key) < 0) node.right = put(node.right,key,value);
        else node.value = value;

        // Balance node
        fixHeight(node);
        node = balance(node);
        return node;
    }

    // End of block "put value"
    @Override
    public Value remove(Key key) {
        // Tree emptiness check
        if (rootNode == null) return null;
        Value value = get(key);
        rootNode = delete(rootNode, key);
        fixHeight(rootNode);
        return value;
        //return delete == null ? null : delete.value;
    }

        private Node delete(Node x, Key key) {
            if (x == null) return null;
            if (key.compareTo(x.key) < 0) x.left = delete(x.left, key);
            else if (key.compareTo(x.key) > 0) x.right = delete(x.right, key);
            else x = innerDelete(x);

            return x;
        }

        private Node innerDelete(Node node) {
            Node q = node.left;
            Node r = node.right;

            if( node.right == null ) return node.left;

            node = min(r);
            node.right = deleteMin(r);
            node.left = q;

            return balance(node);

        }

        private Node deleteMin(Node x) {
            if (x.left == null) {
                return x.right;
            }
            x.left = deleteMin(x.left);
            return balance(x);
        }


    // Search min
        private Node min(Node node) {
            while(node.left != null){
                node = node.left;
            }
            return node;
        }
    @Override
    public Key min() {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search min key
        return min(rootNode).key;
    }

    @Override
    public Value minValue() {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search value in min key
        return min(rootNode).value;
    }
    // End search min

    // Search max
        private Node max(Node node) {
            while(node.right != null){
                node = node.right;
            }
            return node;
        }

    @Override
    public Key max() {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search min key
        return max(rootNode).key;
    }

    @Override
    public Value maxValue() {
        // Tree emptiness check
        if (rootNode == null) return null;

        // Search min key
        return max(rootNode).value;
    }
    // End search max

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
        return 1 + size(node.left) + size(node.right);
    }


    @Override
    public int height() {
        if (rootNode == null) return 0;
        else return rootNode.height;
    }

    @Override
    public String toString() {
        return rootNode.toString();
    }
}
