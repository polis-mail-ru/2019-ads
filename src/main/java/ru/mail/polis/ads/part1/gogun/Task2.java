package ru.mail.polis.ads.part1.gogun;

import java.util.LinkedList;
import java.util.Scanner;

public final class Task2 {
    private Task2(){
    }
    /*

        На e-olymp добился только 56% со стеком
        Залью, не зря же мучался)))

     */
    public static void main(final String[] argc) {
        final Scanner scan = new Scanner(System.in);
        final String str = scan.nextLine();
        final LinkedList<Character> linkedList = new LinkedList<>();
        final StringBuilder builder = new StringBuilder();
        for(final char c : str.toCharArray()) {
            if ((c == '(') | (c == '[')) {
                linkedList.push(c);
            }
            if ((c == ']') | (c == ')')){
                final char bracket = c == ']' ? '[' : '(';
                char tmp;
                while (!linkedList.isEmpty() && (tmp = linkedList.poll()) != bracket){
                    if (tmp == '(') {
                        builder.insert(0, "(").insert(builder.length(), ")");
                    }
                    if (tmp == '[') {
                        builder.insert(0, "[").insert(builder.length(), "]");
                    }
                }
                builder.insert(0, bracket).insert(builder.length(), c);
            }
        }
        while (!linkedList.isEmpty()) {
            char a =  linkedList.pop();
            if (a == '(') {
                builder.insert(0, "(").insert(1, ")");
            }
            if (a == '[') {
                builder.insert(0, "[").insert(1,"]");
            }
        }
        System.out.println(builder);
    }
}
