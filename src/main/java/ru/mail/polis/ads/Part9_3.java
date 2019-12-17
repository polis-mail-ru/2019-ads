import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Part9_3 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        int distances[] = new int[n+1];
        Edge edges[] = new Edge[m+1];

        for (int i = 2; i <= n; i++) {
            distances[i] = 30000;
        }

        for (int i = 1; i <= m; i++) {
            Edge edge = new Edge();
            edge.tail = fs.nextInt();
            edge.head = fs.nextInt();
            edge.weight = fs.nextInt();
            edges[i] = edge;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (distances[edges[j].tail] == 30000) {
                    continue;
                }
                distances[edges[j].head] = Math.min(distances[edges[j].head], edges[j].weight + distances[edges[j].tail]);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(distances[i] + " ");
        }
    }

    private static class Edge {
        int tail;
        int head;
        int weight;
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
