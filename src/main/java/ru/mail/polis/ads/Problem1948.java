package ru.mail.polis.ads;
// https://www.e-olymp.com/ru/submissions/6262649
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Problem1948 {
    private static ArrayList<ArrayList<Integer>> nodes;
    private static int[] colors;
    private static Stack<Integer> res;


    public static void sort(int a){

        if (colors[a] == 1){
            res.add(-1);
            return;
        }
        if (colors[a] == 0){
            colors[a] = 1;
            for (int i: nodes.get(a)) {
                sort(i);
            }
            res.add(a);
            colors[a] = 2;

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
            Integer a;
            Integer b;
            nodes = new ArrayList<>(n+1);
            for (int i = 0; i <= n; ++i) {
                nodes.add(new ArrayList<>());
        }
            colors = new int[n+1];
            res = new Stack<>();
            for (int i = 0; i < m; i++){
                a = in.nextInt();
                b = in.nextInt();
                nodes.get(a).add(b);
            }


            for (int i = 1; i <= n; i++){
                sort(i);
            }
        try{
            if (res.contains(-1)){
                out.println(-1);
            }else {

                while (res.size() > 1) {
                    out.print(res.pop() + " ");
                }
                if (!res.isEmpty())
                    out.print(res.pop());
                }
            }catch (Exception e){
            out.print("ss");
        }
            out.flush();
        }
    }
