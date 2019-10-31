package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
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
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    /**
     * Basic tree node, used for most entries.
     */
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

    /**
     * Class for {@code remove}. Contains current node and removed value
     */
    private class Pair {
        @Nullable Node node;
        @Nullable Value value;

        Pair(@Nullable Node node, @Nullable Value value) {
            this.node = node;
            this.value = value;
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
     * Constructs {@code AvlBst} with elements of {@code Map}.
     * @param elements the elements of {@code Map}.
     */
    public AvlBst(@NotNull Map<? extends Key, ? extends Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<? extends Key, ? extends Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    /**
     * Constructs {@code AvlBst} with one element - root.
     * @param root node, root of tree.
     */
    public AvlBst(@NotNull Node root) {
        this.root = root;
        this.size = 1;
    }

    /**
     * Constructs an empty {@code AvlBst}.
     */
    public AvlBst() {
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
    @Override
    public @Nullable Value get(@NotNull Key key) {
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
        root = searchPlaceForPut(key, value, root);
    }

    /**
     * Removes the mapping for the specified key from this tree if present.
     * @param key key whose mapping is to be removed from the tree
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}
     */
    @Override
    public @Nullable Value remove(@NotNull Key key) {
        Pair pair = searchElementWhichNeedRemove(key, new Pair(root, null));
        root = pair.node;
        if (pair.value != null) {
            size--;
        }
        return pair.value;
    }

    /**
     * Search a minimum key {@code key} from tree.
     *
     * @return minimum {@code key} from tree,
     *         or {@code null} if tree is empty
     */
    @Override
    public @Nullable Key min() {
        if (root == null) {
            return null;
        }
        Node node = searchMinNode(root);
        return node.key;
    }

    /**
     * Search a minimum value {@code value} from tree.
     *
     * @return minimum {@code value} from tree,
     *         or {@code null} if tree is empty
     */
    @Override
    public @Nullable Value minValue() {
        if (root == null) {
            return null;
        }
        Node node = searchMinNode(root);
        return node.value;
    }

    /**
     * Search a maximum key {@code key} from tree.
     *
     * @return maximum {@code key} from tree,
     *         or {@code null} if tree is empty
     */
    @Override
    public @Nullable Key max() {
        if (root == null) {
            return null;
        }
        Node node = searchMaxNode(root);
        return node.key;
    }

    /**
     * Search a maximum value {@code value} from tree.
     *
     * @return maximum {@code value} from tree,
     *         or {@code null} if tree is empty
     */
    @Override
    public @Nullable Value maxValue() {
        if (root == null) {
            return null;
        }
        Node node = searchMaxNode(root);
        return node.value;
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
    @Override
    public @Nullable Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        return searchMaxKeyForFloor(key, root);
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
    @Override
    public @Nullable Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        return searchMinKeyForCeil(key, root);
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
        if (root == null) {
            return false;
        }
        return searchValue(key, root) != null;
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
        if (root == null) {
            return false;
        }
        return searchValue(value, root);
    }

    /**
     * Returns {@code true} if this tree contains no key-value mappings.
     *
     * @return {@code true} if this tree contains no key-value mappings
     */
    @Override
    public boolean empty() {
        return size == 0;
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

    /* ------------------ Private operations ------------------ */

    /**
     * Auxiliary recursive method for {@code ceil} with main implementation
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
     * Auxiliary recursive method for {@code floor} with main implementation
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
     * Auxiliary recursive method for {@code remove} with main implementation
     *
     * @param key key which necessary remove
     * @param pair pair of the current node and removed value
     * @return pair which contain node and removed value
     */
    private Pair searchElementWhichNeedRemove(Key key, Pair pair) {
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

    /**
     * Auxiliary method for {@code searchElementWhichNeedRemove} with
     * main implementation of the replace removed node and some node of tree
     *
     * @param pair pair of the current node and removed value
     */
    private void innerRemove(Pair pair) {
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

    /**
     * Auxiliary recursive method for {@code put} with main implementation
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @param node = current node
     * @return node from tree for the link whit other nodes
     */
    private Node searchPlaceForPut(Key key, Value value, Node node) {
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

    /**
     * Auxiliary recursive method for {@code containsKey} with main implementation
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
     * Auxiliary recursive method for {@code containsValue} with main implementation

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
     * Auxiliary recursive method for {@code min} and {@code minValue}
     * with main implementation
     *
     * @param node current node for recursive method
     * @return node from tree
     */
    private Node searchMinNode(Node node) {
        if (node.left != null) {
            return searchMinNode(node.left);
        }
        return node;
    }

    /**
     * Auxiliary recursive method for {@code max} and {@code maxValue}
     * with main implementation
     *
     * @param node current node for recursive method
     * @return node from tree
     */
    private Node searchMaxNode(Node node) {
        if (node.right != null) {
            return searchMaxNode(node.right);
        }
        return node;
    }

    /**
     * Auxiliary recursive method for {@code innerRemove} with main implementation
     *
     * @param node current node for recursive method
     * @return node from tree
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     *  Auxiliary recursive method for {@code height} with main implementation
     *
     * @param node node from which we are looking a height
     * @return number of height of the necessary node
     */
    private int height(Node node) {
        return node == null ? 0 : node.height;
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
     * Rotate on right node if factor {@code factor} returned
     * unacceptable value
     *
     * @param node node which necessary rotate
     * @return rotated node
     */
    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        fixHeight(node);
        fixHeight(leftNode);
        return leftNode;
    }

    /**
     * Rotate on left node if factor {@code factor} returned
     * unacceptable value
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
        return rightNode;
    }

    /**
     * Calculate a power of the balancing
     *
     * @param node node from which calculate a power of the balancing
     * @return number of the balancing. Acceptable values = [-1, 0, 1],
     *         unacceptable values = [-2, 2]
     */
    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    /**
     * Node balancing if factor {@code factor} returned unacceptable value
     *
     * @param node node from which check balancing
     * @return balanced node
     */
    private Node balance(Node node) {
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