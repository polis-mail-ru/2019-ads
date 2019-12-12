package part9;

import java.io.*;
import java.util.*;

//https://www.e-olymp.com/ru/submissions/6315867

public class Task4 {

    private static class Vertex implements Comparable<Vertex>{
        int v, dist;

        public Vertex(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    private static class Edge{
        int begin, weight;

        public Edge(int begin, int weight) {
            this.begin = begin;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), s = in.nextInt(), f = in.nextInt();
        s--; f--;
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < m; i++)
        {
            int a = in.nextInt(), b = in.nextInt(), w = in.nextInt();
            a--; b--;
            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        ArrayList<Integer> dist = new ArrayList<>(Collections.nCopies(n, -1));
        ArrayList<Integer> parent = new ArrayList<>(Collections.nCopies(n, -1));
        Queue<Vertex> q = new PriorityQueue<>();

        dist.set(s, 0);
        q.add(new Vertex(s, 0));

        while (!q.isEmpty())
        {
            Vertex curr = q.peek(); q.remove();
            if (curr.dist > dist.get(curr.v)) continue;
            for (Edge next : graph.get(curr.v))
            {
                if (dist.get(next.begin) == -1 || dist.get(next.begin) > curr.dist + next.weight )
                {
                    dist.set(next.begin, curr.dist + next.weight);
                    q.add(new Vertex(next.begin, dist.get(next.begin)));
                    parent.set(next.begin, curr.v);
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