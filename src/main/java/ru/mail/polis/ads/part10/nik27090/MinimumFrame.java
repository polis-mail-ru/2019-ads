package ru.mail.polis.ads.part10.nik27090;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//  Задача: Минимальный каркас
//  Решение: https://www.e-olymp.com/ru/submissions/6415354

public class MinimumFrame {

    private static ArrayList<Edge> graph;
    private static int[] mas;
    private static int[] size;


    private static class Edge{
        int start, end;
        int weight;

        public Edge(int begin, int end, int weight) {
            this.start = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList<>(n);
        mas = new int[n+1];
        size= new int[n+1];

        for(int i = 1; i <= n; i++) {
            mas[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            graph.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }

        graph.sort(Comparator.comparingInt(o -> o.weight));

        int res = 0;
        for(int i = 0; i < m; i++) {
            if (union(graph.get(i).start, graph.get(i).end)){
                res += graph.get(i).weight;
            }
        }

        out.println(res);
    }

    private static int repr(int n)
    {
        if (n == mas[n]) return n;
        return mas[n] = repr(mas[n]);
    }

    private static boolean union(int x, int y)
    {
        int x1 = repr(x), y1 = repr(y);
        if (x1 == y1){
            return false;
        }
        if (size[x1] < size[y1]){
            int tmp = x1;
            x1 = y1;
            y1 = tmp;
        }
        mas[y1] = x1;
        size[x1] += size[y1];
        return true;
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