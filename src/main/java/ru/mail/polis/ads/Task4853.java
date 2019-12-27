package ru.mail.polis.ads;
import java.util.*;

public class Task4853 {
    private static final int BIG_INT = Integer.MAX_VALUE;
    static class Rib {
        private int color;
        private int id;
        private List<Rib> links;
        private List<Integer> weights;

        Rib(int color, int id) {
            this.color = color;
            this.id = id;
            this.links = new ArrayList<>();
            this.weights = new ArrayList<>();
        }

    }

    private static int[] distance;
    private static int[] parents;

    private static void solve(){
        final Scanner in = new Scanner(System.in);
        StringBuilder out = new StringBuilder();

        int n = in.nextInt();
        int m = in.nextInt();
        int fromNode = in.nextInt();
        int toNode = in.nextInt();

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
            int weight = in.nextInt();

            graph[from].links.add(graph[to]);
            graph[from].weights.add(weight);

            graph[to].links.add(graph[from]);
            graph[to].weights.add(weight);
        }

        workWithGraph(graph[fromNode]);

        if (distance[toNode] == BIG_INT) {
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
            out.append(path.pop() + " ");
        }
        System.out.println(out);
    }

    private static void workWithGraph(Rib from) {
        from.color = 1;
        Queue<Rib> links = new ArrayDeque<>();
        for (int i = 0; i < from.links.size(); i++) {
            Rib to = from.links.get(i);
            int weight = from.weights.get(i);
            if (distance[to.id] > distance[from.id] + weight) {
                distance[to.id] = distance[from.id] + weight;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                links.add(to);
            }
        }
        from.color = 2;
        while (!links.isEmpty()) {
            workWithGraph(links.remove());
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
