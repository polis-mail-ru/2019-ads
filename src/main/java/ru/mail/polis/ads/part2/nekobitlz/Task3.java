package ru.mail.polis.ads.part2.nekobitlz;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int t = Integer.parseInt(in.nextLine());
        String line;

        for (int i = 0; i < t; i++) {
            line = in.nextLine();
            Tree tree = new Tree(line);
            out.println(tree.reversePrefix());
        }

        out.close();
    }

    public static class Tree {
        private Node root;

        Tree(String expression) {
            root = new Node(expression.charAt(expression.length() - 1));
            LinkedList<Node> rightStack = new LinkedList<>();
            Node n = root;

            for (int i = expression.length() - 2; i >= 0; i--) {
                char ch = expression.charAt(i);

                if (Character.isUpperCase(ch)) {
                    if (n.getLeft() == null) {
                        rightStack.push(n);
                        n.setLeft(new Node(ch));
                        n = n.getLeft();
                    } else if (n.getRight() == null) {
                        n.setRight(new Node(ch));
                        n = n.getRight();
                    } else {
                        n = rightStack.pop();
                        n.setRight(new Node(ch));
                        n = n.getRight();
                    }
                } else {
                    if (n.getLeft() == null) {
                        n.setLeft(new Node(ch));
                    } else {
                        n.setRight(new Node(ch));

                        if (rightStack.size() > 0)
                            n = rightStack.pop();
                    }
                }
            }
        }

        String reversePrefix() {
            StringBuilder sb = new StringBuilder();
            LinkedList<Node> queue = new LinkedList<>();

            queue.addFirst(root);

            while (!queue.isEmpty()) {
                Node n = queue.removeLast();
                sb.append(n.getValue());

                if (n.getRight() != null)
                    queue.addFirst(n.getRight());

                if (n.getLeft() != null)
                    queue.addFirst(n.getLeft());
            }

            return sb.reverse().toString();
        }
    }

    public static class Node {
        private Node right, left;
        private char value;

        Node(char value) {
            this.value = value;
        }

        Node getRight() {
            return right;
        }

        void setRight(Node right) {
            this.right = right;
        }

        Node getLeft() {
            return left;
        }

        void setLeft(Node left) {
            this.left = left;
        }

        char getValue() {
            return value;
        }
    }
}
