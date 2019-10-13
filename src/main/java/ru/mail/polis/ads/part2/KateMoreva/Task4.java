package ru.mail.polis.ads.part2.KateMoreva;

import java.util.LinkedList;
import java.util.Scanner;

//e-olymp problem 5327 "Скобочные последовательности"

public class Task4 {
    private Task4(){
    }

    public static void main(final String[] arg){
        final Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        LinkedList<Character> list = new LinkedList<>();
        if (str.isEmpty()) {
            System.out.println("NO");
            return;
        }

        for (char elem : str.toCharArray()) {
            if (elem == '(') list.push(elem);
            else {
                if (!list.isEmpty()) list.pop();
                else {
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
            return;
        }

    }

}
