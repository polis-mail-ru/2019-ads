package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;

/**
 * Made by БорискинМА
 * inspired by lectures & Stephen Hall's 2017 Red Black Tree implementation in Java:
 * https://github.com/halls7588/Data_Structures_in_15_Languages/blob/master/Java/Trees/RedBlackTree/RedBlackTree.java
 * 23.11.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * LLRB implementation of binary search tree.
 */
/**
 * К-ч деревья (left-learning red-black tree):
 * В каждый узел, кроме корневого, входит ровно один линк.
 * Все следует из эквивалентности 2-3 деревьям.
 * Максимальная высота: 2*log2(n): 3-узлы - на пути к минимальному ключу; 2-узлы - все остальные).
 * Сложность всех операций - O(log(n)).
 * Каждый узел либо красный, либо черный: каждый узел либо 3-узел, либо 2-узел. Красный узел - это левая часть 3-узла.
 * Корень черный: в него ничего не входит. Корень не может быть левой частью 3-узла.
 * У красного узла только черные дети: потому что нет 4-узла.
 * Идеальная черная высота - количество черных узлов в любом пути от корня до листа одинаково:
 * 2-3 дерево идеально сбалансировано. Черные линки - линки между узлами в 2-3 дереве.
 *
 * Сохранить заданное значение по указанному ключу:
 * Добавление: в 2-3 дерево с учетом представления через цвет линка (повороты и смена цвета).
 * Вставка в к-ч дерево из одного узла: новый ключ добавляется с красным линком.
 * Если новый ключ добавляется справа: нужно повернуть - красный линк должен быть левым.
 * Вставка в любое двоичное дерево поиска происходит на дне. Новый узел добавляется с красным линком: 2-узел до 3-узла.
 * Если новый узел добавляется справа, то нужно повернуть - красный линк должен быть левым.
 * Два узла, связанные красным линком - одинокий 3-узел. Новый ключ может быть меньше, больше или находиться между существующими.
 * В таком случае: swap(). Корень покраснел - это сигнал о том, что высота дерева увеличилась на 1. Цвет корня: сбрасываем в черный.
 * 1) Ключ больше: swap();
 * 2) Ключ меньше: rightRotate(), swap();
 * 3) Ключ между: leftRotate(), rightRotate(), swap().
 * В случае вставки в 3-узел на дне: аналогично + входящий линк краснеет - нужно восстановить инвариант в родителе:
 * 1) Правый сын красный: leftRotate();
 * 2) Левый сын красный && его левый сын тоже красный: rightRotate();
 * 3) Оба сына черные: swap().
 * + Повторяем те же действия на выходе из рекурсивного put().
 *
 * Удалить по заданному ключу:
 * Узел с минимальным ключом всегда на дне дерева (легко удалять из не 2-узла на дне в 2-3 дереве):
 * Нужно обеспечить, чтобы узел с минимальным ключом был не 2-узлом.
 * deleteMin(): спускаясь вниз влево, поддерживаем текущий узел в 2-3 дереве не 2-узлом.
 * deleteMax(): спускаясь вниз вправо, поддреживаем текущий узел в 2-3 дереве не 2-узлом.
 * Удаление произвольного элемента:
 * Все как в обычных бинарных деревьях поиска + поддерживаем инвариант: текущий узел - не 2-узел.
 * При поиска слева: REDtoLeft().
 * При поиске справа: REDtoRight().
 * Если нашли node - замещаем его ключ и значение через deleteMin(node.right).
 * Разворачиваем правые красные линки и удаляем временные 4-узлы на выходе из рекурсии - checkColor().
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

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.height = 1;
        }

        // https://www.javaworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
        // https://www.geeksforgeeks.org/overriding-equals-method-in-java/
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            Node node = (Node) object;

            return (key.equals(node.key) && value.equals(node.key));
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private class TreeSuccessor {
        Node node;
        Value value;

        TreeSuccessor(Node node, Value value) {
            this.node = node;
            this.value = value;
        }
    }

    public RedBlackBst() {
        this.root = null;
        this.size = 0;
    }

    public RedBlackBst(Node root) {
        this.root = root;
        this.size = 1;
    }

    public RedBlackBst(Map<? extends Key, ? extends Value> elements) {
        this.root = null;
        this.size = 0;
        for (Map.Entry<? extends Key, ? extends Value> element : elements.entrySet()) {
            this.put(element.getKey(), element.getValue());
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return findNode(key, root);
    }

    private Value findNode(Key key, Node node) {
        if (node == null) {
            return null;
        }

        int matching = key.compareTo(node.key);
        if (matching > 0) {
            return findNode(key, node.right);
        }
        else if (matching < 0) {
            return findNode(key, node.left);
        }
        else if (key == node.key) {
            return node.value;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        // к-ч дерево
        root = put(key, value, root);
        root.color = BLACK;
    }

    // https://www.geeksforgeeks.org/red-black-tree-set-2-insert/
    // Идем от корня до тех пор, пока следующий сын не лист;
    // вместо него вставляем новый элемент с красным цветом и с нулевыми потомками.
    // Далее проверки на баланс.
    // Визуализация: https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        int matching = key.compareTo(node.key);
        // тот же проход по дереву
        if (matching < 0) {
            node.left = put(key, value, node.left);
        }
        else if (matching > 0) {
            node.right = put(key, value, node.right);
        }
        else {
            node.value = value;
        }

        node = checkColor(node);
        node.height = newHeight(node);

        return node;
    }

    private int newHeight(Node node) {
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private Node checkColor(Node node) {

        Node current = node;

        if (isRED(current.left) && isRED(current.left.left)) {
            current = rightRotate(current);
        }

        if (isRED(current.left) && isRED(current.right)) {
            swap(current);
        }

        if (isRED(current.right) && !isRED(current.left)) {
            current = leftRotate(current);
        }

        return current;
    }

    private boolean isRED(Node node) {
        return (node != null && node.color);
    }

    private Node swap(Node node) {
        node.left.color = !node.left.color;

        node.right.color = !node.right.color;

        node.color = !node.color;

        return node;
    }

    private Node rightRotate(Node node) {
        Node lNode = node.left;
        node.left = lNode.right;
        lNode.right = node;

        node.height = newHeight(node);
        node.height = newHeight(lNode);

        lNode.color = node.color;
        node.color = RED;

        return lNode;
    }

    private Node leftRotate(Node node) {
        Node rNode = node.right;
        node.right = rNode.left;
        rNode.left = node;

        node.height = newHeight(node);
        node.height = newHeight(rNode);

        rNode.color = node.color;
        node.color = RED;

        return rNode;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        TreeSuccessor redBlackTree = newRedBlackTree(key, new TreeSuccessor(root, null));

        root = redBlackTree.node;

        if (redBlackTree.value != null) {
            size--;
        }

        if (root != null) {
            root.color = BLACK;
        }

        return redBlackTree.value;
    }

    private TreeSuccessor newRedBlackTree(Key key, TreeSuccessor treeSuccessor) {
        if (treeSuccessor.node == null) {
            return treeSuccessor;
        }

        int matching = key.compareTo(treeSuccessor.node.key);
        if (matching < 0) {
            if (treeSuccessor.node.left != null) {
                if (!isRED(treeSuccessor.node.left) && !isRED(treeSuccessor.node.left.left)) {
                    treeSuccessor.node = REDtoLeft(treeSuccessor.node);
                }

                TreeSuccessor temp = new TreeSuccessor(treeSuccessor.node.left,treeSuccessor.value);

                newRedBlackTree(key, temp);

                treeSuccessor.node.left = temp.node;
                treeSuccessor.value = temp.value;
            }
        }
        else if (matching > 0) {
            if (treeSuccessor.node.right != null) {
                if (isRED(treeSuccessor.node.left)) {
                    treeSuccessor.node = rightRotate(treeSuccessor.node);
                }

                if (!isRED(treeSuccessor.node.right) && !isRED(treeSuccessor.node.right.left)) {
                    treeSuccessor.node = REDtoRight(treeSuccessor.node);
                }

                TreeSuccessor temp = new TreeSuccessor(treeSuccessor.node.right, treeSuccessor.value);

                newRedBlackTree(key, temp);

                treeSuccessor.node.right = temp.node;
                treeSuccessor.value = temp.value;
            }
        }
        else {
            if (isRED(treeSuccessor.node.left)) {
                treeSuccessor.node = rightRotate(treeSuccessor.node);

                if (treeSuccessor.node.right != null) {
                    Node temp = treeSuccessor.node.right;

                    treeSuccessor.value = temp.value;

                    if (temp.right == null) {
                        treeSuccessor.node.right = treeSuccessor.node.right.left;
                    }
                    else {
                        treeSuccessor.node.right.key = findMin(temp.right).key;

                        treeSuccessor.node.right.value = findValue(treeSuccessor.node.right.key, treeSuccessor.node.right.right);

                        treeSuccessor.node.right.right = deleteMin(temp.right);

                        if (treeSuccessor.node.right.right == null && treeSuccessor.node.right.left != null && !isRED(treeSuccessor.node.right.left)) {
                            treeSuccessor.node.right.left.color = RED;
                            treeSuccessor.node.right.color = BLACK;
                        }
                    }

                    treeSuccessor.node = checkColor(treeSuccessor.node);

                    treeSuccessor.node.height = newHeight(treeSuccessor.node);

                    return treeSuccessor;
                }
            }

            if (treeSuccessor.node.right == null) {
                treeSuccessor.value = treeSuccessor.node.value;
                treeSuccessor.node = null;

                return treeSuccessor;
            }

            if (treeSuccessor.node.left != null && !isRED(treeSuccessor.node) && !isRED(treeSuccessor.node.right) && !isRED(treeSuccessor.node.left)) {
                swap(treeSuccessor.node);

                treeSuccessor.node.color = BLACK;
            }

            Node temp = treeSuccessor.node;

            treeSuccessor.value = temp.value;

            treeSuccessor.node.key = findMin(temp.right).key;

            treeSuccessor.node.value = findValue(treeSuccessor.node.key, treeSuccessor.node.right);

            treeSuccessor.node.right = deleteMin (temp.right);

            if (treeSuccessor.node.right == null && treeSuccessor.node.left != null && !isRED(treeSuccessor.node.left)) {
                treeSuccessor.node.left.color = RED;
                treeSuccessor.node.color = BLACK;
            }
        }

        treeSuccessor.node = checkColor(treeSuccessor.node);

        treeSuccessor.node.height = newHeight(treeSuccessor.node);

        return treeSuccessor;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRED(node.left) && !isRED(node.left.left)) {
            node = REDtoLeft(node);
        }

        node.left = deleteMin(node.left);

        node = checkColor(node);

        node.height = newHeight(node);

        return node;
    }

    private Value findValue(Key key, Node node) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {
            return findValue(key, node.right);
        }
        else if (key.compareTo(node.key) < 0) {
            return findValue(key, node.left);
        }

        return node.value;
    }

    private Node REDtoLeft(Node node) {
        swap(node);

        if (isRED(node.right.left)) {
            node.right = rightRotate((node.right));

            node = leftRotate(node.right);

            swap(node);
        }

        return node;
    }

    private Node REDtoRight(Node node) {
        swap(node);

        if (isRED(node.left.left)) {
            node = rightRotate(node);

            swap(node);
        }

        return node;
    }

    @Nullable
    @Override
    public Key min() {
        return root != null ? findMin(root).key : null;
    }

    private Node findMin(Node node) {
        if (node.left != null) {
            return findMin(node.left);
        }
        return node;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root != null ? findMin(root).value : null;
    }

    @Nullable
    @Override
    public Key max() {
        return root != null ? findMax(root).key : null;
    }

    private Node findMax(Node node) {
        if (node.right != null) {
            return findMax(node.right);
        }
        return node;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root != null ? findMax(root).value : null;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return root != null ? findFloor(key, root) : null;
    }

    private Key findFloor(Key key, Node node) {
        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                return findFloor(key, node.left);
            }
            else {
                return null;
            }
        }
        else {
            if (node.right != null) {
                if (key.compareTo(node.right.key) < 0) {
                    if (node.right.left != null) {
                        return findFloor(key, node.right.left);
                    }
                    else {
                        return node.key;
                    }
                }
                else {
                    return findFloor(key, node.right);
                }
            }
            else {
                return node.key;
            }
        }
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return root != null ? findCeil(key, root) : null;
    }

    private Key findCeil(Key key, Node node) {
       if (key.compareTo(node.key) > 0) {
           if (node.right != null) {
               return findCeil(key, node.right);
           }
           else {
               return null;
           }
       }
       else {
           if (node.left != null) {
               if (key.compareTo(node.left.key) > 0) {
                   if (node.left.right != null) {
                       return findCeil(key, node.left.right);
                   }
                   else {
                       return node.key;
                   }
               }
               else {
                   return findCeil(key, node.left);
               }
           }
           else {
               return node.key;
           }
       }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private TreeSuccessor deleteMin(TreeSuccessor treeSuccessor) {
        if (treeSuccessor.node == null) {
            return treeSuccessor;
        }

        if (treeSuccessor.node.left == null) {
            treeSuccessor.value = treeSuccessor.node.value;

            treeSuccessor.node = null;

            return treeSuccessor;
        }

        if (!isRED(treeSuccessor.node.left) && !isRED(treeSuccessor.node.left.left)) {
            treeSuccessor.node = REDtoLeft(treeSuccessor.node);
        }

        TreeSuccessor tempTreeWithoutValue = new TreeSuccessor(treeSuccessor.node.left, treeSuccessor.value);

        deleteMin(tempTreeWithoutValue);

        treeSuccessor.node.left = tempTreeWithoutValue.node;
        treeSuccessor.value = tempTreeWithoutValue.value;
        treeSuccessor.node = checkColor(treeSuccessor.node);
        treeSuccessor.node.height = newHeight(treeSuccessor.node);

        return treeSuccessor;
    }

    private TreeSuccessor deleteMax(TreeSuccessor treeSuccessor) {
        if (treeSuccessor.node == null) {
            return treeSuccessor;
        }

        if (isRED(treeSuccessor.node.left)) {
            treeSuccessor.node = rightRotate(treeSuccessor.node);
        }

        if (treeSuccessor.node.right == null) {
            treeSuccessor.value = treeSuccessor.node.value;

            treeSuccessor.node = null;

            return treeSuccessor;
        }

        if (!isRED(treeSuccessor.node.right) && !isRED(treeSuccessor.node.right.right)) {
            treeSuccessor.node = REDtoRight(treeSuccessor.node);
        }

        TreeSuccessor tempTreeWithoutValue = new TreeSuccessor(treeSuccessor.node.right, treeSuccessor.value);

        deleteMax(tempTreeWithoutValue);

        treeSuccessor.node.right = tempTreeWithoutValue.node;
        treeSuccessor.value = tempTreeWithoutValue.value;
        treeSuccessor.node = checkColor(treeSuccessor.node);

        treeSuccessor.node.height = newHeight(treeSuccessor.node);

        return treeSuccessor;
    }


}
