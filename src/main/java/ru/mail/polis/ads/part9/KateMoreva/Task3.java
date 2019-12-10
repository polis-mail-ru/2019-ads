package ru.mail.polis.ads.part9.KateMoreva;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 1453 "Форд-Беллман"

public class Task3 {

    private static class Node {
        int start;
        int end;
        int weight;

        Node(){
        }
    }

    public static void main(String[] args){
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        Node[] mass = new Node[m + 1];
        int[] answer = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            answer[i] = 30000;
        }
        answer[1] = 0;

        for (int i = 1; i <= m; i++) {
            mass[i] = new Node();
            mass[i].start = Integer.parseInt(in.next());
            mass[i].end = Integer.parseInt(in.next());
            mass[i].weight = Integer.parseInt(in.next());
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                if (answer[mass[j].start] == 30000) continue;
                answer[mass[j].end] = Math.min(answer[mass[j].end], mass[j].weight + answer[mass[j].start]);
            }
        }
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        for (int i = 1; i <= n; i++) {
            out.print(answer[i] + " ");
        }
        out.flush();
    }
}
