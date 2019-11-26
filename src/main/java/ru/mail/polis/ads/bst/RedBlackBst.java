package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value> implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node node;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        Node(Key key, Value value, boolean color, int height) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.height = height;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(node, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        node = put(node, key, value);
        node.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value remove = get(node, key);
        node = remove(node, key);
        return remove;
    }

    @Nullable
    @Override
    public Key min() {
        return min(node);
    }

    @Nullable
    @Override
    public Value minValue() {
        return get(min());
    }

    @Nullable
    @Override
    public Key max() {
        return max(node);
    }

    @Nullable
    @Override
    public Value maxValue() {
        return get(max());
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(node, key, null);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(node, key, null);
    }

    @Override
    public int size() {
        return size(node);
    }

    @Override
    public int height() {
        return height(node);
    }

    private Value get(Node x, Key key) {
        if (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) {
                return get(x.left, key);
            } else if (compare > 0) {
                return get(x.right, key);
            } else {
                return x.value;
            }
        }
        return null;
    }

    private Node put(Node x, Key key, Value value) {
        if (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) {
                x.left = put(x.left, key, value);
            } else if (compare > 0) {
                x.right = put(x.right, key, value);
            } else {
                x.value = value;
            }
            setNewHeight(x);
            return fixedNode(x);
        }
        return new Node(key, value, RED, 1);
    }

    private Node moveRedLeft(Node x) {
        invertColor(x);
        if (x.right != null && isRed(x.right.left)) {
            x.right = rotateNodeRight(x.right);
            x = rotateNodeLeft(x);
            invertColor(x);
        }
        return x;
    }

    private Node deleteMinNode(Node x) {
        if (x.left != null) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = deleteMinNode(x.left);
            return fixedNode(x);
        }
        return null;
    }

    private Node moveRedRight(Node x) {
        invertColor(x);
        if (x.left != null && isRed(x.left.left)) {
            x = rotateNodeRight(x);
            invertColor(x);
        }
        return x;
    }

    private Node remove(Node x, Key key) {
        if (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) {
                if (x.left != null) {
                    if (!isRed(x.left) && !isRed(x.left.left)) {
                        x = moveRedLeft(x);
                    }
                    x.left = remove(x.left, key);
                }
            } else if (compare > 0) {
                if (x.right != null) {
                    if (isRed(x.left)) {
                        x = rotateNodeRight(x);
                    }
                    if (!isRed(x.right) && !isRed(x.right.left)) {
                        x = moveRedRight(x);
                    }
                    x.right = remove(x.right, key);
                }
            } else {
                Node remove = x;
                if (isRed(x.left)) {
                    x = rotateNodeRight(x);
                    remove = x.right;
                }
                if (remove.right == null) {
                    if (remove.left == null) {
                        return null;
                    }
                    return remove.left;
                }
                remove.key = min(remove.right);
                remove.value = get(remove.right, remove.key);
                remove.right = deleteMinNode(remove.right);
            }
            return fixedNode(x);
        }
        return null;
    }

    private Key min(Node x) {
        if (x != null) {
            if (x.left == null) {
                return x.key;
            }
            return min(x.left);
        }
        return null;
    }

    private Key max(@NotNull Node x) {
        if (x != null) {
            if (x.right != null) {
                return max(x.right);
            }
            return x.key;
        }
        return null;
    }

    private Key floor(Node x, Key key, Key max) {
        if (x != null) {
            int compare = key.compareTo(x.key);
            if (compare < 0) {
                max = floor(x.left, key, max);
            } else if (compare > 0) {
                if (max == null || max.compareTo(x.key) < 0) {
                    max = x.key;
                }
                max = floor(x.right, key, max);
            } else {
                max = x.key;
            }
        }
        return max;
    }

    private Key ceil(Node x, Key key, Key min) {
        if (x != null) {
            int compare = key.compareTo(x.key);
            if (compare > 0) {
                min = ceil(x.right, key, min);
            } else if (compare < 0) {
                if (min == null || min.compareTo(x.key) > 0) {
                    min = x.key;
                }
                min = ceil(x.left, key, min);
            } else {
                min = x.key;
            }
        }
        return min;
    }

    private int size(Node x) {
        return x != null ? size(x.left) + size(x.right) + 1 : 0;
    }

    private int height(Node x) {
        return x != null ? x.height : 0;
    }

    //-------------------------------------------
    private Node fixedNode(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateNodeLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateNodeRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            invertColor(x);
        }
        return x;
    }

    private void setNewHeight(Node x) {
        x.height = Math.max(height(x.left), height(x.right)) + 1;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateNodeRight(Node x) {
        Node left = x.left;
        x.left = left.right;
        left.right = x;
        left.color = x.color;
        x.color = RED;
        return left;
    }

    private Node rotateNodeLeft(Node x) {
        Node right = x.right;
        x.right = right.left;
        right.left = x;
        right.color = x.color;
        x.color = RED;
        return right;
    }

    private Node invertColor(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }
}
