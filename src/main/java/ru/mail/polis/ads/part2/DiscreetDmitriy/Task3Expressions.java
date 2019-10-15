package ru.mail.polis.ads.part2.DiscreetDmitriy;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Task3Expressions {
    private Task3Expressions() {
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        int t = Integer.parseInt(in.nextLine());

        for (int i = 0; i < t; i++) {
            String line = in.nextLine();
            Expression expression = new Expression(line);

            StringBuilder sb = new StringBuilder();
            LinkedList<CharInExpression> queue = new LinkedList<>();

            queue.addFirst(expression.node);

            while (!queue.isEmpty()) {
                CharInExpression n = queue.removeLast();
                sb.append(n.getValue());

                if (n.getRight() != null)
                    queue.addFirst(n.getRight());

                if (n.getLeft() != null)
                    queue.addFirst(n.getLeft());
            }

            out.println(sb.reverse().toString());
        }

        out.close();
    }

    public static class Expression {
        private CharInExpression node;

        Expression(String line) {
            node = new CharInExpression(line.charAt(line.length() - 1));
            LinkedList<CharInExpression> rightStack = new LinkedList<>();
            CharInExpression n = node;

            for (int i = line.length() - 2; i >= 0; i--) {
                char ch = line.charAt(i);

                if (Character.isUpperCase(ch))
                    if (n.getLeft() == null) {
                        rightStack.push(n);
                        n.setLeft(new CharInExpression(ch));
                        n = n.getLeft();
                    } else if (n.getRight() == null) {
                        n.setRight(new CharInExpression(ch));
                        n = n.getRight();
                    } else {
                        n = rightStack.pop();
                        n.setRight(new CharInExpression(ch));
                        n = n.getRight();
                    }
                else if (n.getLeft() == null) {
                    n.setLeft(new CharInExpression(ch));
                } else {
                    n.setRight(new CharInExpression(ch));

                    if (rightStack.size() > 0)
                        n = rightStack.pop();
                }
            }
        }
    }

    public static class CharInExpression {
        private CharInExpression right, left;
        private char value;

        CharInExpression(char value) {
            this.value = value;
        }

        CharInExpression getRight() {
            return right;
        }

        void setRight(CharInExpression right) {
            this.right = right;
        }

        CharInExpression getLeft() {
            return left;
        }

        void setLeft(CharInExpression left) {
            this.left = left;
        }

        char getValue() {
            return value;
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

