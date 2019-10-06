package ru.mail.polis.ads.valer1435.part1;
// https://www.e-olymp.com/ru/submissions/5789638
import java.util.*;

public class Problem1618 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String s1 = in.nextLine();
        String b = in.nextLine();
        String s2 = in.nextLine();
        ArrayList<String> minS;
        ArrayList<String> maxS;
        if (s2.length() > s1.length()) {
            minS = new ArrayList<String>(Arrays.asList(s1.split(" ")));
            maxS = new ArrayList<String>(Arrays.asList(s2.split(" ")));
        } else {
            minS = new ArrayList<String>(Arrays.asList(s2.split(" ")));
            maxS = new ArrayList<String>(Arrays.asList(s1.split(" ")));
        }
        Map<Integer, Integer> d = new TreeMap<>();
        d.put(0, -1);
        for (String max : maxS) {
            ArrayList<Integer> lst = new ArrayList<>(d.keySet());
            ArrayList<Integer> counts = new ArrayList<>();
            for (int k = 0; k < minS.size(); k++) {
                if (minS.get(k).equals(max)) {
                    counts.add(k);
                }
            }
            for (Integer k : counts) {
                for (Integer j : lst) {
                    if (k > d.get(j)) {
                        Integer ind = d.get(j + 1);
                        if (ind != null) {
                            if (k < d.get(j + 1)) {
                                d.put(j + 1, k);
                            }
                        } else {
                            d.put(j + 1, k);
                            break;
                        }
                    }
                }
            }
        }
        ArrayList<Integer> lst = new ArrayList<>(d.keySet());
        Collections.reverse(lst);
        System.out.println(lst.get(0));
    }
}