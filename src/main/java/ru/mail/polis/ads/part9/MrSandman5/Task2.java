package part9;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/6329656

public class Task2 {

    private enum color{
        WHITE,
        GREY,
        BLACK,
        UNKNOWN
    }
    private static PriorityQueue<Integer>[] graph;
    private static ArrayList<color> colors;
    private static int[] parent;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new PriorityQueue[n + 1];
        colors = new ArrayList<>(Collections.nCopies(n + 1, color.WHITE));
        parent = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new PriorityQueue<>(10);
            parent[i] = -1;
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        int min = dfs(n);

        if (min == n + 1) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(min);
        }

    }

    private static int dfs (int n) {
        Deque<Integer> stack = new ArrayDeque<>();
        int min = n + 1;
        for (int i = 1; i < n + 1; i++) {
            if (min < i + 1) {
                break;
            }
            boolean hasCycle = false;
            if (colors.get(i) == color.WHITE) {
                stack.push(i);
            }

            while (!stack.isEmpty()) {
                Integer current = stack.peek();

                if (colors.get(current) == color.UNKNOWN) {
                    hasCycle = false;
                    min = Math.min(current, min);
                }
                colors.set(current, color.GREY);

                boolean canContinue = false;
                while(!graph[current].isEmpty()) {
                    if (hasCycle) {
                        break;
                    }
                    try {
                        int next = graph[current].poll();
                        if (next == parent[current]) {
                            continue;
                        }

                        if (colors.get(next) == color.GREY) {
                            hasCycle = true;
                            colors.set(next, color.UNKNOWN);
                            break;
                        }

                        if (colors.get(next) == color.WHITE) {
                            parent[next] = current;
                            stack.push(next);
                            canContinue = true;
                            break;
                        }
                    } catch (NullPointerException ignored) {}
                }

                if (canContinue) {
                    continue;
                }

                if (hasCycle) {
                    min = Math.min(current, min);
                }

                if (colors.get(current) != color.WHITE) {
                    colors.set(current, color.BLACK);
                    stack.pop();
                }
            }
        }
        return min;
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
