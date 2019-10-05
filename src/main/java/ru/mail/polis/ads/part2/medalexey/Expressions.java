package ru.mail.polis.ads.part2.medalexey;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


/**
 *  Задача: Выражения (https://www.e-olymp.com/ru/problems/3837)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5783276
 */
public class Expressions {

    private Expressions() {

    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final int numberOfExpressions = in.nextInt();

        for (int i = 0; i < numberOfExpressions; i++) {
            out.println(buildQueueEquivalent(buildTree(in.next())));
        }

    }


    // строим дерево выражения
    private static ArrayDeque<TreeNode> buildTree(final String sequence) {
        final ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curElement;

        // строим дерево выражения
        for (char item: sequence.toCharArray()) {
            if (Character.isLowerCase(item)) {
                stack.push(new TreeNode(item));
            } else {
                curElement = new TreeNode(item);
                curElement.rightChild = stack.pop();
                curElement.leftChild = stack.pop();
                stack.push(curElement);
            }
        }

        return stack;
    }


    // формируем из дерева последовательность для очереди
    private static String buildQueueEquivalent(final ArrayDeque<TreeNode> queue) {
        final StringBuilder result = new StringBuilder();
        TreeNode curElement;

        while (!queue.isEmpty()) {
            curElement = queue.pollFirst();
            result.insert(0, curElement.name);
            if (curElement.leftChild != null && curElement.rightChild != null) {
                queue.addLast(curElement.leftChild);
                queue.addLast(curElement.rightChild);
            }
        }

        return result.toString();
    }


    private static class TreeNode {
        char name;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(char name) {
            this.name = name;
            this.leftChild = null;
            this.rightChild = null;
        }
    }


    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
