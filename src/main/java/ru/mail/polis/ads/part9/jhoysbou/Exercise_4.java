package ru.mail.polis.ads.part9.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// Submission here https://www.e-olymp.com/ru/submissions/6412301

public class Exercise_4 {

    private static class Node {
        int index;
        int distance;

        Node(int distance, int index) {
            this.distance = distance;
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

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int weight = scanner.nextInt();

            graph.get(start).add(new Node(weight, end));
            graph.get(end).add(new Node(weight, start));
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparing(element -> element.distance));

        distance[s] = 0;
        queue.add(new Node(0, s));
        boolean[] usedElements = new boolean[n + 1];
        int[] previous = new int[n + 1];

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll().index;

            if (usedElements[currentIndex]) {
                break;
            }

            usedElements[currentIndex] = true;

            for (Node subElement : graph.get(currentIndex)) {
                int subIndex = subElement.index;
                int subDistance = subElement.distance;

                if (distance[subIndex] > distance[currentIndex] + subDistance) {

                    distance[subIndex] = distance[currentIndex] + subDistance;
                    queue.add(new Node(distance[subIndex], subIndex));
                    previous[subIndex] = currentIndex;

                }

            }
        }

        if (distance[f] != Integer.MAX_VALUE) {
            System.out.println(distance[f]);
        } else {
            System.out.println(NO_PATH);
        }

        List<Integer> result = new LinkedList<>();

        while (previous[f] != 0) {
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
