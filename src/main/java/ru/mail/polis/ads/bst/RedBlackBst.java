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
        @NotNull Key key;
        @NotNull Value value;
        @Nullable Node left;
        @Nullable Node right;
        boolean color;
        int height;

        Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.height = 1;
        }
    }

    private RedBlackNodeForRemoveTask nil = new RedBlackNodeForRemoveTask();
    private RedBlackNodeForRemoveTask rmRoot = nil;

    class RedBlackNodeForRemoveTask {
        static final int rmBLACK = 0;
        static final int rmRED = 1;
        Key key;
        Value value;

        RedBlackNodeForRemoveTask rmParent;
        RedBlackNodeForRemoveTask rmLeft;
        RedBlackNodeForRemoveTask rmRight;
        int numLeft;
        int numRight;
        int color;

        RedBlackNodeForRemoveTask() {
            color = rmBLACK;
            numLeft = 0;
            numRight = 0;
            rmParent = null;
            rmLeft = null;
            rmRight = null;
        }

        RedBlackNodeForRemoveTask(Key key, Value value) {
            this();
            this.key = key;
            this.value = value;
        }
    }

    public RedBlackBst() {
        this.root = null;
        this.size = 0;
        rmRoot.rmLeft = nil;
        rmRoot.rmRight = nil;
        rmRoot.rmParent = nil;
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

    private RedBlackNodeForRemoveTask findNode(Key key) {
        RedBlackNodeForRemoveTask current = rmRoot;

        while (current != nil) {
            if (current.key.equals(key)) {
                return current;
            }
            else if (current.key.compareTo(key) < 0) {
                current = current.rmRight;
            }
            else {
                current = current.rmLeft;
            }
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
        RedBlackNodeForRemoveTask z = findNode(key);

        RedBlackNodeForRemoveTask x = nil;
        RedBlackNodeForRemoveTask y = nil;

        if (z != null) {
            if ((z.rmLeft == nil) || (z.rmRight == nil)) {
                y = z;
            }
            else {
                y = treeSuccessor(z);
            }
        }

        if (y.rmParent != nil) {
            x = y.rmLeft;
        }
        else {
            x = y.rmRight;
        }

        x.rmParent = y.rmParent;

        if (y.rmParent == nil) {
            rmRoot = x;
        }
        else if ((y.rmParent.rmLeft != nil) && (y.rmParent.rmLeft == y)) {
            y.rmParent.rmLeft = x;
        }
        else if (y != z) {
            if (z != null) {
                z.key = y.key;
            }
        }

        fixNodeData(x, y);

        if (y.color == RedBlackNodeForRemoveTask.rmBLACK) {
            removeFixup(x);
        }

        if (z != null) {
            return z.value;
        }
        return null;
    }

    private RedBlackNodeForRemoveTask treeSuccessor(RedBlackNodeForRemoveTask x) {
        if (x.rmLeft != nil) {
            return findMin(x.rmRight);
        }

        RedBlackNodeForRemoveTask y = x.rmParent;

        while ((y != nil) && (x == y.rmRight)) {
            x = y;
            y = y.rmParent;
        }

        return y;
    }

    private void fixNodeData(RedBlackNodeForRemoveTask x, RedBlackNodeForRemoveTask y) {
        RedBlackNodeForRemoveTask current = nil;
        RedBlackNodeForRemoveTask track = nil;

        if (x == nil) {
            current = y.rmParent;
            track = y;
        }
        else {
            current = x.rmParent;
            track = x;
        }

        while (current != nil) {
            if (y.key != current.key) {
                if (y.key.compareTo(current.key) > 0) {
                    current.numRight--;
                }

                if (y.key.compareTo(current.key) < 0) {
                    current.numLeft--;
                }
            }
            else {
                if (current.rmLeft == nil) {
                    current.numLeft--;
                }
                else if (current.rmRight == nil) {
                    current.numRight--;
                }
                else if (track == current.rmRight) {
                    current.numRight--;
                }
                else if (track == current.rmLeft) {
                    current.numLeft--;
                }
            }
        }

        track = current;
        current = current.rmParent;
    }

    private void removeFixup(RedBlackNodeForRemoveTask x) {
        RedBlackNodeForRemoveTask w;

        while ((x != rmRoot) && (x.color == RedBlackNodeForRemoveTask.rmBLACK)) {
            if (x == x.rmParent.rmLeft) {
                w = x.rmParent.rmRight;

                if (w.color == RedBlackNodeForRemoveTask.rmRED) {
                    w.color = RedBlackNodeForRemoveTask.rmBLACK;
                    x.rmParent.color = RedBlackNodeForRemoveTask.rmRED;

                    rmLeftRotate(x.rmParent);

                    w = x.rmParent.rmRight;
                }

                if ((w.rmLeft.color == RedBlackNodeForRemoveTask.rmBLACK) && (w.rmRight.color == RedBlackNodeForRemoveTask.rmBLACK)) {
                    w.color = RedBlackNodeForRemoveTask.rmRED;
                    x = x.rmParent;
                }
                else {
                    if (w.rmRight.color == RedBlackNodeForRemoveTask.rmBLACK) {
                        w.rmLeft.color = RedBlackNodeForRemoveTask.rmBLACK;
                        w.color = RedBlackNodeForRemoveTask.rmRED;

                        rmRightRotate(w);
                        w = x.rmParent.rmRight;
                    }

                    w.color = x.rmParent.color;
                    x.rmParent.color = RedBlackNodeForRemoveTask.rmBLACK;
                    w.rmRight.color = RedBlackNodeForRemoveTask.rmBLACK;

                    rmLeftRotate(x.rmParent);
                    x = rmRoot;
                }
            }
            else {
                w = x.rmParent.rmLeft;

                if (w.color == RedBlackNodeForRemoveTask.rmRED) {
                    w.color = RedBlackNodeForRemoveTask.rmBLACK;
                    x.rmParent.color = RedBlackNodeForRemoveTask.rmRED;

                    rmRightRotate(x.rmParent);
                    w = x.rmParent.rmLeft;
                }

                if ((w.rmRight.color == RedBlackNodeForRemoveTask.rmBLACK) && (w.rmLeft.color == RedBlackNodeForRemoveTask.rmBLACK)) {
                    w.color = RedBlackNodeForRemoveTask.rmRED;
                    x = x.rmParent;
                }
                else {
                    if (w.rmLeft.color == RedBlackNodeForRemoveTask.rmBLACK) {
                        w.rmRight.color = RedBlackNodeForRemoveTask.rmBLACK;
                        w.color = RedBlackNodeForRemoveTask.rmRED;

                        rmLeftRotate(w);
                        w = x.rmParent.rmLeft;
                    }

                    w.color = x.rmParent.color;
                    x.rmParent.color = RedBlackNodeForRemoveTask.rmBLACK;
                    w.rmLeft.color = RedBlackNodeForRemoveTask.rmBLACK;

                    rmRightRotate(x.rmParent);
                    x = rmRoot;
                }
            }
        }

        x.color = RedBlackNodeForRemoveTask.rmBLACK;
    }

    private void rmLeftRotate(RedBlackNodeForRemoveTask x) {
        rmLeftRotateFixup(x);

        RedBlackNodeForRemoveTask y;

        y = x.rmRight;
        x.rmRight = y.rmLeft;

        if (y.rmLeft != nil) {
            y.rmLeft.rmParent = x;
        }

        y.rmParent = x.rmParent;

        if (x.rmParent == nil) {
            rmRoot = y;
        } else if (x.rmParent.rmLeft == x) {
            x.rmParent.rmLeft = y;
        } else {
            x.rmParent.rmRight = y;
        }

        y.rmLeft = x;
        x.rmParent = y;
    }

    private void rmLeftRotateFixup(RedBlackNodeForRemoveTask x){
        if ((x.rmLeft == nil) && (x.rmRight.rmLeft == nil)) {
            x.numLeft = 0;
            x.numRight = 0;

            x.rmRight.numLeft = 1;
        }
        else if (x.rmLeft == nil && (x.rmRight.rmLeft != nil)) {
            x.numLeft = 0;
            x.numRight = 1 + x.rmRight.rmLeft.numLeft +
                    x.rmRight.rmLeft.numRight;
            x.rmRight.numLeft = 2 + x.rmRight.rmLeft.numLeft +
                    x.rmRight.rmLeft.numRight;
        }
        else if ((x.rmLeft != nil) && (x.rmRight.rmLeft == nil)) {
            x.numRight = 0;
            x.rmRight.numLeft = 2 + x.rmLeft.numLeft + x.rmLeft.numRight;

        }
         {
            x.numRight = 1 + x.rmLeft.rmLeft.numLeft +
                    x.rmRight.rmLeft.numRight;
            x.rmRight.numLeft = 3 + x.rmLeft.numLeft + x.rmLeft.numRight +
                    x.rmLeft.rmLeft.numLeft + x.rmLeft.rmLeft.numRight;
        }
    }

    private void rmRightRotate(RedBlackNodeForRemoveTask y) {
        rmRightRotateFixup(y);

        RedBlackNodeForRemoveTask x = y.rmLeft;
        y.rmLeft = x.rmRight;

        if (x.rmRight != nil) {
            x.rmRight.rmParent = y;
        }

        x.rmParent = y.rmParent;

        if (y.rmParent == nil) {
            rmRoot = x;
        }
        else if (y.rmParent.rmRight == y) {
            y.rmParent.rmRight = x;
        }
        else {
            y.rmParent.rmLeft = x;
        }

        x.rmRight = y;
        y.rmParent = x;
    }

    private void rmRightRotateFixup(RedBlackNodeForRemoveTask y) {
        if ((y.rmRight == nil) && (y.rmLeft.rmRight == nil)) {
            y.numRight = 0;
            y.numLeft = 0;

            y.rmLeft.numRight = 1;
        }
        else if ((y.rmRight == nil) && (y.rmLeft.rmRight != nil)) {
            y.numRight = 0;
            y.numLeft = 1 + y.rmLeft.rmRight.numRight +
                    y.rmLeft.rmRight.numLeft;
            y.rmLeft.numRight = 2 + y.rmLeft.rmRight.numRight +
                    y.rmLeft.rmRight.numLeft;
        }
        else if ((y.rmRight != nil) && (y.rmLeft.rmRight == nil)) {
            y.numLeft = 0;
            y.rmLeft.numRight = 2 + y.rmRight.numRight +y.rmRight.numLeft;

        }
        else {
            y.numLeft = 1 + y.rmLeft.rmRight.numRight +
                    y.rmLeft.rmRight.numLeft;
            y.rmLeft.numRight = 3 + y.rmRight.numRight +
                    y.rmRight.numLeft +
                    y.rmLeft.rmRight.numRight + y.rmLeft.rmRight.numLeft;
        }
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

    private RedBlackNodeForRemoveTask findMin(RedBlackNodeForRemoveTask node) {
        if (node.rmLeft != null) {
            return findMin(node.rmLeft);
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
