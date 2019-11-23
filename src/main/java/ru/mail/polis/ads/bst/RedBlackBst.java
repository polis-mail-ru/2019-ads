package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;
    private int size;
    
    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.height = 1;
        }

    }

    public RedBlackBst() {
        this.root = null;
        this.size = 0;
    }

    public RedBlackBst(Node root) {
        this.root = root;
        this.size = 1;
    }

    public RedBlackBst(Map<? extends Key, ? extends Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<? extends Key, ? extends Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return findNode(key, root);
    }

    private Value findNode(Key key, Node node) {
        if (node == null) {
            return null;
        }

        int matching = key.compareTo(node.key);
        if (matching > 0) {
            return findNode(key, node.right);
        }
        else if (matching < 0) {
            return findNode(key, node.left);
        }
        else if (key == node.key) {
            return node.value;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        // к-ч дерево
        root = put(key, value, root);
        root.color = BLACK;
    }

    // https://www.geeksforgeeks.org/red-black-tree-set-2-insert/
    // Идем от корня до тех пор, пока следующий сын не лист;
    // вместо него вставляем новый элемент с красным цветом и с нулевыми потомками.
    // Далее проверки на баланс.
    // Визуализация: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int matching = key.compareTo(node.key);
        // тот же проход по дереву
        if (matching < 0) {
            node.left = put(key, value, node.left);
        }
        else if (matching > 0) {
            node.right = put(key, value, node.right);
        }
        else {
            node.value = value;
        }

        node = checkColor(node);
        node.height = newHeight(node);

        return node;
    }

    private int newHeight(Node node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node checkColor(Node node) {

        Node current = node;

        if (isRED(current.left) && isRED(current.left.left)) {
            current = rightRotate(current);
        }

        if (isRED(current.left) && isRED(current.right)) {
            swap(current);
        }

        if (isRED(current.right) && !isRED(current.left)) {
            current = leftRotate(current);
        }

        return current;
    }

    private boolean isRED(Node node) {
        return (node != null && node.color);
    }

    private Node swap(Node node) {
        node.left.color = !node.left.color;

        node.right.color = !node.right.color;

        node.color = !node.color;

        return node;
    }

    private Node rightRotate(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;

        node.height = newHeight(node);
        node.height = newHeight(lNode);

        lNode.color = node.color;
        node.color = RED;

        return lNode;
    }

    private Node leftRotate(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;

        node.height = newHeight(node);
        node.height = newHeight(rNode);

        rNode.color = node.color;
        node.color = RED;

        return rNode;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        return null;
    }

    @Nullable
    @Override
    public Key min() {
        return root != null ? findMin(root).key : null;
    }

    private Node findMin(Node node) {
        if (node.left != null) {
            return findMin(node.left);
        }
        return node;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root != null ? findMin(root).value : null;
    }

    @Nullable
    @Override
    public Key max() {
        return root != null ? findMax(root).key : null;
    }

    private Node findMax(Node node) {
        if (node.right != null) {
            return findMax(node.right);
        }
        return node;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root != null ? findMax(root).value : null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return root!= null ? findFloor(key, root) : null;
    }

    private Key findFloor(Key key, Node node) {
        if (key.compareTo(node.key) < 0) {
            if (node.right != null) {
                return findCeil(key, node.left);
            }
            else {
                return null;
            }
        }
        else {
            if (node.right != null) {
                if (key.compareTo(node.right.key) < 0) {
                    if (node.right.left != null) {
                        return findCeil(key, node.right.left);
                    }
                    else {
                        return node.key;
                    }
                }
                else {
                    return findCeil(key, node.right);
                }
            }
            else {
                return node.key;
            }
        }
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return root != null ? findCeil(key, root) : null;
    }

    private Key findCeil(Key key, Node node) {
       if (key.compareTo(node.key) > 0) {
           if (node.right != null) {
               return findCeil(key, node.right);
           }
           else {
               return null;
           }
       }
       else {
           if (node.left != null) {
               if (key.compareTo(node.left.key) > 0) {
                   if (node.left.right != null) {
                       return findCeil(key, node.left.right);
                   }
                   else {
                       return node.key;
                   }
               }
               else {
                   return findCeil(key, node.left);
               }
           }
           else {
               return node.key;
           }
       }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
}
