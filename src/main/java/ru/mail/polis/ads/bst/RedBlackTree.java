package ru.mail.polis.ads.bst;

public class RedBlackTree <Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
  class Node {
    Key key;
    Value value;
    Node left, right;
    boolean color;

    private Node (Key key, Value value, boolean color) {
      this.key = key;
      this.value = value;
      this.color = color;
    }
  }

  public RedBlackTree() {
    this.size = 0;
  }

  public RedBlackTree(Node root) {
    this.root = root;
    this.size = 1;
  }

  @Override
  public Value get(Key key) {
    Node forReturn = get(root, key);
    return forReturn == null ? null : forReturn.value;
  }

  @Override
  public void put(Key key, Value value) {
    root = put(root, key, value);
    root.color = BLACK;
  }

  @Override
  public void remove(Key key) {

  }

  @Override
  public Key min() {
    Node forMin = min(root);
    return forMin == null ? null : forMin.key;
  }

  @Override
  public Value minValue() {
    Node forMinValue = min(root);
    return forMinValue == null ? null : forMinValue.value;
  }

  @Override
  public Key max() {
    Node forMax = max(root);
    return forMax == null ? null : forMax.key;
  }

  @Override
  public Value maxValue() {
    Node forMaxValue = max(root);
    return forMaxValue == null ? null : forMaxValue.value;
  }

  @Override
  public Key floor(Key key) {
    Node nodeWithKey = get(root, key);
    if (nodeWithKey == null || nodeWithKey.left == null) {
      return null;
    }
    Node nodeWithMaxKey = nodeWithKey.left;
    while (nodeWithMaxKey.right != null)
      nodeWithMaxKey = nodeWithMaxKey.right;
    return nodeWithMaxKey.key;
  }

  @Override
  public Key ceil(Key key) {
    Node nodeWithKey = get(root, key);
    if (nodeWithKey == null || nodeWithKey.right == null) {
      return null;
    }
    Node nodeWithMinKey = nodeWithKey.right;
    while (nodeWithMinKey.left != null)
      nodeWithMinKey = nodeWithMinKey.left;
    return nodeWithMinKey.key;
  }

  @Override
  public int size() {
    return size;
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

  private Node flipColors(Node x) {
    x.color = !x.color;
    x.right.color = !x.right.color;
    x.left.color = !x.left.color;
    return x;
  }

  private Node fixUp(Node x) {
    if (isRed(x.right) && !isRed(x.left))
      x = rotateLeft(x);
    if (isRed(x.left) && isRed(x.left.left))
      x = rotateRight(x);
    if (isRed(x.left) && isRed(x.right))
      x = flipColors(x);
    return x;
  }

  private Node put(Node x, Key key, Value value) {
    if (x == null) {
      ++size;
      return new Node(key, value, RED);
    }
    if (key.compareTo(x.key) > 0)
      x.right = put(x.right, key, value);
    else if (key.compareTo(x.key) < 0)
      x.left = put(x.left, key, value);
    else
      x.value = value;
    x = fixUp(x);
    return x;
  }

  private boolean isRed(Node x) {
    return x != null && x.color == RED;
  }

  static final boolean RED = true;
  static final boolean BLACK = false;

  private Node root;
  private int size;
}
