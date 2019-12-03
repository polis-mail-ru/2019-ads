package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * class RedBlackBst
 * Implementation of the {@code Bst} interface. This class makes guarantees
 * as to the balance of the tree, and current node key more than left child
 * of the current node key, but less than right child of the current node key.
 *
 * <p>This implementation provides log(size) time performance for the basic
 * operations ({@code get} and {@code put}).
 *
 * @param <Key> the type of keys maintained by this binary search tree
 * @param <Value> the type of values
 *
 * @author  Maschenko Bogdan
 * @see Bst
 * @see Map
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
    implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    /**
     * Basic tree node, used for most entries.
     */
    class Node {

        @NotNull Key key;
        @NotNull Value value;
        @Nullable Node left;
        @Nullable Node right;
        int height;
        boolean color;

        Node(@NotNull Key key, @NotNull Value value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.color = RED;
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

    /* ------------------ Fields ------------------ */

    /**
     * The main node - root, initialized on first use {@code put} or transfer
     * in constructor.
     */
    @Nullable private Node root;

    /**
     * The number of key-value contained in this tree.
     */
    private int size;

    /* ------------------ Public operations ------------------ */

    /**
     * Constructs {@code RedBlackBst} with elements of {@code Map}.
     * @param elements the elements of {@code Map}.
     */
    public RedBlackBst(@NotNull Map<? extends Key, ? extends Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<? extends Key, ? extends Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    /**
     * Constructs {@code RedBlackBst} with one element - root.
     * @param root node, root of tree.
     */
    public RedBlackBst(@NotNull Node root) {
        this.root = root;
        this.size = 1;
    }

    /**
     * Constructs an empty {@code RedBlackBst}.
     */
    public RedBlackBst() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Return the value to which the specified key is mapped,
     * or {@code null} if this tree contains no mapping for the key.
     *
     * <p>More formally, if this tree contains a mapping from a key
     * {@code key} to a value {@code value}, then this method returns
     * {@code value}; otherwise it returns {@code null}. (There can be
     * at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the tree contains no mapping for the key. The
     * {@link #containsKey containsKey} operation may be used to distinguish
     * this case.
     *
     * @param key the key for searching a value
     * @return the value to which the specified key is mapped,
     *         or {@code null} if this tree contains no mapping for the key.
     */
    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return searchValue(key, root);
    }

    /**
     * Associates the specified value with the specified key in this tree.
     * If the tree previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(key, value, root);
        root.color = BLACK;
    }

    /**
     * Removes the mapping for the specified key from this tree if present.
     *
     * @param key key whose mapping is to be removed from the tree
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}
     */
    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Node removedNode = searchRemovedNode(root, key);
        if (removedNode == null) {
            return null;
        }
        Value removedValue = removedNode.value;

        root = delete(key, root);
        size--;
        if (root != null) {
            root.color = BLACK;
        }
        return removedValue;
    }

    /**
     * Search a minimum key {@code key} from tree.
     *
     * @return minimum {@code key} from tree,
     *         or {@code null} if tree is empty
     */
    @Nullable
    @Override
    public Key min() {
        return root != null ? searchMinNode(root).key : null;
    }

    /**
     * Search a minimum value {@code value} from tree.
     *
     * @return minimum {@code value} from tree,
     *         or {@code null} if tree is empty
     */
    @Nullable
    @Override
    public Value minValue() {
        return root != null ? searchMinNode(root).value : null;
    }

    /**
     * Search a maximum key {@code key} from tree.
     *
     * @return maximum {@code key} from tree,
     *         or {@code null} if tree is empty
     */
    @Nullable
    @Override
    public Key max() {
        return root != null ? searchMaxNode(root).key : null;
    }

    /**
     * Search a maximum value {@code value} from tree.
     *
     * @return maximum {@code value} from tree,
     *         or {@code null} if tree is empty
     */
    @Nullable
    @Override
    public Value maxValue() {
        return root != null ? searchMaxNode(root).value : null;
    }

    /**
     * Search a maximum key {@code k} from tree which equal or less than
     * given key {@code key}.
     *
     * @param key key which less than or equal to the necessary key
     * @return maximum {@code k} from tree
     *         which equal or less than given
     *         key {@code key}, or {@code null}
     *         if tree is empty
     */
    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return root!= null ? searchMaxKeyForFloor(key, root) : null;
    }

    /**
     * Search a minimum key {@code k} from tree which equal or more than
     * given key {@code key}.
     *
     * @param key key which more than or equal to the necessary key
     * @return minimum {@code k} from tree
     *         which equal or more than given
     *         key {@code key}, or {@code null}
     *         if tree is empty
     */
    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return root!= null ? searchMinKeyForCeil(key, root) : null;
    }

    /**
     * Returns the number of key-value mappings in this tree.
     *
     * @return the number of key-value mappings in this tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the number of maximum height of the node in this tree.
     *
     * @return the number of maximum height of the node in this tree
     */
    @Override
    public int height() {
        return height(root);
    }

    /**
     * Returns {@code true} if this tree contains a mapping for the
     * specified key.
     *
     * @param key The key whose presence in this tree is to be tested
     * @return {@code true} if this tree contains a mapping for the specified
     */
    @Override
    public boolean containsKey(@NotNull Key key) {
        return root != null && searchValue(key, root) != null;
    }

    /**
     * Returns {@code true} if this tree maps one or more keys to the
     * specified value.
     *
     * @param value value whose presence in this tree is to be tested
     * @return {@code true} if this tree maps one or more keys to the
     *         specified value
     */
    @Override
    public boolean containsValue(@NotNull Value value) {
        return root != null && searchValue(value, root);
    }

    /**
     * Removes all of the mappings from this tree.
     * The tree will be empty after this call returns.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* -------------- Package-private operations -------------- */

    /**
     * Method for check the correct operation of the tree construction
     *
     * @return {@code root}
     */
    Node getRoot() {
        return root;
    }

    /* ------------------ Private operations ------------------ */

    /**
     * Returns {@code node} with min {@code key} and {@code value}.
     * Uses in methods {@code min} and {@code minValue}
     *
     * @param node current node
     * @return {@code node} with min {@code key} and {@code value}
     */
    private Node searchMinNode(Node node) {
        if (node.left != null) {
            return searchMinNode(node.left);
        }
        return node;
    }

    /**
     * Returns {@code node} with max {@code key} and {@code value}.
     * Uses in methods {@code max} and {@code maxValue}
     *
     * @param node current node for recursive method
     * @return {@code node} with max {@code key} and {@code value}
     */
    private Node searchMaxNode(Node node) {
        if (node.right != null) {
            return searchMaxNode(node.right);
        }
        return node;
    }

    /**
     * Method for {@code ceil} with main implementation
     * Search a minimum key {@code k} from tree which equal or more than
     * given key {@code key}.
     *
     * @param key  key {@code key} which more than
     *             or equal to the necessary key
     * @param node current node {@code node}
     * @return obtained key
     */
    private Key searchMinKeyForCeil(Key key, Node node) {
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

    /**
     * Method for {@code floor} with main implementation
     * Search a maximum key {@code k} from tree which equal or less than
     * given key {@code key}.
     *
     * @param key  key {@code key} which less than
     *             or equal to the necessary key
     * @param node current node {@code node}
     * @return obtained key
     */
    private Key searchMaxKeyForFloor(Key key, Node node) {
        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                return searchMaxKeyForFloor(key, node.left);
            } else {
                return null;
            }
        } else {
            if (node.right != null) {
                if (key.compareTo(node.right.key) < 0) {
                    if (node.right.left != null) {
                        return searchMaxKeyForFloor(key, node.right.left);
                    } else {
                        return node.key;
                    }
                } else {
                    return searchMaxKeyForFloor(key, node.right);
                }
            } else {
                return node.key;
            }
        }
    }

    /**
     * Method for {@code height} with main implementation
     * Returns the number of maximum height of the node in this tree.
     *
     * @param node node from which we are looking a height
     * @return number of height of the necessary node
     */
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Method for {@code containsKey} with main implementation
     * Returns {@code true} if this tree contains a mapping for the
     * specified key.
     *
     * @param key The key whose presence in this tree is to be tested
     * @param currentNode current node for recursive method
     * @return value if this tree contains a mapping for the specified
     *         or {@code null} if currentNode == null
     */
    private Value searchValue(Key key, Node currentNode) {
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

    /**
     * Method for {@code containsValue} with main implementation
     * Returns {@code true} if this tree maps one or more keys to the
     * specified value.
     *
     * @param value The value whose presence in this tree is to be tested
     * @param currentNode current node for recursive method
     * @return {@code true} if this tree maps one or more keys to the
     *         specified value
     */
    private boolean searchValue(Value value, Node currentNode) {
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

    /**
     * Rotate on right node
     *
     * @param node node which necessary rotate
     * @return rotated node
     */
    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        fixHeight(node);
        fixHeight(leftNode);
        return leftNode;
    }

    /**
     * Rotate on left node
     *
     * @param node node which necessary rotate
     * @return rotated node
     */
    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        fixHeight(node);
        fixHeight(rightNode);
        rightNode.color = node.color;
        node.color = RED;
        return rightNode;
    }

    /**
     * Swap the color of the node and the color of its left and
     * right node
     *
     * @param node node which necessary rotate
     * @return node with another color
     */
    private Node flipColors(Node node) {
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
        node.color = !node.color;
        return node;
    }

    /**
     * Fix height after call {@code put} or {@code remove}, or after balancing
     *
     * @param node node from which we are fix a height
     */
    private void fixHeight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * method for {@code put} with main implementation
     *
     * @param key added key
     * @param value added value
     * @param node checking node
     * @return node with added new node with {@code key} and {@code value}
     */
    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int result = key.compareTo(node.key);
        if (result < 0) {
            node.left = put(key, value, node.left);
        } else if (result > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    /**
     *  Fixing color and balance for the specific node.
     *  There are 3 case:
     * + + + + + + + + + + + + + + + + + + + + + + + + + + + + +
     *        before fixing       +        after fixing        +
     *    1) root ---> ...        +    root ---> ...           +
     *                  |         +               |            +
     *                BLACK       +             BLACK          +
     *               /    \       +            /    \          +
     *          BLACK     RED     +         RED    ...         +
     *                            +        /                   +
     *                            +    BLACK                   +
     * + + + + + + + + + + + + + + + + + + + + + + + + + + + + +
     *   2) root ---> ...         +  root ---> ...             +
     *                 |          +             |              +
     *              BLACK         +           BLACK            +
     *             /    \         +           /   \            +
     *          RED     ...       +        RED    RED          +
     *         /                  +                            +
     *      RED                   +                            +
     * + + + + + + + + + + + + + + + + + + + + + + + + + + + + +
     *    3) root ---> ...        +   root ---> ...            +
     *                  |         +              |             +
     *                BLACK       +             RED            +
     *               /     \      +            /   \           +
     *            RED      RED    +       BLACK    BLACK       +
     * + + + + + + + + + + + + + + + + + + + + + + + + + + + + +
     * @param node node from which we are checking color and change balance
     *             if it's necessary
     * @return fixed node
     */
    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    /**
     * Checking node color is red or not
     *
     * @param node node from which we are checking color
     * @return {@code true} if color is red or {@code false} if color is
     *         {@code null} or black
     */
    private boolean isRed(Node node) {
        return node != null && node.color;
    }

    /**
     * Maintains 3-node state when moving to the left
     *
     * @param node current node {@code node}
     * @return 3-node
     */
    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * Method for {@code delete} which searching min node for replace
     *
     * @param node current node for recursive method
     * @return node from tree
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    /**
     * Maintains 3-node state when moving to the right
     *
     * @param node current node {@code node}
     * @return 3-node
     */
    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    /**
     * Method for {@code remove} with main implementation
     *
     * @param key key whose mapping is to be removed from the tree
     * @param node current node at the tree
     * @return treeWithoutValue which contain node and removed value
     */
    private Node delete(Key key, Node node) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = delete(key, node.left);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = delete(key, node.right);
            } else {
                if (node.right == null && cmp == 0) {
                    return null;
                }
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                cmp = key.compareTo(node.key);
                if (cmp == 0) {
                    Node min = searchMinNode(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = delete(key, node.right);
                }
            }
        }
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    /**
     * Method search min node which need remove for return removed value
     * @param node current node at the tree
     * @param key key of node which need remove
     * @return path to removed node without remove
     */
    private Node searchRemovedNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            return searchRemovedNode(node.right, key);
        } else if (cmp < 0) {
            return searchRemovedNode(node.left, key);
        } else {
            return node;
        }
    }
}
