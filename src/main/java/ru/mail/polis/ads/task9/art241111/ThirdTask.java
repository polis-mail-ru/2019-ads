package ru.mail.polis.ads.task9.art241111;

        import java.util.Scanner;

public class ThirdTask {

    private final static int INITIAL_VALUE = 30000;
    private static class Rib {
        int beginningOfRibs;
        int endOfTheRibs;
        int weight;
        Rib() {}
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        int[] answer = new int[n + 1];
        Rib[] graph = new Rib[m+1];

        for (int i = 2; i <= n; i++) {
            answer[i] = INITIAL_VALUE;
        }

        for (int i = 1; i <= m; i++) {
            graph[i] = new Rib();
            graph[i].beginningOfRibs = Integer.parseInt(in.next());
            graph[i].endOfTheRibs = Integer.parseInt(in.next());
            graph[i].weight = Integer.parseInt(in.next());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (answer[graph[j].beginningOfRibs] != INITIAL_VALUE) {
                    int min = Math.min(answer[graph[j].endOfTheRibs],
                            graph[j].weight + answer[graph[j].beginningOfRibs]);
                    answer[graph[j].endOfTheRibs] = min;
                }

            }
        }

        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            out.append(answer[i]).append(" ");
        }
        System.out.println(out);
    }
}