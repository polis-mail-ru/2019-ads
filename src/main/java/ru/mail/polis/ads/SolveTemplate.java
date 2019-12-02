package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
//public final class SolveTemplate {
//    private static Map<Integer, LinkedList<Integer>> nodes;
//    private static int[] colors;
//    private static LinkedList<Integer> res;
//    private SolveTemplate() {
//        // Should not be instantiated
//    }
//
//    private static void solve(final FastScanner in, final PrintWriter out) {
//        int n = in.nextInt();
//        int m = in.nextInt();
//        Integer a;
//        Integer b;
//        out.println(m);
//        out
//        nodes = new HashMap<>();
//        colors = new int[1000000];
//        res = new LinkedList<>();
//        for (int i = 0; i < m; m++){
//            a = in.nextInt();
//            b = in.nextInt();
//            if (!nodes.containsKey(a)){
//                nodes.put(a, new LinkedList<>());
//            }
//            nodes.get(a).add(b);
//
//        }
//        out.println(nodes);
//        for (int i = 0; i < n; n++){
//            sort(i);
//        }
//        out.println(res);
//        out.flush();
//    }
//
//    public static void sort(int a){
//        if (colors[a] == 1){
//            res.add(-1);
//        }
//        if (colors[a] == 0){
//            colors[a] = 1;
//            LinkedList<Integer> ch = nodes.get(a);
//            for (Integer integer : ch) {
//                sort(integer);
//            }
//            res.add(a);
//            colors[a] = 2;
//        }
//
//
//    }
//
//    private static class FastScanner {
//        private final BufferedReader reader;
//        private StringTokenizer tokenizer;
//
//        FastScanner(final InputStream in) {
//            reader = new BufferedReader(new InputStreamReader(in));
//        }
//
//        String next() {
//            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
//                try {
//                    tokenizer = new StringTokenizer(reader.readLine());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return tokenizer.nextToken();
//        }
//
//        int nextInt() {
//            return Integer.parseInt(next());
//        }
//    }
//
//    public static void main(final String[] arg) {
//        final FastScanner in = new FastScanner(System.in);
//        try (PrintWriter out = new PrintWriter(System.out)) {
//            solve(in, out);
//        }
//    }
//}
