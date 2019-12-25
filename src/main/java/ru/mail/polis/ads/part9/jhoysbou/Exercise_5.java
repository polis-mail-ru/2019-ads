package ru.mail.polis.ads.part9.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// Submission here https://www.e-olymp.com/ru/submissions/6413392
// Literally the same code

public class Exercise_5 {
    private static class Node {
        int index;

        Node(int index) {
            this.index = index;
        }
    }

    private static final int NO_PATH = -1;
    private static List<List<Node>> graph;
    private static int n;
    private static int m;
    private static int s;
    private static int f;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        s = scanner.nextInt();
        f = scanner.nextInt();

        graph = new ArrayList<>(n + 1);
        int[] distance = new int[n + 1];
        int[] previous = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = NO_PATH;
            previous[i] = NO_PATH;
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            graph.get(start).add(new Node(end));
            graph.get(end).add(new Node(start));
        }

        Queue<Integer> queue = new LinkedList<>();

        distance[s] = 0;
        previous[s] = s;
        queue.add(s);

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            if (currentIndex == f) {
                break;
            }

            for (Node subElement : graph.get(currentIndex)) {
                int subIndex = subElement.index;

                if (previous[subIndex] == NO_PATH) {
                    previous[subIndex] = currentIndex;
                    distance[subIndex] = distance[currentIndex] + 1;
                    queue.add(subElement.index);
                }

            }
        }

        System.out.println(distance[f]);

        if (distance[f] == NO_PATH) {
            return;
        }

        List<Integer> result = new LinkedList<>();

        while (f != s) {
            result.add(0, f);
            f = previous[f];
        }

        result.add(0, s);

        for(Integer element : result) {
            System.out.print(element + " ");
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
