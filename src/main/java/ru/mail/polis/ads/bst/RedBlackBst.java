package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

      private Node (Key key, Value value, boolean color) {
        this.key = key;
        this.value = value;
        this.color = color;
        this.height = 1;
      }
    }

    public RedBlackBst() {
      this.size = 0;
    }

    public RedBlackBst(Node root) {
      this.root = root;
      this.size = 1;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
      Node forReturn = get(root, key);
      return forReturn == null ? null : forReturn.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
      root = put(root, key, value);
      root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
      Node nodeForRemove = get(root, key);
      if (nodeForRemove == null) {
        return null;
      }
      Value valueForRemove = nodeForRemove.value;
      root = remove(root, key);
      --size;
      return valueForRemove;
    }

    @Nullable
    @Override
    public Key min() {
      Node forMin = min(root);
      return forMin == null ? null : forMin.key;
    }

    @Nullable
    @Override
    public Value minValue() {
      Node forMinValue = min(root);
      return forMinValue == null ? null : forMinValue.value;
    }

    @Nullable
    @Override
    public Key max() {
      Node forMax = max(root);
      return forMax == null ? null : forMax.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
      Node forMaxValue = max(root);
      return forMaxValue == null ? null : forMaxValue.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
      Node floorNode =  floor(root, key);
      return floorNode == null ? null : floorNode.key;
    }

    private Node floor(Node x, Key key) {
      if (x == null)
        return null;

      if (key.compareTo(x.key) == 0)
        return x;

      if (key.compareTo(x.key) < 0)
        return floor(x.left, key);

      Node floorNode = floor(x.right, key);
      return floorNode == null ? x : floorNode;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
      Node ceilNode = ceil(root, key);
      return ceilNode == null ? null : ceilNode.key;
    }

    private Node ceil(Node x, Key key) {
      if (x == null)
        return null;

      if (key.compareTo(x.key) == 0)
        return x;


      if (key.compareTo(x.key) > 0)
        return ceil(x.right, key);

      Node ceilNode = ceil(x.left, key);
      return ceilNode == null ? x : ceilNode;
    }

    @Override
    public int size() {
      return size;
    }

    @Override
    public int height() {
      return root != null ? root.height : 0;
    }

    private Node get(Node x, Key key) {
      if (x == null) {
        return null;
      }
      if (key.compareTo(x.key) > 0) {
        return get(x.right, key);
      } else if (key.compareTo(x.key) < 0) {
        return get(x.left, key);
      } else {
        return x;
      }
    }

    private Node min(Node x) {
      if (x == null) {
        return null;
      }
      while (x.left != null)
        x = x.left;
      return x;
    }

    private Node max(Node x) {
      if (x == null) {
        return null;
      }
      while (x.right != null)
        x = x.right;
      return x;
    }

    private Node rotateLeft(Node x) {
      Node right = x.right;
      x.right = right.left;
      right.left = x;
      right.color = x.color;
      x.color = RED;
      return right;
    }

    private Node rotateRight(Node x) {
      Node left = x.left;
      x.left = left.right;
      left.right = x;
      left.color = x.color;
      x.color = RED;
      return left;
    }

    private void flipColors(Node x) {
      x.color = !x.color;
      if (x.right != null)
        x.right.color = !x.right.color;
      if (x.left != null)
        x.left.color = !x.left.color;
    }

    private Node fixUp(Node x) {
      if (x == null)
        return null;
      if (isRed(x.right) && !isRed(x.left))
        x = rotateLeft(x);
      if (isRed(x.left) && isRed(x.left.left))
        x = rotateRight(x);
      if (isRed(x.left) && isRed(x.right))
        flipColors(x);
      x.height = Math.max(height(x.left), height(x.right)) + 1;
      return x;
    }

    private int height(Node x) {
      return x != null ? x.height : 0;
    }

    private Node put(Node x, Key key, Value value) {
      if (x == null) {
        ++size;
        return new Node(key, value, RED);
      }

      int compareResults = key.compareTo(x.key);
      if (compareResults > 0)
        x.right = put(x.right, key, value);
      else if (compareResults < 0)
        x.left = put(x.left, key, value);
      else
        x.value = value;

      x = fixUp(x);
      return x;
    }

    private boolean isRed(Node x) {
      return x != null && x.color == RED;
    }

  private Node moveRedLeft(Node x) {
    flipColors(x);
    if (x.right != null && isRed(x.right.left)) {
      x.right = rotateRight(x.right);
      x = rotateLeft(x);
      flipColors(x);
    }
    return x;
  }

  private Node deleteMin(Node x) {
    if (x.left == null)
      return null;
    if (!isRed(x.left) && !isRed(x.left.left))
      x = moveRedLeft(x);
    x.left = deleteMin(x.left);
    return fixUp(x);
  }

  public void deleteMin() {
    root = deleteMin(root);
    --size;
    if (root != null)
      root.color = BLACK;
  }

  private Node moveRedRight(Node x) {
    flipColors(x);
    if (x.left != null && isRed(x.left.left)) {
      x = rotateRight(x);
      flipColors(x);
    }
    return x;
  }

  private Node deleteMax(Node x) {
    if (isRed(x.left))
      x = rotateRight(x);
    if (x.right == null)
      return null;
    if (!isRed(x.right) && !isRed(x.right.left))
      x = moveRedRight(x);
    x.right = deleteMax(x.right);
    return fixUp(x);
  }

  public void deleteMax() {
    root = deleteMax(root);
    --size;
    if (root != null)
      root.color = BLACK;
  }

  private Node remove(Node x, Key key) {
    if (x == null)
      return null;
    int compareResult = key.compareTo(x.key);
    if (compareResult < 0) {
      if (x.left != null) {
        if (!isRed(x.left) && !isRed(x.left.left))
          x = moveRedLeft(x);
        x.left = remove(x.left, key);
      }
    } else {
      if (isRed(x.left)) {
        x = rotateRight(x);
        x.right = remove(x.right, key);
      } else if (compareResult == 0 && x.right == null)
        return null;
      else {
        if (x.right != null && !isRed(x.right) && !isRed(x.right.left))
          x = moveRedRight(x);
        if (key.compareTo(x.key) == 0) {
          Node min = min(x.right);
          x.key = min.key;
          x.value = min.value;
          x.right = deleteMin(x.right);
        } else
          x.right = remove(x.right, key);
      }
    }
    return fixUp(x);
  }
}
