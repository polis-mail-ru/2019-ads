package ru.mail.polis.ads;
import java.io.*;
import java.util.*;

public class Task2022 {

    private static ArrayList<ArrayList<Integer>> nodes;
    private static boolean[] cycle;
    private static byte[] used;
    private static int[] prev;

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int min = Integer.MAX_VALUE;
        int n = in.nextInt();
        int m = in.nextInt();
        nodes = new ArrayList<>(n + 1);
        used = new byte[n + 1];
        cycle = new boolean[n + 1];
        prev = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            nodes.get(from).add(to);
            nodes.get(to).add(from);

        }

        for (int i = 1; i < nodes.size(); i++) {
            if (used[i] == 0) {
                Deque<Integer> stack = new ArrayDeque<>();
                stack.addFirst(i);

                while (!stack.isEmpty()) {
                    int tmp = stack.peek();
                    used[tmp] = 1;
                    boolean nonCycle = false;

                    for (int subNode : nodes.get(tmp)) {
                        if (prev[tmp] == subNode || used[subNode] == 2) {
                            continue;
                        }
                        if (used[subNode] == 0) {
                            prev[subNode] = tmp;
                            stack.addFirst(subNode);
                            nonCycle = true;
                            break;
                        } else if (used[subNode] == 1) {
                            int j = tmp;
                            cycle[subNode] = true;
                            if (cycle[j]) {
                                continue;
                            }
                            while (j != subNode) {
                                cycle[j] = true;
                                j = prev[j];
                                if (cycle[j]) {
                                    break;
                                }
                            }
                        }
                    }
                    if (!nonCycle) {
                        stack.pop();
                        used[tmp] = 2;
                    }
                }
            }
        }

        for (int i = 0; i < cycle.length; i++) {
            if (cycle[i] && min > i) {
                min = i;
            }
        }

        if (min == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(min);
        }
        out.flush();
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