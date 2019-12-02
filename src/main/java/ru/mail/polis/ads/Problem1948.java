package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Problem1948 {
    private static Node[] nodes;
    private static int[] colors;
    private static Stack<Integer> res;


    public static void sort(int a){

        if (colors[a] == 1){
            res.add(-1);
        }
        if (colors[a] == 0){
            colors[a] = 1;
            if (nodes[a] != null){
                for (int i:nodes[a].childrens) {
                    sort(i);
                }
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
    public static class Node{
        LinkedList<Integer> childrens;

        Node(){
            childrens = new LinkedList<>();
        }
        void add(Integer a){
            childrens.add(a);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
            int n = in.nextInt();
            int m = in.nextInt();
            Integer a;
            Integer b;
            nodes = new Node[n+1];
            colors = new int[n+1];
            res = new Stack<>();
            for (int i = 0; i < m; i++){
                a = in.nextInt();
                b = in.nextInt();
                if (nodes[a] == null){
                    nodes[a] = new Node();
                }
                nodes[a].add(b);
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
