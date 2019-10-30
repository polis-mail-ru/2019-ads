package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.polis.ads.exception.IllegalHeightException;

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

        public Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }

        public Node(@NotNull Key key, @NotNull Value value, int height) {
            this.key = key;
            this.value = value;
            if (height < 1) {
                throw new IllegalHeightException("Incorrect height");
            }
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

    @Nullable private Node root;
    @Nullable private Value deletedValue;
    private int size;

    public AvlBst(@Nullable Node root) {
        this.root = root;
        this.size = 1;
        this.deletedValue = null;
    }

    public AvlBst() {
        this.root = null;
        this.size = 0;
        this.deletedValue = null;
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
        deletedValue = null;
        root = searchElementWhichNeedRemove(key, root);
        if (deletedValue != null) {
            size--;
        }
        return deletedValue;
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
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public @Nullable Key ceil(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }

    @Override
    public boolean contains(@NotNull Key key) {
        if (root == null) {
            return false;
        }
        return searchValue(key, root) != null;
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

    private @Nullable Node searchElementWhichNeedRemove(@NotNull Key key, @Nullable Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = searchElementWhichNeedRemove(key, node.right);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = searchElementWhichNeedRemove(key, node.left);
        }
        if (key.compareTo(node.key) == 0){
            node = innerRemove(node);
        }
        return node;
    }

    private @Nullable Node innerRemove(@NotNull Node node) {
        if (node.left == null) {
            deletedValue = node.value;
            return node.right;
        }
        if (node.right == null) {
            deletedValue = node.value;
            return node.left;
        }
        Node tempNode = node;
        node = searchMinNode(tempNode.right);
        node.right = deleteMin(tempNode.right);
        node.left = tempNode.left;
        deletedValue = tempNode.value;
        return node;
    }

    private @NotNull Node searchPlaceForPut(@NotNull Key key, @NotNull Value value, @Nullable Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = searchPlaceForPut(key, value, node.right);
            if (node.left == null) {
                node.height = node.right.height + 1;
            } else {
                node.height = Math.max(node.left.height, node.right.height) + 1;
            }
        } else if (key.compareTo(node.key) < 0) {
            node.left = searchPlaceForPut(key, value, node.left);
            if (node.right == null) {
                node.height = node.left.height + 1;
            } else {
                node.height = Math.max(node.left.height, node.right.height) + 1;
            }
        } else {
            node.value = value;
        }
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

    private @NotNull Node searchMinNode(@NotNull Node node) {
        if (node.left != null) {
            searchMinNode(node.left);
        }
        return node;
    }

    private @NotNull Node searchMaxNode(@NotNull Node node) {
        if (node.right != null) {
            searchMaxNode(node.right);
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
}
