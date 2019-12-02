package ru.mail.polis.ads.part9.bogdanmendli;

import java.util.Arrays;
import java.util.Scanner;

public class ThirdTask {

    private static class Link {
        int from;
        int to;
        int weight;

        public Link(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ;

        int[] distance = new int[n + 1];
        Arrays.fill(distance, 30000);
        distance[1] = 0;

        Link[] links = new Link[m];
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            links[i] = new Link(from, to, weight);
        }

        while (true) {
            boolean isChanged = false;
            for (int j = 0; j < m; j++) {
                if (distance[links[j].from] < 30000 && distance[links[j].to] > distance[links[j].from] + links[j].weight) {
                    distance[links[j].to] = distance[links[j].from] + links[j].weight;
                    isChanged = true;
                }
                if (distance[links[j].from] < 30000) {
                    distance[links[j].to] = Math.min(distance[links[j].to], distance[links[j].from] + links[j].weight);
                }
            }
            if (!isChanged) {
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(distance[i] + " ");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}