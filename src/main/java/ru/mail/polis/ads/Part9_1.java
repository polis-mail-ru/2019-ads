import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Part9_1 {
    private static List<Integer> adjListArray[];
    private static Color colors[];
    private static int[] order;
    private static int curLabel;

    enum Color {
        WHITE,
        GREY,
        BLACK
    }

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        adjListArray = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjListArray[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; i++) {
            adjListArray[fs.nextInt()].add(fs.nextInt());
        }

        colors = new Color[n + 1];
        for (int i = 1; i <= n; i++) {
            colors[i] = Color.WHITE;
        }
        order = new int[n + 1];

        try {
            topoSort(n);
            for (int i = 1; i <= n; i++) {
                System.out.print(order[i] + " ");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(-1);
        }
    }

    public static void topoSort( int v) {
        curLabel = v;
        for (int i = 1; i <= v ; i++) {
            if (colors[i] == Color.WHITE) {
                dfsTopo(i);
            }
        }
    }

    public static void dfsTopo(int s) {
        colors[s] = Color.GREY;
        List<Integer> list = adjListArray[s];
        for (Integer v : list) {
            if (colors[v] == Color.WHITE) {
                dfsTopo(v);
            } else if (colors[v] == Color.GREY) {
                throw new IllegalArgumentException("Cyclic graph");
            }
        }
        colors[s] = Color.BLACK;
        order[curLabel--] = s;
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
