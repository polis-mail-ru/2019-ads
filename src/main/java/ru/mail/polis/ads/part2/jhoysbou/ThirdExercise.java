package ru.mail.polis.ads.part2.jhoysbou;

import java.util.LinkedList;
import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5811033

public class ThirdExercise {

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

    public static class Tree {
        private Node root;

        Tree(String expression) {
            root = new Node(expression.charAt(expression.length() - 1));
            LinkedList<Node> right = new LinkedList<>();
            Node node = root;

            for (int i = expression.length() - 2; i >= 0; i--) {
                char currentChar = expression.charAt(i);

                if (Character.isUpperCase(currentChar)) {
                    if (node.getLeft() == null) {
                        right.push(node);
                        node.setLeft(new Node(currentChar));
                        node = node.getLeft();
                    } else if (node.getRight() == null) {
                        node.setRight(new Node(currentChar));
                        node = node.getRight();
                    } else {
                        node = right.pop();
                        node.setRight(new Node(currentChar));
                        node = node.getRight();
                    }
                } else {
                    if (node.getLeft() == null) {
                        node.setLeft(new Node(currentChar));
                    } else {
                        node.setRight(new Node(currentChar));

                        if (right.size() > 0) {
                            node = right.pop();
                        }
                    }
                }
            }
        }

        String reversePrefix() {
            StringBuilder string = new StringBuilder();
            LinkedList<Node> queue = new LinkedList<>();
            queue.addFirst(root);

            while (!queue.isEmpty()) {
                Node node = queue.removeLast();
                string.append(node.getValue());
                if (node.getRight() != null) {
                    queue.addFirst(node.getRight());
                }
                if (node.getLeft() != null) {
                    queue.addFirst(node.getLeft());
                }
            }

            return string.reverse().toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfLines = Integer.parseInt(scanner.nextLine());

        String line;
        for (int i = 0; i < numberOfLines; i++) {
            line = scanner.nextLine();
            Tree tree = new Tree(line);
            System.out.println(tree.reversePrefix());
        }
    }
}