package ru.mail.polis.ads.part2.kuzo_liza;

import java.util.Scanner;

public class BracketSequences {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        boolean flag = false;
        int open  = 0;

        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                open++;
            else
                open--;
            if (open < 0)
                flag = true;
        }

        if (flag || (open > 0))
            System.out.println("NO");

        else
            System.out.println("YES");
    }
}
