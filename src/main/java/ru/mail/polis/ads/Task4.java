package ru.mail.polis.ads;

import java.util.*;

public class Task4 {
    public static void main(String[] argc) {
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        LinkedList<Character> list = new LinkedList<>();
        for (char c : string.toCharArray()) {
            if (c == '(') {
                list.push(c);
            }
            if (c == ')') {
                if (!list.isEmpty()) {
                    list.pop();
                } else {
                    System.out.println("NO");
                    return;
                }
            }

        }
        if (!list.isEmpty()) {
            System.out.println("NO");
            return;
        } else {
            System.out.println("YES");
        }
    }
}

