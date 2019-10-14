package main.java.ru.mail.polis.ads.part2.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858899
 */

public class Task3 {

    private static int position;
    private static int maxLvl;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        String str;
        StringBuilder[] sb;

        for (int i = 0; i < t; i++) {
            str = in.next();
            maxLvl = 0;
            position = str.length() - 1;
            sb = new StringBuilder[(str.length() / 2) + 1];
            createTree(str, 0, sb);

            for (int j = maxLvl - 1; j >= 0; j--) {
                sb[maxLvl].append(sb[j]);
            }

            System.out.println(sb[maxLvl].toString());
        }
    }

    private static void createTree(String str, int level, StringBuilder[] builderArray) {
        if (builderArray[level] == null) {
            builderArray[level] = new StringBuilder();
        }

        builderArray[level].append(str.charAt(position));
        position--;
        maxLvl = Math.max(level, maxLvl);

        if (str.charAt(position + 1) >= 'A' && str.charAt(position + 1) <= 'Z') {
            createTree(str, level + 1, builderArray);
            createTree(str, level + 1, builderArray);
        }
    }
}
