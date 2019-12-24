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

    private static class Vertices {
        COLORS color;
        int number;
        List<Vertices> edges;

        Vertices(int number) {
            this.color = COLORS.WHITE;
            this.number = number;
            edges = new ArrayList<>();
        }

    }

    private enum COLORS {
        WHITE, GRAY, BLACK
    }

    private static Vertices[] verticesArray;
    private static ArrayList<Integer> sorted = new ArrayList<>();


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        verticesArray = new Vertices[n + 1];
        for (int i = 1; i <= n; i++) {
            verticesArray[i] = new Vertices(i);
        }

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            verticesArray[from].edges.add((verticesArray[to]));
        }

        for (int i = 1; i < verticesArray.length; i++) {
            dfs(verticesArray[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            System.out.print(sorted.get(i) + " ");
        }


    }

    private static void dfs(Vertices element) {
        if (element.color == COLORS.BLACK) {
            return;
        }

        if (element.color == COLORS.GRAY) {
            System.out.println(-1);
            System.exit(0);
        }

        element.color = COLORS.GRAY;

        for (Vertices subElement: element.edges) {
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
