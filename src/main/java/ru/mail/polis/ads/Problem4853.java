package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Problem4853 {
    private static Node[] nodes;
    private static int[] colors;
    private static int[] res1;
    private static ArrayList<LinkedList<Integer>> res;


    public static void bfs(int a){
        res1[a] = 0;
        res.get(a).add(a);
        int u;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(a);
        while (!q.isEmpty()){
            u = q.remove();
            colors[u] = 1;
            if (nodes[u] != null){
                for (int i:nodes[u].childrens) {
                    if (res1[i] > res1[u]+1){
                        res.set(i, (LinkedList<Integer>) res.get(u).clone());
                        res.get(i).add(i);
                        res1[i] = res1[u]+1;
                    }
                    if (colors[i] != 1){
                        q.add(i);
                    }
                }
            }
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
    public static class Node{
        LinkedList<Integer> childrens;

        Node(){
            childrens = new LinkedList<>();
        }
        void add(Integer a){
            childrens.add(a);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int from = in.nextInt();
        int to = in.nextInt();
        Integer a;
        Integer b;
        nodes = new Node[n+1];
        colors = new int[n+1];
        res1 = new int[n+1];
        res = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++){
            res.add(new LinkedList<>());
        }
        Arrays.fill(res1, 10000000);
        for (int i = 0; i < m; i++){
            a = in.nextInt();
            b = in.nextInt();
            if (nodes[a] == null){
                nodes[a] = new Node();
            }
            nodes[a].add(b);
            if (nodes[b] == null){
                nodes[b] = new Node();
            }
            nodes[b].add(a);
        }

        bfs(from);
        LinkedList<Integer> st = res.get(to);
        if (res1[to] == 10000000 ||res1[to] == 0){
            out.print(-1);
        }
        else{
            out.println(st.size()-1);
            for (int i = 0; i < st.size()-1; i++){
                out.print(st.get(i)+" ");
            }
            out.print(to);

        }
        out.flush();
    }
}