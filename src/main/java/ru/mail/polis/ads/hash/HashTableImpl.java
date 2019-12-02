package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private class Node<Key, Value> {
        Key key;
        Value value;
        int hash;

        Node next;

        public Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    private final static float LOAD_FACTOR = 0.75f;
    private final static int[] CAPACITY_LIST = {17, 31, 61, 127, 251, 509, 1021, 2039};
    private Node<Key, Value>[] nodes = new Node[CAPACITY_LIST[0]]; // При создании размер будет 17
    private int currentCapacity = CAPACITY_LIST[0];
    private int indexInCapacityList = 0; // Текущий индекс в CAPACITY_LIST
    private int amountOfElements = 0; // Количество элементов
    private int amountOfKeys = 0; // Количество цепочек

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = key.hashCode();
        Node<Key, Value> current = nodes[hash % currentCapacity];

        if (current == null) {
            return null; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        while (current != null) {
            if (current.hash == hash && key.equals(current.key)) {
                return current.value;
            }

            current = current.next; // Если значение ещё не нашлось, идём к следующем элементу
        }

        return null; // Если ничего не нашли, вернём null
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hash = key.hashCode();
        Node<Key, Value> current = nodes[hash % currentCapacity];

        if (current == null) {
            return false; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        while (current != null) {
            if (current.hash == hash && key.equals(current.key)) {
                return true; // Если нашли, то возвращаем true
            }

            current = current.next;
        }

        return false; // Если до этой строки дошло, значит ничего не нашлось, вернём false
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node(key, value, key.hashCode()));
    }

    private void put(Node<Key, Value> node) {
        // Проверяем загруженность, если надо - увеличиваем массив
        if (amountOfElements / currentCapacity > LOAD_FACTOR) {
            resize();
        }

        int index = node.hash % currentCapacity;
        Node<Key, Value> current = nodes[index];
        Node<Key, Value> prev = null;

        if (current == null) {
            nodes[index] = node;
            amountOfKeys++;
            amountOfElements++;
            return;
        }

        while (current != null) {
            if (current.hash == node.hash && current.key.equals(node.key)) {
                current.value = node.value;
                return;
            }

            prev = current;
            current = current.next;
        }

        prev.next = node;
        amountOfElements++;
    }

    // Метод, увеличивающий размер массива nodes
    private void resize() {
        Node<Key, Value>[] temp = nodes; // Сохраняем значения, чтобы можно было их перенести в новый увеличенный nodes
        nodes = new Node[CAPACITY_LIST[++indexInCapacityList]]; // Делаем новый массив больше старого (увеличиваем размер nodes);
        amountOfKeys = 0;

        // Перекладываем элементы в новый увеличенный массив
        for (int i = 0; i < temp.length; i++) {
            Node<Key, Value> current = temp[i];
            while (current != null) {
                put(current);
                current = current.next;
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = key.hashCode();
        int index = hash % currentCapacity;
        Node<Key, Value> current = nodes[index];
        // Будем хранить ссылку на предыдущий элемент, поскольку при удалении элемента n нужно элементу n-1 присвоить ссылку на n+1
        Node<Key, Value> prev = null;

        if (current == null) {
            return null; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        while (current != null) {
            if (current.hash == hash && key.equals(current.key)) {
                if (prev == null) {
                    nodes[index] = current.next;
                    amountOfElements--;

                    if (nodes[index] == null) {
                        amountOfKeys--;
                    }
                } else {
                    prev.next = current.next; // элементу n-1 присвоили ссылку на n+1 (элемент n удалили)
                }

                return current.value;
            }

            // Если элемент ещё не нашёлся, продолжим двигаться по списку
            prev = current;
            current = current.next;
        }

        return null; // Если ничего не нашли, вернём null
    }

    @Override
    public int size() {
        return amountOfElements;
    }

    @Override
    public boolean isEmpty() {
        if (amountOfKeys == 0) {
            return true;
        }

        return false;
    }
}
