package ru.mail.polis.ads.valer1435.part2;

import java.io.*;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/5822665
public class Problem3837 {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        for (int j = 0; j < cnt; j++) {
            String str = in.next();
            Deque<Node> stack = new ArrayDeque<Node>();

            for (int index = 0; index < str.length(); ++index) {
                char ch = str.charAt(index);
                if (Character.isUpperCase(ch)) {

                    Node right = stack.pop();
                    Node left = stack.pop();

                    stack.push(new BinOp(left, right, ch));

                } else {
                    stack.push(new Literal(ch));
                }
            }

            Queue<Node> q = new ArrayDeque<>();
            q.add(stack.pop());
            StringBuilder stringBuilder = new StringBuilder();
            while (!q.isEmpty()) {
                Node node = q.remove();
                if (node instanceof BinOp) {
                    BinOp binOp = (BinOp) node;
                    stringBuilder.append(binOp.op);
                    q.add(binOp.left);
                    q.add(binOp.right);
                } else {
                    Literal literal = (Literal) node;
                    stringBuilder.append(literal.literal);
                }
            }

            String res = stringBuilder.toString();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int index = res.length() - 1; index >= 0; --index) {
                System.out.print(res.charAt(index));
            }
            System.out.println();
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }

    static abstract class Node {
    }

    static class BinOp extends Node {
        Node left;
        Node right;
        char op;

        BinOp(Node a, Node b, char c) {
            left = a;
            right = b;
            op = c;
        }

        @Override
        public String toString() {
            return "BinOp{" +
                    "left=" + left +
                    ", right=" + right +
                    ", op=" + op +
                    '}';
        }
    }

    static class Literal extends Node {
        char literal;

        Literal(char a) {
            literal = a;
        }

        @Override
        public String toString() {
            return String.valueOf(literal);
        }
    }


    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }


}
