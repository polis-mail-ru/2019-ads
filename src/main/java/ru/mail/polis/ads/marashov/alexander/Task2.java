package ru.mail.polis.ads.marashov.alexander;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Task2 {

    private static int dfs(int current, int prev, int min, char[] colors, ArrayList<LinkedList<Integer>> array, boolean[] hasCycle) {
        if (colors[current] == 'b') { // если вершина посещена ранее (цвет чёрный), то выйти
            return min;
        }

        if (colors[current] == 'g') { // если вершина посещена этим заходом (серый цвет), то нашли цикл
            hasCycle[0] = true;
            colors[current] = 'c'; // вершина помечена цикличной
            return Math.min(current, min); // вернуть минимум текущего минимума и номера вершины
        }

        colors[current] = 'g'; // пометить серым, что зашли сюда

        for (Integer integer: array.get(current)) { // перебираем все рёбра из вершины
            if (hasCycle[0]) { // если уже найден цикл, то дальше никуда не идём
                break;
            }
            if (integer == prev) { // обратно - тоже не идём
                continue;
            }
            min = Math.min(min, dfs(integer, current, min, colors, array, hasCycle)); // проходимся по доступным вершинам
        }

        if (colors[current] != 'c') {
            colors[current] = 'b'; // "выходим" из вершины и красим в чёрный цвет
        }

        if (colors[current] == 'c') { // если вершина отмечена окончанием найденного цикла, то выводим ответ
            System.out.println("Yes");
            System.out.println(Math.min(current, min));
            return min;
        }

        if (hasCycle[0]) {
            return Math.min(current, min);
        }

        return min;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int n = in.nextInt();
        ArrayList<LinkedList<Integer>> array = new ArrayList<>(n);
        char[] colors = new char[n + 1];
        for (int i = 0; i <= n; ++i) {
            array.add(new LinkedList<>());
            colors[i] = 'w';
        }

        final int m = in.nextInt();
        for (int i = 1; i <= m; ++i) {
            final int first = Integer.parseInt(in.next());
            final int second = Integer.parseInt(in.next());
            array.get(first).add(second);
            array.get(second).add(first);
        }

        boolean[] hasCycle = new boolean[1];
        for (int i = 1; i <= n; ++i) {
            dfs(i, -1, n + 1, colors, array, hasCycle);
            if (hasCycle[0]) {
                return;
            }
        }

        System.out.println("No");

    }
}
