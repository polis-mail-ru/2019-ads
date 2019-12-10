package ru.mail.polis.ads.suhova.part9;

import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 3: https://www.e-olymp.com/ru/submissions/6237538
 */
public class Task1453 {
    private static int[] d;
    private static ArrayList<ArrayDeque<E>> s;
    private static ArrayDeque<Integer> q = new ArrayDeque<>();

    private static class E{
        Integer end,w;
        E(Integer end, Integer w){
            this.end = end;
            this.w = w;
        }
    }
    public static void main(final String[] arg) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        d = new int[n + 1];
        for (int i = 2; i <=n ; i++) {
            d[i]=30000;
        }
        s = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {
            s.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int w = in.nextInt();
            s.get(start).push(new E(end,w));
        }

        q.addLast(1);
        while(!q.isEmpty()){
            int node = q.pollFirst();
            for(E e: s.get(node)){
                int newD = d[node]+e.w;
                if (newD<d[e.end]) {
                    d[e.end] = newD;
                    q.addLast(e.end);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(d[i]+" ");
        }
    }
}