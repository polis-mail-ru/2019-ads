package ru.mail.polis.ads;
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.util.*;
        import java.util.stream.Collectors;


/*
8 6
1 2
2 3
3 4
4 2
1 4
4 6
*/
public class Problem2022 {
    private static ArrayList<LinkedList<Integer>> nodes;
    private static int[] colors;
    private static int[] prev;
    private static int res;
    private static LinkedList<Integer> r;


    public static void bfs(int a){
        return;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        r = new LinkedList<>();
        int n = in.nextInt();
        int m = in.nextInt();
        Integer a;
        Integer b;
        nodes = new ArrayList<>(n+1);
        for (int i = 0; i < n+1; i++){
            nodes.add(new LinkedList<>());
        }
        colors = new int[n+1];
        res = 10000001;
        for (int i = 0; i < m; i++){
            a = in.nextInt();
            b = in.nextInt();

            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }


        for (int i = 1; i <= n; i++){
            bfs(i);
        }
        if (res == 10000001){
            out.println("No");
        }else{
            out.println("Yes");
            out.println(res);
        }
        out.flush();


    }
}