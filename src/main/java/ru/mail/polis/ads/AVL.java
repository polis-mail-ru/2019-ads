package ru.mail.polis.ads.bst;

class AVL<Key extends Comparable<Key>, Value> {
    int size = 0;
    public class Node {
        private int h;
        private int balance;
        Key key;
        Value value;
        private Node left, right, father;

        public Node(Key key, Value value, Node father) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.father = father;
            this.h = 1;
            this.balance = 0;
        }

        public Node next() {
            return floor(this.key);
        }

        public Node previus() {
            return ceil(this.key);
        }
    }

    private Node root;

    private Node floor(Key key) {
        Node p = root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp < 0) {
                if (p.left != null)
                    p = p.left;
                else
                    return p;
            } else {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Node father = p.father;
                    Node ch = p;
                    while (father != null && ch == father.right) {
                        ch = father;
                        father = father.father;
                    }
                    return father;
                }
            }
        }
        return null;
    }

    private Node ceil(Key key) {
        Node p = root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp > 0) {
                if (p.right != null)
                    p = p.right;
                else
                    return p;
            } else {
                if (p.left != null) {
                    p = p.left;
                } else {
                    Node father = p.father;
                    Node ch = p;
                    while (father != null && ch == father.left) {
                        ch = father;
                        father = father.father;
                    }
                    return father;
                }
            }
        }
        return null;
    }

    public Node getFirstNode() {
        return min(root);
    }

    public Node getLastNode() {
        return max(root);
    }

    private int height(Node x, Node y) {
        if (x == null && y == null) return 0;
        else if (x == null) return y.h;
        else if (y == null) return x.h;
        else return Math.max(x.h, y.h);
    }

    private int balance(Node x, Node y) {
        if (x == null && y == null) return 0;
        else if (x == null) return -y.h;
        else if (y == null) return x.h;
        else return x.h - y.h;
    }

    private Node put(Node node, Key key, Value value, Node father) {
        if (node == null) {
            Node newnode = new Node(key, value, father);
            return newnode;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            node.right = put(node.right, key, value, node);
            node.h = height(node.left, node.right) + 1;
        } else if (compareResult < 0) {
            node.left = put(node.left, key, value, node);
            node.h = height(node.left, node.right) + 1;
        } else {
            node.value = value;
        }
        node.balance = balance(node.left, node.right);
        if (node.balance == -2) {
            node = leftRotation(node);
        } else if (node.balance == 2) {
            node = rightRotation(node);
        }
        size++;
        return node;
    }

    private Node leftRotation(Node node) {
        if (node.right.right == null && node.right.left != null) {
            node.right = rightRotation(node.right);
            node = leftRotation(node);
        } else if (node.right.left == null || node.right.left.h <= node.right.right.h) {
            Node newnode = node.right;
            newnode.father = node.father;
            node.right = newnode.left;
            if (node.right != null)
                node.right.father = node;
            node.h = height(node.left, node.right) + 1;
            node.father = newnode;
            node.balance = balance(node.left, node.right);
            newnode.left = node;
            node = newnode;
            node.balance = balance(node.left, node.right);
            node.h = height(node.left, node.right) + 1;
        } else {
            node.right = rightRotation(node.right);
            node = leftRotation(node);
        }
        return node;
    }

    private Node rightRotation(Node node) {
        if (node.left.right != null && node.left.left == null) {
            node.left = leftRotation(node.left);
            node = rightRotation(node);
        } else if (node.left.right == null || node.left.right.h <= node.left.left.h) {
            Node newnode = node.left;
            newnode.father = node.father;
            node.left = newnode.right;
            if (node.left != null){
                node.left.father = node;
            }
            node.h = height(node.left, node.right) + 1;
            node.father = newnode;
            node.balance = balance(node.left, node.right);
            newnode.right = node;
            node = newnode;
            node.balance = balance(node.left, node.right);
            node.h = height(node.left, node.right) + 1;
        } else {
            node.left = leftRotation(node.left);
            node = rightRotation(node);
        }
        return node;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value, null);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            node.right = delete(node.right, key);
        } else if (compareResult < 0) {
            node.left = delete(node.left, key);
        } else {
            if (node.right == null && node.left == null) {
                node = null;
            } else if (node.right == null) {
                node.left.father = node.father;
                node = node.left;
            } else if (node.left == null) {
                node.right.father = node.father;
                node = node.right;
            } else {
                if (node.right.left == null) {
                    node.right.left = node.left;
                    node.right.father = node.father;
                    node.right.father = node.father;
                    node.left.father = node.right;
                    node = node.right;
                } else {
                    Node res = min(node.right);
                    node.key = res.key;
                    node.value = res.value;
                    delete(node.right, node.key);
                }
            }
        }
        if (node != null) {
            node.h = height(node.left, node.right) + 1;
            node.balance = balance(node.left, node.right);
            if (node.balance == -2) {
                node = leftRotation(node);
            } else if (node.balance == 2) {
                node = rightRotation(node);
            }
        }
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public Key minValue() {
        try {
            return min(root).key;
        } catch (Exception e) {

        }
        return null;
    }

    public Key maxValue() {
        try {
            return max(root).key;
        } catch (Exception e) {

        }
        return null;
    }

    private Node min(Node node) {
        if (root == null) return null;
        if (node.left == null) return node;
        return min(node.left);
    }

    private Node max(Node node) {
        if (root == null) return null;
        if (node.right == null) return node;
        return min(node.right);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            return node.value;
        } else if (compareResult > 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private void print(Node node, int level) {
        if (node != null) {
            print(node.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }
            System.out.println(node.key + "->" + node.value + " h=" + node.h + " balance=" + node.balance);
            print(node.left, level + 1);
        } else {
            System.out.println("null");
        }
    }

    public void print() {
        print(root, 0);
    }
    public void size() {
        System.out.println(size);
    }

    public void main(String[] args) {
        AVL<Integer, Integer> tree = new AVL<>();
//        tree.print();
//        tree.put(0, 500);
//        System.out.println(tree.maxValue());
//        System.out.println(tree.minValue());
        size();
    }
}