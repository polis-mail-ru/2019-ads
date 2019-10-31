package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    
    private class Node {
        @NotNull Key key;
        @NotNull Value value;
        @Nullable Node left;
        @Nullable Node right;
        int height;

        Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        Node(@NotNull Key key, @NotNull Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return key.equals(node.key)
                && value.equals(node.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private class Pair {
        @Nullable Node node;
        @Nullable Value value;

        Pair(@Nullable Node node, @Nullable Value value) {
            this.node = node;
            this.value = value;
        }
    }

    @Nullable private Node root;
    @Nullable private Value deletedValue;
    private int size;

    public AvlBst(@NotNull Map<Key, Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<Key, Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    public AvlBst(@NotNull Node root) {
        this.root = root;
        this.size = 1;
    }

    public AvlBst() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        return searchValue(key, root);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = searchPlaceForPut(key, value, root);
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        Pair pair = searchElementWhichNeedRemove(key, new Pair(root, null));
        root = pair.node;
        if (pair.value != null) {
            size--;
        }
        return pair.value;
    }

    @Override
    public @Nullable Key min() {
        if (root == null) {
            return null;
        }
        Node node = searchMinNode(root);
        return node.key;
    }

    @Override
    public @Nullable Value minValue() {
        if (root == null) {
            return null;
        }
        Node node = searchMinNode(root);
        return node.value;
    }

    @Override
    public @Nullable Key max() {
        if (root == null) {
            return null;
        }
        Node node = searchMaxNode(root);
        return node.key;
    }

    @Override
    public @Nullable Value maxValue() {
        if (root == null) {
            return null;
        }
        Node node = searchMaxNode(root);
        return node.value;
    }

    @Override
    public @Nullable Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        return searchMinKeyForFloor(key, root);
    }

    @Override
    public @Nullable Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        return searchMinKeyForCeil(key, root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        if (root == null) {
            return false;
        }
        return searchValue(key, root) != null;
    }

    @Override
    public boolean containsValue(@NotNull Value value) {
        if (root == null) {
            return false;
        }
        return searchValue(value, root);
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private @Nullable Key searchMinKeyForCeil(@NotNull Key key, @NotNull Node node) {
        if (key.compareTo(node.key) > 0) {
            if (node.right != null) {
                return searchMinKeyForCeil(key, node.right);
            } else {
                return null;
            }
        } else {
            if (node.left != null) {
                if (key.compareTo(node.left.key) > 0) {
                    if (node.left.right != null) {
                        return searchMinKeyForCeil(key, node.left.right);
                    } else {
                        return node.key;
                    }
                } else {
                    return searchMinKeyForCeil(key, node.left);
                }
            } else {
                return node.key;
            }
        }
    }

    private @Nullable Key searchMinKeyForFloor(@NotNull Key key, @NotNull Node node) {
        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                return searchMinKeyForFloor(key, node.left);
            } else {
                return null;
            }
        } else {
            if (node.right != null) {
                if (key.compareTo(node.right.key) < 0) {
                    if (node.right.left != null) {
                        return searchMinKeyForFloor(key, node.right.left);
                    } else {
                        return node.key;
                    }
                } else {
                    return searchMinKeyForFloor(key, node.right);
                }
            } else {
                return node.key;
            }
        }
    }

    private @NotNull Pair searchElementWhichNeedRemove(@NotNull Key key, @NotNull Pair pair) {
        if (pair.node == null) {
            return pair;
        }
        if (key.compareTo(pair.node.key) > 0) {
            Pair tempPair = new Pair(pair.node.right, pair.value);
            searchElementWhichNeedRemove(key, tempPair);
            pair.node.right = tempPair.node;
            pair.value = tempPair.value;
        }
        if (key.compareTo(pair.node.key) < 0) {
            Pair tempPair = new Pair(pair.node.left, pair.value);
            searchElementWhichNeedRemove(key, tempPair);
            pair.node.left = tempPair.node;
            pair.value = tempPair.value;
        }
        if (key.compareTo(pair.node.key) == 0){
            innerRemove(pair);
        }
        if (pair.node != null) {
            fixHeight(pair.node);
            pair.node = balance(pair.node);
        }
        return pair;
    }

    private void innerRemove(@NotNull Pair pair) {
        if (pair.node.left == null) {
            pair.value = pair.node.value;
            pair.node = pair.node.right;
            return;
        }
        if (pair.node.right == null) {
            pair.value = pair.node.value;
            pair.node = pair.node.left;
            return;
        }
        Node tempNode = pair.node;
        pair.node = searchMinNode(tempNode.right);
        pair.node.right = deleteMin(tempNode.right);
        pair.node.left = tempNode.left;
        pair.value = tempNode.value;
    }

    private @NotNull Node searchPlaceForPut(@NotNull Key key, @NotNull Value value, @Nullable Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = searchPlaceForPut(key, value, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = searchPlaceForPut(key, value, node.left);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private @Nullable Value searchValue(@NotNull Key key, @Nullable Node currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (key.compareTo(currentNode.key) > 0) {
            return searchValue(key, currentNode.right);
        } else if (key.compareTo(currentNode.key) < 0) {
            return searchValue(key, currentNode.left);
        }
        return currentNode.value;
    }

    private boolean searchValue(@NotNull Value value, @NotNull Node currentNode) {
        if (value.equals(currentNode.value)) {
            return true;
        }
        if (currentNode.left != null) {
            return searchValue(value, currentNode.left);
        }
        if (currentNode.right != null) {
            return searchValue(value, currentNode.right);
        }
        return false;
    }

    private @NotNull Node searchMinNode(@NotNull Node node) {
        if (node.left != null) {
            return searchMinNode(node.left);
        }
        return node;
    }

    private @NotNull Node searchMaxNode(@NotNull Node node) {
        if (node.right != null) {
            return searchMaxNode(node.right);
        }
        return node;
    }

    private @Nullable Node deleteMin(@NotNull Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private int height(@Nullable Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(@NotNull Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private @NotNull Node rotateRight(@NotNull Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        fixHeight(node);
        fixHeight(leftNode);
        return leftNode;
    }

    private @NotNull Node rotateLeft(@NotNull Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        fixHeight(node);
        fixHeight(rightNode);
        return rightNode;
    }

    private int factor(@NotNull Node node) {
        return height(node.left) - height(node.right);
    }

    private @NotNull Node balance(@NotNull Node node) {
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
