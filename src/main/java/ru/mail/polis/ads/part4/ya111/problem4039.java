import java.io.*;
import java.util.*;


public class problem4039 {
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
        int n = in.nextInt();
        PriorityQueue<Integer> prq = new PriorityQueue<>((Collections.reverseOrder()));
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; ++i) {
                int k = in.nextInt();
                if (k == 0) {
                    int temp = in.nextInt();
                    prq.add(temp);
                }
                if (k == 1) {
                    out.println(prq.peek());
                    prq.remove(prq.peek());
                }

            }
        }
    }
}