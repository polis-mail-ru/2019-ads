package ru.mail.polis.ads.part1.blinkyz;

import java.util.*;

public class Problem1618 {
    private Problem1618() {

    }

    private static final class EntrancesFinder {
        private final Map<Integer, List<Integer>> entrancesCache = new WeakHashMap<>();

        private final int[] arr;

        EntrancesFinder(final int[] arr) {
            this.arr = arr;
        }

        List<Integer> find(final int ch) {
            if (entrancesCache.get(ch) != null) {
                return entrancesCache.get(ch);
            }

            final List<Integer> entrances = new ArrayList<>();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == ch) {
                    entrances.add(i);
                }
            }
            entrancesCache.put(ch, entrances);
            return entrances;
        }
    }

    private static void solve() {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = scanner.nextInt();
        }
        final int m = scanner.nextInt();
        final int[] arr2 = new int[m];
        for (int i = 0; i < m; i++) {
            arr2[i] = scanner.nextInt();
        }

        // d - это словарь, где ключ - размер подпоследовательности, а значение - индекс последнего элемента
        // при желании можно расширить логику до хранения самих элементов, но в денной задаче это не нужно
        final Map<Integer, Integer> d = new HashMap<>();
        // база динамики - размер 0, индекс последнего элемента (-1)
        d.put(0, -1);
        int maxSize = 0; // размер максимальной подпоследовательности, т.е. количество подпоследовательностей
        final EntrancesFinder entrancesFinder = new EntrancesFinder(arr2);
        for (int i = 0; i < n; i++) {
            final int ch = arr1[i];

            final List<Integer> entrances = entrancesFinder.find(ch);
            if (entrances.size() == 0) {
                continue;
            }

            int lastSize = -1; // размер последней подпоследовательности, куда мы смогли вставить вхождение элемента
            // не имеет смысла пытаться вставлять последующие вхождения элемента в подпоследовательности размера,
            // меньшего чем lastSize, так как их мы гарантированно не улучшим, так как все последующие вхождения больше
            // предыдущих вхождений (по индексу)
            for (final int curEntrancePos : entrances) {
                // size - размер текущей подпоследовательности, начинаем пытаться улучшать с максимального размера
                for (int size = maxSize; size > lastSize; size--) {
                    // curLastPos - индекс последнего элемента в этой подпоследовательности
                    // мы можем вставить элемент, только если индекс текущего вхождения элемента больше индекса
                    // последнего элемента
                    final Integer curLastPos = d.get(size);
                    if (curLastPos < curEntrancePos) {
                        final Integer nextSubLastPos = d.get(size + 1);
                        // nextSubLastPos - это индекс последнего элемента в следующей подпоследовательности, т.е.
                        // подпоследовательности размера (size + 1)
                        // если nextSubLastPos == null, то мы получили подпоследовательность большего размера,
                        // чем maxSize,
                        // иначе пытаемся улучшить текущую подпоследовательность
                        // улучшение означает, что вставив элемент в подпоследовательность размера size, мы получаем
                        // подпоследовательность размера (size + 1) с индексом последнего элемента меньшим, чем был
                        // до этого
                        if (nextSubLastPos == null) {
                            d.put(size + 1, curEntrancePos);
                            maxSize = size + 1;
                            lastSize = maxSize;
                            break;
                        } else if (curEntrancePos < nextSubLastPos) {
                            d.put(size + 1, curEntrancePos);
                            lastSize = size + 1;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(maxSize);
    }

    public static void main(final String[] arg) {
        solve();
    }
}