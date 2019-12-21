package part10;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {

    private static final int MAX = 1000001;
    private static int[] arr = new int [MAX];

    private static class Edge{
        int begin, end;
        int danger;

        public Edge(int begin, int end, int danger) {
            this.begin = begin;
            this.end = end;
            this.danger = danger;
        }
    }

    private static int findRepresenter(int n)
    {
        if (n == arr[n]) return n;
        return arr[n] = findRepresenter(arr[n]);
    }

    private static void union(int x, int y)
    {
        int x1 = findRepresenter(x), y1 = findRepresenter(y);
        if (x1 != y1) arr[x1] = y1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        List<Edge> graph = new ArrayList<>(MAX);

        for (int i = 0; i < m; i++) {
            graph.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
        }

        for (int i = 1; i <= n; i++) arr[i] = i;

        graph.sort(Comparator.comparingInt(o -> o.danger));

        int fin = 0;
        for (int i = 0; i < m; i++)
        {
            union(graph.get(i).begin, graph.get(i).end);
            if (findRepresenter(1) == findRepresenter(n)) {
                fin = i;
                break;
            }
        }
        out.println(graph.get(fin).danger);
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
