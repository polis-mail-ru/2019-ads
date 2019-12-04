package ru.mail.polis.ads;
// https://www.e-olymp.com/ru/submissions/6263587
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Problem4853 {
    private static ArrayList<ArrayList<Integer>> nodes;
    private static int[] colors;
    private static int[] res1;
    private static int[] res;


    public static void bfs(int a, int to){
        res1[a] = 0;
        res[a] = a;
        int u;
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        while (!q.isEmpty()){
            u = q.poll();
            if (u == to) {
                break;
            }

            for (int i: nodes.get(u)) {
                if (res[i] == 0){
                    res[i] = u;
                    res1[i] = res1[u]+1;
                    q.add(i);
                }
            }
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int from = in.nextInt();
        int to = in.nextInt();
        Integer a;
        Integer b;
        nodes = new ArrayList<>();
        colors = new int[n+1];
        res1 = new int[n+1];
        res = new int[n+1];
        for (int i=0; i <= n; i++){
            nodes.add(new ArrayList<>());
        }
        Arrays.fill(res1, 10000000);
        for (int i = 0; i < m; i++){
            a = in.nextInt();
            b = in.nextInt();
            nodes.get(a).add(b);
            nodes.get(b).add(a);
        }

        bfs(from, to);
        if (res1[to] == 10000000 ||res1[to] == 0){
            out.print(-1);
        }
        else{
           // out.println(Arrays.toString(res));
            out.println(res1[to]);
            int curr = res[to];
            Stack<Integer> stack = new Stack<>();
            stack.push(res[to]);
            while (curr != from) {
                stack.push(res[curr]);
                curr = res[curr];
            }
            while (!stack.empty()) {
                out.print(stack.pop() + " ");
            }
            out.print(to);

        }
        out.flush();
    }
}