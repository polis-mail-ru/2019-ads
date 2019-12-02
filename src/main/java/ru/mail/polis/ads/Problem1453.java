package ru.mail.polis.ads;

// https://www.e-olymp.com/ru/submissions/6231764
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem1453 {
    static int[] res;
    static int[][] edges;

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int a;
        int b;
        int c;
        res = new int[n+1];
        Arrays.fill(res, 30000);
        res[1] = 0;
        edges = new int[m][3];
        for (int i = 0; i < m; i++){
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = c;
        }

        for (int i = 0; i < n; i++){
            for (int k = 0; k < m; k++){
                if (res[edges[k][0]] != 30000 && res[edges[k][0]] + edges[k][2] < res[edges[k][1]]){
                    res[edges[k][1]] = res[edges[k][0]] + edges[k][2];
                }
            }
        }
        for (int i = 1; i < res.length-1; i++){
            out.print(res[i]+" ");
        }
        out.print(res[res.length-1]);
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
