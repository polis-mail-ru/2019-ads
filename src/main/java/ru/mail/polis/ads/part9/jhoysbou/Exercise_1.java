package ru.mail.polis.ads.part9.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//Submission here https://www.e-olymp.com/ru/submissions/6409590

public class Exercise_1 {

    private static class Vertice {
        COLORS color;
        int number;
        List<Vertice> edges;

        Vertice(int number) {
            this.color = COLORS.WHITE;
            this.number = number;
            edges = new ArrayList<>();
        }

    }

    private enum COLORS {
        WHITE, GRAY, BLACK
    }

    private static Vertice[] verticeArray;
    private static ArrayList<Integer> sorted = new ArrayList<>();


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        verticeArray = new Vertice[n + 1];
        for (int i = 1; i <= n; i++) {
            verticeArray[i] = new Vertice(i);
        }

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            verticeArray[from].edges.add((verticeArray[to]));
        }

        for (int i = 1; i < verticeArray.length; i++) {
            dfs(verticeArray[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            System.out.print(sorted.get(i) + " ");
        }


    }

    private static void dfs(Vertice element) {
        if (element.color == COLORS.BLACK) {
            return;
        }

        if (element.color == COLORS.GRAY) {
            System.out.println(-1);
            System.exit(0);
        }

        element.color = COLORS.GRAY;

        for (Vertice subElement: element.edges) {
            dfs(subElement);
        }

        element.color = COLORS.BLACK;
        sorted.add(element.number);
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
