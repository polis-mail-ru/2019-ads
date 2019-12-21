package part10;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/6365046

public class Task2 {

    private static final int MAXV = 20001;
    private static final int MAXE = 100001;
    private static int[] mas = new int[MAXV], size = new int[MAXV];

    private static class Edge{
        int begin, end;
        int weight;

        public Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<Edge> graph = new ArrayList<>(MAXE);

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
            if (union(graph.get(i).begin, graph.get(i).end)) res += graph.get(i).weight;
        }

        out.println(res);
    }

    private static int findRepresenter(int n)
    {
        if (n == mas[n]) return n;
        return mas[n] = findRepresenter(mas[n]);
    }

    private static boolean union(int x, int y)
    {
        int x1 = findRepresenter(x), y1 = findRepresenter(y);
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