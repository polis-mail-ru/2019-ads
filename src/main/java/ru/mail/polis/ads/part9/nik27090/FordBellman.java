package ru.mail.polis.ads.part9.nik27090;

import java.io.InputStreamReader;
import java.util.Scanner;

//  Задача: Форд-Беллман
//  Решение: https://www.e-olymp.com/ru/submissions/6351935

public class FordBellman {
    private static Edge[] graph;

    static class Edge {
        int input;
        int output;
        int weight;
        Edge(int input, int output, int weight){
            this.input=input;
            this.output=output;
            this.weight=weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        graph = new Edge[m+1];
        for (int i = 1; i <= m ; i++) {
            graph[i]= new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        int[] result = new int[n+1];
        for (int i = 2; i <=n ; i++) {
            result[i]=30000;
        }
        int[] res = bellMan(result);

        for (int i = 1; i <=n ; i++) {
        System.out.print(res[i]+" ");
        }
    }

    public static int[] bellMan(int[] result){
        for(int i = 1; i < result.length-1; i++){
            for (int j = 1; j <= graph.length-1 ; j++) {
                int in = graph[j].input;
                int out = graph[j].output;
                int weight = graph[j].weight;
                if (result[in]!=30000){
                    int min = Math.min(result[out], result[in]+weight);
                        result[out]=min;
                    }
                }
            }
        return result;
    }
}


