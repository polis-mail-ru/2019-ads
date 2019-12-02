package ru.mail.polis.ads;

import java.io.*;
import java.util.*;

public class Problem4856 {
    private static ArrayList<HashMap<Integer, Integer>> nodes;
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
            if (!nodes.get(u).isEmpty()){
                for (Map.Entry<Integer, Integer> i:nodes.get(u).entrySet()) {
                    if (res1[i.getKey()] > res1[u]+i.getValue()){
                        res.set(i.getKey(), (LinkedList<Integer>) res.get(u).clone());
                        res.get(i.getKey()).add(i.getKey());
                        res1[i.getKey()] = res1[u]+i.getValue();
                    }
                    if (colors[i.getKey()] != 1){
                        q.add(i.getKey());
                    }
                }
            }
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
        Integer c;
        nodes = new ArrayList<>(n+1);
        for (int i=0; i < n+1; i++){
            nodes.add(new HashMap<>());
        }
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
            c = in.nextInt();
            nodes.get(a).put(b, c);
            nodes.get(b).put(a, c);
        }

        bfs(from);
        LinkedList<Integer> st = res.get(to);
        if (res1[to] == 10000000 ||res1[to] == 0){
            out.print(-1);
        }
        else{
            out.println(res1[to]);
            for (int i = 0; i < st.size()-1; i++){
                out.print(st.get(i)+" ");
            }
            out.print(to);

        }
        out.flush();
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
