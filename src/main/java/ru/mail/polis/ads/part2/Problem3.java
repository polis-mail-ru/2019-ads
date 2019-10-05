package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5784926
 */
public class Problem3 {

    private static Deque<Tree> stack;
    private static List<String> results;
    private static BufferedReader reader;

    private static class Tree {
        private Tree left;
        private Tree right;
        private char content;

        private Tree(final Tree left, final Tree right, final char content) {
            this.left = left;
            this.right = right;
            this.content = content;
        }
    }


    private Problem3() {
        // Should not be instantiated
    }

    private static void solve() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(reader.readLine());
        stack = new ArrayDeque<>();
        results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            processLine();
        }
        results.forEach(System.out::println);
    }

    private static void processLine() throws IOException {
        final String str = reader.readLine();

        createTree(str);

        Deque<Character> chars = traverseTree();

        addCharsToResult(chars);
    }

    private static Deque<Character> traverseTree() {
        final Tree tree = stack.pop();
        final Deque<Character> chars = new ArrayDeque<>();

        final Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            final Tree node = queue.poll();
            chars.push(node.content);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return chars;
    }

    private static void addCharsToResult(final Deque<Character> chars) {
        final StringBuilder builder = new StringBuilder();
        chars.forEach(builder::append);
        results.add(builder.toString());
    }

    private static void createTree(final String str) {
        for (int j = 0; j < str.length(); j++) {
            final char c = str.charAt(j);
            if (Character.isUpperCase(c)) {
                final Tree right = stack.pop();
                final Tree left = stack.pop();
                stack.push(
                    new Tree(left, right, c)
                );
            } else {
                stack.push(new Tree(null, null, c));
            }
        }
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
