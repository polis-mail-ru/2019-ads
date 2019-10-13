package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Task3 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        String s;
        for (int i = 0; i < t; i++) {
            s = fs.next();
            System.out.println(solve(s));
        }
    }

    public static String solve(String s) {
        Tree tree = new Tree();
        Index index = new Index(s.length() - 1);;
        buildTree(tree.root, s, index);

        Deque<Character> answer = new ArrayDeque<>();
        Deque<Tree.Node> list = new ArrayDeque<>();
        list.addLast(tree.root);
        createDeque(list, answer);
        StringBuilder sb = new StringBuilder();
        while (answer.size() > 0) {
            sb.append(answer.pollFirst());
        }

        return sb.toString();
    }

    public static void createDeque(Deque<Tree.Node> list, Deque<Character> answer) {
        Tree.Node currentNode = list.peekFirst();
        if (currentNode == null) {
            return;
        }
        answer.addFirst(list.pollFirst().data);

        if (currentNode.leftChild != null) {
            list.addLast(currentNode.leftChild);
        }

        if (currentNode.rightChild != null) {
            list.addLast(currentNode.rightChild);
        }

        if (list.size() > 0) {
            createDeque(list, answer);
        }

    }

    public static void buildTree(Tree.Node currentNode, String s, Index index) {
        currentNode.data = s.charAt(index.ind);
        index.ind--;
        if (Character.isUpperCase(s.charAt(index.ind + 1))) {
            currentNode.rightChild = new Tree.Node();
            currentNode.leftChild = new Tree.Node();
            buildTree(currentNode.rightChild, s, index);
            buildTree(currentNode.leftChild, s, index);
        }
    }

    private static class Tree {
        Node root;

        public Tree() {
            root = new Node();
        }

        private static class Node {
            Character data;
            Node leftChild;
            Node rightChild;
        }
    }

    private static class Index {
        int ind;
        public Index(int a) {
            ind = a;
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
}
