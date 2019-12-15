import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class t5 {
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
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        final int s = in.nextInt();
        final int f = in.nextInt();

        List<Integer>[] nodes = new LinkedList[n + 1];
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = -1;
            prev[i] = -1;
            nodes[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            nodes[first].add(second);
            nodes[second].add(first);
        }

        dist[s] = 0;
        prev[s] = s;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {

            Integer current = queue.poll();

            if (current == f) {
                break;
            }

            for (Integer integer: nodes[current]) {
                if (prev[integer] == -1) {
                    prev[integer] = current;
                    dist[integer] = dist[current] + 1;
                    queue.add(integer);
                }
            }

        }

        System.out.println(dist[f]);
        if (dist[f] != -1) {
            int current = f;
            Stack<Integer> stack = new Stack<>();
            while (current != s) {
                stack.push(prev[current]);
                current = prev[current];
            }
            while (!stack.empty()) {
                System.out.print(stack.pop() + " ");
            }
            System.out.print(f);
        }
    }
}
