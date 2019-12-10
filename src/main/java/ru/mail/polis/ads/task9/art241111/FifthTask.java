package ru.mail.polis.ads.task9.art241111;


import java.util.*;

public class FifthTask {
    private static final int BIG_INT = Integer.MAX_VALUE;
    static class Rib {
        private int color;
        private int id;
        private List<Rib> links;

        Rib(int color, int id) {
            this.color = color;
            this.id = id;
            this.links = new ArrayList<>();
        }

    }

    private static int[] distance;
    private static int[] parents;
    private static Queue<Rib> links;

    private static void solve(){
        final Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        links = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int fromNode = in.nextInt();;
        int toNode = in.nextInt();;

        Rib[] graph = new Rib[n + 1];
        distance = new int[n + 1];
        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            distance[i] = BIG_INT;
            graph[i] = new Rib(0, i);
        }

        distance[fromNode] = 0;

        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            graph[from].links.add(graph[to]);
            graph[to].links.add(graph[from]);
        }

        workWithGraph(graph[fromNode]);
        while (!links.isEmpty()) {
            workWithGraph(links.remove());
        }

        if (distance[toNode] == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }

        out.append(distance[toNode]).append("\n");

        Stack<Integer> path = new Stack<>();
        path.push(toNode);

        do {
            toNode = parents[toNode];
            path.push(toNode);
        } while (toNode != fromNode);

        while (!path.isEmpty()) {
            out.append(path.pop()).append(" ");
        }
        System.out.println(out);
    }

    private static void workWithGraph(Rib from) {
        from.color = 1;
        for (int i = 0; i < from.links.size(); i++) {
            Rib to = from.links.get(i);
            if (distance[to.id] > distance[from.id] + 1) {
                distance[to.id] = distance[from.id] + 1;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                links.add(to);
            }
        }
        from.color = 2;
    }

    public static void main(String[] args) {
            solve();
    }
}
