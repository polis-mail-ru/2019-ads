package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

    private static int n;
    private static int m;
    private static int[] distance;
    private static Link[] links;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] initialData = br.readLine().split(" ");
        n = Integer.parseInt(initialData[0]);
        m = Integer.parseInt(initialData[1]);
        distance = new int[n];
        Arrays.fill(distance, 30000);
        distance[0] = 0;
        links = new Link[m];
        for (int i = 0; i < m; i++) {
            initialData = br.readLine().split(" ");
            if (initialData[0].equals(initialData[1])) {
                continue;
            }
            links[i] = new Link(
                Integer.parseInt(initialData[0]),
                Integer.parseInt(initialData[1]),
                Integer.parseInt(initialData[2])
            );
        }

        while (true) {
            boolean isChanged = false;
            for (int j = 0; j < m; j++) {
                if (distance[links[j].from - 1] < 30000 && distance[links[j].to - 1] >  distance[links[j].from - 1] + links[j].weight) {
                    distance[links[j].to - 1] = distance[links[j].from - 1] + links[j].weight;
                    isChanged = true;
                }
            }
            if (!isChanged) {
                break;
            }
        }
//        System.out.print("0 ");
        for (int i = 0; i < n; i++) {
            System.out.print(distance[i] + " ");
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}