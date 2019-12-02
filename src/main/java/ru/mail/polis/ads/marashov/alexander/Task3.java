package ru.mail.polis.ads.marashov.alexander;

import java.util.Scanner;

public class Task3 {

    private static class Node {
        int first;
        int second;
        int weight;
        Node() {}
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        int[] answer = new int[n + 1];
        Node[] mass = new Node[m+1];

        for (int i = 1; i <= n; i++) {
            answer[i] = 30000;
        }
        answer[1]=0;

        for (int i = 1; i <= m; i++) {
            mass[i] = new Node();
            mass[i].first = Integer.parseInt(in.next());
            mass[i].second = Integer.parseInt(in.next());
            mass[i].weight = Integer.parseInt(in.next());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (answer[mass[j].first] == 30000) continue;
                answer[mass[j].second]= Math.min(answer[mass[j].second], mass[j].weight+answer[mass[j].first]);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
