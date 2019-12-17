import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private static final int CAPACITY = 16;
    private static final double COEFFICIENT = 0.75;
    private Node[] nodes;
    private int size;
    private int capacity = CAPACITY;
    private int usedValue;

    public HashTableImpl() {
        this.nodes = new Node[CAPACITY];
    }

    private class Node<Key, Value> {
        final Key key;
        final int hash;
        Value value;
        Node next;

        Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            } else if (o != null && o.getClass() == Node.class) {
                return Objects.equals(this.key, ((Node) o).key) &&
                        Objects.equals(this.value, ((Node) o).value);
            }
            return false;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = hashCode(key);
        int i = hash % nodes.length;
        Node node = nodes[i];
        while (node != null) {
            if (node.hash == hash && key.equals(node.key)) {
                return (Value) node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node(key, value, hashCode(key)));
        resize();
    }

    private void put(Node x) {
        int i = x.hash % nodes.length;
        Node node = nodes[i];
        if (node == null) {
            nodes[i] = x;
            usedValue++;
            size++;
        } else {
            Node last = node;
            do {
                if (x.key.equals(node.key)) {
                    node.value = x.value;
                    return;
                }
                last = node;
                node = node.next;
            } while (node != null);
            last.next = x;
            size++;
        }
    }

    private void resize() {
        if ((capacity * COEFFICIENT) < usedValue) {
            capacity = capacity * 2;
            usedValue = 0;
            Node[] oldTable = nodes;
            nodes = new Node[capacity];
            for (Node node : oldTable) {
                while (node != null) {
                    Node next = node.next;
                    put(node);
                    node = next;
                }
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = hashCode(key);
        int i = hash % nodes.length;
        Node node = nodes[i];
        Node previous = null;

        while (node != null) {
            if (key.equals(node.key)) {
                size--;
                if (previous == null) {
                    nodes[i] = node.next;
                    if (nodes[i] == null) {
                        usedValue--;
                    }
                    return (Value) node.value;
                } else {
                    previous.next = node.next;
                    return (Value) node.value;
                }
            }
            previous = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int hashCode(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ h >>> 16;
    }
}
