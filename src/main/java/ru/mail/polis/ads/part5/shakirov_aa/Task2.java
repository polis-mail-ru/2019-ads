package ru.mail.polis.ads.part5.shakirov_aa;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int k = fs.nextInt();

        List<Integer> edgesList[] = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            edgesList[i] = new LinkedList<>();
        }

        for (int i = 0; i < k; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            edgesList[a].add(b);
            edgesList[b].add(a);
        }

        boolean isBlack[] = new boolean[n + 1];

        int answer = cycleSearch(n, edgesList, isBlack);
        if (answer > 0) {
            System.out.println("Yes");
            System.out.println(answer);
        } else {
            System.out.println("No");
        }
    }

    private static int cycleSearch(int n, List<Integer> edgesList[], boolean isBlack[]) {
        for (int i = 1; i <= n; i++) {
            boolean isCycle = dfsCycle(i, i, i, edgesList, isBlack.clone());
            if (isCycle) {
                return i;
            }
        }

        return -1;
    }

    private static boolean dfsCycle(int prev, int current, int endV, List<Integer> edgesList[], boolean isBlack[]) {
        if (current != endV) {
            isBlack[current] = true;
        } else if (prev != endV) {
            return true;
        }

        for (Integer w : edgesList[current]) {
            if (isBlack[w] == false && w != prev) {
                boolean isCycle = dfsCycle(current, w, endV, edgesList, isBlack);
                if (isCycle) {
                    return true;
                }
            }
        }


        return false;
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
