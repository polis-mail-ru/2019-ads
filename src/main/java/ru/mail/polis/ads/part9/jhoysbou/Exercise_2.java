package ru.mail.polis.ads.part9.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Exercise_2 {

    private enum COLORS {
        WHITE, GRAY, BLACK
    }

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

    private static Set<Integer> cicled = new HashSet<>();
    private static Vertices[] verticesArray;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int n = scanner.nextInt();
        final int k = scanner.nextInt();
        verticesArray = new Vertices[n + 1];
        for (int i = 1; i <= n; i++) {
            verticesArray[i] = new Vertices(i);
        }

        for (int i = 0; i < k; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            verticesArray[from].edges.add((verticesArray[to]));
            verticesArray[to].edges.add((verticesArray[from]));
        }


        if (cicled.isEmpty()) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(
                    cicled
                    .stream()
                    .min(Integer::compareTo)
            );
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
