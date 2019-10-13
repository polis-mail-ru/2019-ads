package ru.mail.polis.ads.part2.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Queue;
/**
 * Problem solution template.
 */
class Node
{
    private Node left;
    private Node right;
    private char value;

    public Node(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public void addLeftChild(Node child) {
        left = child;
    }
    public void addRightChild(Node child) {
        right = child;
    }
    public char getValue(){
        return value;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
}

public final class Task3837 {
    private Task3837() {
        // Should not be instantiated
    }
    static Node Build(String s){
        Stack stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))){
                Node right = (Node)stack.peek();
                stack.pop();
                Node left = (Node)stack.peek();
                stack.pop();
                Node root = new Node(s.charAt(i));
                root.addLeftChild(left);
                root.addRightChild(right);
                stack.push(root);
            }
            else {
                stack.push(new Node(s.charAt(i)));
            }
        }
        return (Node)stack.peek();
    }

    static String GetAns(Node root){
        Queue <Node> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder ans = new StringBuilder("");
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            ans.append(cur.getValue());
            if (cur.getLeft() != null){
                queue.offer(cur.getLeft());
            }
            if (cur.getRight() != null){
                queue.offer(cur.getRight());
            }
        }
        String reversedString = new StringBuilder(ans).reverse().toString();
        return reversedString;
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String s;
        while (n > 0){
            s = in.next();
            Node root = Build(s);
            out.println(GetAns(root));
            n--;
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
