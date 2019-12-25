package ru.mail.polis.ads.part_9.gogun;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Task2 {

    enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static Color[] vertexColor;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int[] prev;
    private static boolean[] isCycle;

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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

    private static void dfs() {
        for (int i = 1; i < graph.size(); ++i) {
            if (vertexColor[i] == Color.WHITE) {
                LinkedList<Integer> stack = new LinkedList<>();
                stack.add(i);

                while (!stack.isEmpty()) {
                    int curr = stack.getLast();
                    vertexColor[curr] = Color.GRAY;
                    boolean check = true;

                    for (int currentNode : graph.get(curr)) {
                        if (prev[curr] == currentNode) {
                            continue;
                        }
                        if (vertexColor[currentNode] == Color.WHITE) {
                            prev[currentNode] = curr;
                            stack.add(currentNode);

                            check = false;
                            break;
                        } else if (vertexColor[currentNode] == Color.GRAY) {
                            isCycle[currentNode] = true;

                            int forNode = curr;
                            if (isCycle[forNode]) {
                                continue;
                            }

                            while (forNode != currentNode) {
                                isCycle[forNode] = true;
                                forNode = prev[forNode];
                                if (isCycle[forNode]) {
                                    break;
                                }
                            }

                        }
                    }
                    if (check) {
                        stack.removeLast();
                        vertexColor[curr] = Color.BLACK;
                    }
                }
            }
        }
    }

    private static void solve(FastScanner in, PrintWriter out) {
        int vertexNum = in.nextInt();
        int edgeNum = in.nextInt();

        for (int i = 0; i < vertexNum+1; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeNum; ++i) {
            int vertexFrom = in.nextInt();
            int vertexTo = in.nextInt();

            graph.get(vertexFrom).add(vertexTo);
            graph.get(vertexTo).add(vertexFrom);
        }

        prev = new int[vertexNum + 1];
        isCycle = new boolean[vertexNum + 1];
        vertexColor = new Color[vertexNum + 1];
        Arrays.fill(vertexColor, Color.WHITE);

        dfs();

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < isCycle.length; ++i) {
            if (isCycle[i] && min > i) {
                min = i;
            }
        }
        if (min == Integer.MAX_VALUE) {
            out.print("No");
        } else {
            out.println("Yes");
            out.print(min);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }

}
