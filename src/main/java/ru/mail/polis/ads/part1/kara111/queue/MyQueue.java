package ru.mail.polis.ads.part1.kara111.queue;

class Node {

    public Node next;

    public int value;

}

public class MyQueue {
    Node first, last;
    int length = 0;

    void push(int a) {

        length++;
        Node node = new Node();
        node.value = a;
        if (first == null) {
            last = node;
            first = last;
        } else {
            last.next = node;
            last = last.next;
        }
        System.out.println("ok");

    }

    void pop() {
        length--;
        if (first == last) {
            Node buf = first;
            first = null;
            last = null;
            System.out.println(buf.value);
        } else {
            Node buf = first;
            first = first.next;
            System.out.println(buf.value);
        }

    }

    void front() {
        System.out.println(first.value);

    }

    void size() {
        System.out.println(length);
    }

    void clear() {
        while (this.length > 0) {
            this.pop();
        }
        System.out.println("ok");
    }


}


