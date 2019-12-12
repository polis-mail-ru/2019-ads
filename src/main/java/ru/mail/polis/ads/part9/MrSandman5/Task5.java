package part9;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/6315765

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), s = in.nextInt(), f = in.nextInt();
        s--; f--;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++)
        {
            int a = in.nextInt(), b = in.nextInt();
            a--; b--;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n, -1));
        ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(n, -1));
        Queue<Integer> q = new LinkedList<>();

        dist.set(s, 0);
        q.add(s);

        while (!q.isEmpty())
        {
            int v = q.peek(); q.remove();
            for (int next : graph.get(v))
            {
                if (dist.get(next) == -1)
                {
                    q.add(next);
                    dist.set(next, dist.get(v) + 1);
                    parent.set(next, v);
                }
            }
        }

        if (dist.get(f) == -1) {
            out.println(-1);
        }
        else
        {
            out.println(dist.get(f));
            ArrayList<Integer> path = new ArrayList<>();
            int curr = f;
            while (curr != s)
            {
                path.add(curr);
                curr = parent.get(curr);
            }
            path.add(s);
            Collections.reverse(path);
            for (int v: path)
            {
                out.print((v+1) + " ");
            }
            out.println();
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}

