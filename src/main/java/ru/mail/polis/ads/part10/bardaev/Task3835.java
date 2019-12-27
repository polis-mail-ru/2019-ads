package ru.mail.polis.ads.part10.bardaev;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3835 {

    static class Edge implements Comparable {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Object o) {
            Edge edge = (Edge) o;
            if (w != edge.w) return w < edge.w ? -1 : 1;
            return 0;
        }

    }

    static class DSF {
        int[] set;
        int[] rnk;

        DSF(int size) {
            set = new int [size + 1];
            rnk = new int [size + 1];
            for (int i = 0; i < size; i++)
                set[i] = i;
        }

        int set(int x) {
            return x == set[x] ? x : (set[x] = set(set[x]));
        }

        boolean union(int u, int v) {
            if ((u = set(u)) == (v = set(v)))
                return false;
            if (rnk[u] < rnk[v]) {
                set[u] = v;
            } else {
                set[v] = u;
                if (rnk[u] == rnk[v])
                    rnk[u]++;
            }
            return true;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Edge[] edges;
        int n = in.nextInt();
        int m = in.nextInt();
        edges = new Edge[m];
        for (int i = 0; i < m; i++) edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
        DSF dsf = new DSF(n);
        Arrays.sort(edges);
        int ret = 0;
        for (Edge e : edges)
            if (dsf.union(e.u, e.v))
                ret += e.w;
        out.println(ret);
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
