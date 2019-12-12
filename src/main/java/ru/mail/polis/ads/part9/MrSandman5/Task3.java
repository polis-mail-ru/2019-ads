package part9;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/6321170

public class Task3 {

    private static final int INF = Integer.MAX_VALUE - 1;

    private static class Edge{
        int begin, end;
        long weight;

        public Edge(int begin, int end, long weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        ArrayList<Edge> graph = new ArrayList<>(100001);
        for (int i = 0; i < m; i++) {
            graph.add(new Edge(in.nextInt(), in.nextInt(), in.nextLong()));
        }

        long[] d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = INF;
        }
        d[0] = 0;

        for (int i = 0; i < n ; i++)
        {
            for (int j = 0; j < m; j++)
            {
                int x = graph.get(j).begin;
                int y = graph.get(j).end;
                x--; y--;
                long weight = graph.get(j).weight;
                if (d[x] != INF){
                    d[y] = Math.min(d[y], d[x] + weight);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (d[i] == INF) d[i] = 30000;
            out.print(d[i] + " ");
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

        long nextLong(){
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}