package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<Key, Value> implements HashTable<Key, Value> {

    private class Node {
        Key key;
        Value value;
        int hash;

        public Node(Key key, Value value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    private final static float LOAD_FACTOR = 0.75f;
    private final static int[] CAPACITY_LIST = {17, 31, 61, 127, 251, 509, 1021, 2039};
    List<Node>[] nodes = new LinkedList[CAPACITY_LIST[0]]; // При создании размер будет 17
    private int currentCapacity = CAPACITY_LIST[0];
    private int indexInCapacityList = 0; // Текущий индекс в CAPACITY_LIST
    private int amountOfElements = 0; // Количество элементов
    private int amountOfKeys = 0; // Количество созданных List (количество ключей)

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        int hash = key.hashCode();
        List<Node> list = nodes[hash % currentCapacity];

        if (list == null) {
            return null; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            Node temp = list.get(i);
            if (temp.key == key) {
                return temp.value; // Нашли, возвращаем value
            }
        }

        return null; // Если ничего не нашли, вернём null
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hash = key.hashCode();
        List<Node> list = nodes[hash % currentCapacity];

        if (list == null) {
            return false; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            Node temp = list.get(i);
            if (temp.key == key) {
                return true; // Нашли
            }
        }

        return false;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        put(new Node(key, value, key.hashCode()));
    }

    private void put(Node node) {
        int index = node.hash % currentCapacity;

        if (nodes[index] == null) {
            nodes[index] = new LinkedList<>();
            amountOfKeys++;
        }
        nodes[index].add(node);
        amountOfElements++;

        if (amountOfElements / currentCapacity > LOAD_FACTOR) {
            resize();
        }
    }

    private void resize() {
        List<Node>[] temp = nodes; // Сохраняем значения, чтобы можно было их перенести в новый увеличенный nodes
        nodes = new LinkedList[CAPACITY_LIST[++indexInCapacityList]]; // Делаем новый LinkedList больше старого (увеличиваем размер nodes);
        amountOfKeys = 0;

        for (List<Node> a : temp) {
            for (Node b : a) {
                put(b); // Переписываем значения в новый увеличенный list
            }
        }
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        int hash = key.hashCode();
        List<Node> list = nodes[hash % currentCapacity];

        if (list == null) {
            return null; // Ничего не нашли, по такому хешкоду вообще ни одного элемента
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            Node temp = list.get(i);
            if (temp.key == key) {
                list.remove(i); // Нашли, удаляем
                amountOfElements--; // Количество элементов стало на 1 меньше

                // Если по данному ключу нет других элементов, то присваиваем null списку этого ключа и количество ключей стало на 1 меньше
                if (list.isEmpty()) {
                    amountOfKeys--;
                    nodes[hash % currentCapacity] = null;
                }

                return temp.value; // Возвращаем удалённое значение
            }
        }

        return null; // Если ничего не нашли, вернём null
    }

    @Override
    public int size() {
        return amountOfKeys;
    }

    @Override
    public boolean isEmpty() {
        if (amountOfKeys == 0) {
            return true;
        }

        return false;
    }
}
