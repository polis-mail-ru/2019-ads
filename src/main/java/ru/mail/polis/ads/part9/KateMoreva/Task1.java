package ru.mail.polis.ads.part9.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.*;

//e-olymp problem 1948 "Топологическая сортировка"

public class Task1 {

    private enum Colors {
        BLACK, WHITE, GREY
    }
    private static List<Integer> array;
    private static Colors[] colors;

    private static void topologicalSort(List<List<Integer>> graph, int node) {

            colors[node] = Colors.GREY;
            for(Integer ends : graph.get(node)) {
                if (colors[ends] == Colors.WHITE){
                    topologicalSort(graph, ends);
                } else if (colors[ends] == Colors.GREY){
                    System.out.println(-1);
                    System.exit(0);
                }
            }
            colors[node] = Colors.BLACK;
            array.add(0, node);
        }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        List<List<Integer>> graph;
        String[] string =in.nextLine().split(" ");
        int n = Integer.parseInt(string[0]);
        int m = Integer.parseInt(string[1]);
        array = new LinkedList<>();
        colors = new Colors[n +1];
        for (int i = 0; i < n +1; i++){
            colors[i] = Colors.WHITE;
        }
        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            string = in.nextLine().split(" ");
            int b = Integer.parseInt(string[0]);
            Integer a = Integer.parseInt(string[1]);
            graph.get(b).add(a);
        }
        for (int i = 1; i < graph.size(); i++) {
            if (colors[i] == Colors.WHITE)
                topologicalSort(graph, i);
        }

        for (Integer node : array) {
            out.print(node + " ");
        }
        out.flush();
    }
}
