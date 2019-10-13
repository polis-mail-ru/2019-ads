package ru.mail.polis.ads.part1.ya111;

import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5809467
public final class problem5327 {
    private problem5327(){
    }

    public static void main(final String[] argc) {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        Stack<Integer> stack = new Stack<>();
        boolean err = false;
        for (int i = 0; i<string.length(); ++i) {
            if (string.charAt(i) == '(')
                stack.push(1);
            if (string.charAt(i) == ')')
                if (stack.empty() != true)
                    stack.pop();
                else {
                    err=true;
                    break;
                }
        }
        if (err == false && stack.empty() == true)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
