package ru.mail.polis.ads.bst;

public class TestOneNode {
    public static void main(String[] args) {
        AvlBst<Integer, String > avl = new AvlBst<>();
        System.out.println(avl.get(1));
        System.out.println(avl.size());
        avl.put(1, "X");
        System.out.println(avl.get(1));
        System.out.println(avl.size());
        avl.remove(1);
        System.out.println(avl.get(1));
        System.out.println(avl.size());
    }
}
