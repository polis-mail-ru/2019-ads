package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Task4 {
    private static List<Integer> adjListArray[];
    private static List<Integer> adjListArrayRev[];
    private static int orderForRevGraph[];
    private static int curLabel;
    private static int inWhichSccVertex[];


    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        adjListArray = new LinkedList[n + 1];
        adjListArrayRev = new LinkedList[n + 1];
        inWhichSccVertex = new int[n + 1];
        orderForRevGraph = new int[n];

        for (int i = 1; i <= n; i++) {
            adjListArray[i] = new LinkedList<>();
            adjListArrayRev[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; i++) {
            int b = fs.nextInt();
            int e = fs.nextInt();

            adjListArray[b].add(e);
            adjListArrayRev[e].add(b);
        }

        int numScc = kosaraju(n);
        boolean wasEdgeBetweenScc[][] = new boolean[numScc + 1][numScc + 1];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> list = adjListArray[i];
            for (Integer w : list) {
                int numScc1 = inWhichSccVertex[i];
                int numScc2 = inWhichSccVertex[w];
                if (numScc1 != numScc2 && !wasEdgeBetweenScc[numScc1][numScc2]) {
                    count++;
                    wasEdgeBetweenScc[numScc1][numScc2] = true;
                    wasEdgeBetweenScc[numScc2][numScc1] = true;
                }
            }
        }

        System.out.println(count);
    }

    static int kosaraju(int n) {
        boolean visited[] = new boolean[n + 1];
        topoSort(n);
        int numScc = 0;
        for (Integer a : orderForRevGraph) {
            if (!visited[a]) {
                numScc++;
                dfsScc(a, visited, numScc);
            }
        }

        return numScc;
    }

    static void topoSort(int n) {
        boolean visited[] = new boolean[n + 1];
        curLabel = n - 1;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfsTopo(i, visited);
            }
        }
    }

    static void dfsTopo(int v, boolean visited[]) {
        visited[v] = true;
        for (Integer w : adjListArrayRev[v]) {
            if (!visited[w]) {
                dfsTopo(w, visited);
            }
        }
        orderForRevGraph[curLabel] = v;
        curLabel--;
    }

    static void dfsScc(int v, boolean visited[], int numScc) {
        visited[v] = true;
        inWhichSccVertex[v] = numScc;
        for (Integer w : adjListArray[v]) {
            if (!visited[w]) {
                dfsScc(w, visited, numScc);
                inWhichSccVertex[w] = numScc;
            }
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
}
