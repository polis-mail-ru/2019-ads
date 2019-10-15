package ru.mail.polis.ads.part2.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class StackToQueue {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {
            String stackString = in.next();
            int len = stackString.length();
            if (len == 1) {
                System.out.println(stackString);
            } else {
                Node root = new Node(stackString.charAt(len - 1));
                root.left = new Node(' ');
                root.right = new Node(' ');
                root.parent = null;
                root.left.parent = root;
                root.right.parent = root;
                Node currentNode = root.right;
                for (int i = stackString.length() - 2; i >= 0; i--) {
                    currentNode.name = stackString.charAt(i);
                    if (Character.isLowerCase(currentNode.name)) {
                        currentNode.left = null;
                        currentNode.right = null;
                        while (currentNode.parent != null && currentNode == currentNode.parent.left)
                            currentNode = currentNode.parent;
                        if (currentNode.parent == null)
                            continue;
                        currentNode = currentNode.parent.left;
                    } else {
                        currentNode.right = new Node(' ');
                        currentNode.left = new Node(' ');
                        currentNode.left.parent = currentNode;
                        currentNode.right.parent = currentNode;
                        currentNode = currentNode.right;
                    }
                }
                char[] queueString = new char[len];
                int index = len - 1;
                ArrayList<Node> queue = new ArrayList<>();
                queue.add(root);
                while (index >= 0) {
                    if (queue.get(0).left != null)
                        queue.add(queue.get(0).left);
                    if (queue.get(0).right != null)
                        queue.add(queue.get(0).right);
                    queueString[index] = queue.get(0).name;
                    index--;
                    queue.remove(0);
                }
                System.out.println(String.valueOf(queueString));
            }
        }
    }

    static class Node {
        char name;
        Node left;
        Node right;
        Node parent;
        Node(char c) {
            this.name = c;
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
