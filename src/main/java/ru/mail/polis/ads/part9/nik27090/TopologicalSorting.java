package ru.mail.polis.ads.part9.nik27090;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

//  Задача: Топологическая сортировка
//  Решение: https://www.e-olymp.com/ru/submissions/6350363

public class TopologicalSorting {

     static ArrayList<ArrayList<Integer>> graph;
     static ArrayList<Integer> result;
     static int[] used;
     static boolean error = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        graph = new ArrayList<>();
        result =  new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int in = sc.nextInt();
            int out = sc.nextInt();
            graph.get(in).add(out);
        }
        used =  new int[n+1];
        for (int i = 1; i <= n; i++) {
            if (used[i]==0) {
                dfs(i);
            }
        }

        if (error || result.size() != n) System.out.println("-1");
        else {
            for(int i = result.size() - 1; i >= 0; i--)
                System.out.print(result.get(i) + " ");
        }
    }

     static void dfs (int vertex){
        used[vertex] = 1;
        for(int i = 0; i < graph.get(vertex).size(); i++) {
            int v = graph.get(vertex).get(i);
            if (used[v]==1){
                error = true;
                break;
            } else if (used[v]==0){
                dfs(v);
            }
        }
        used[vertex] = 2;
        result.add(vertex);
    }
}
