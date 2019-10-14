package ru.mail.polis.ads.part2.atani20;

import java.util.Scanner;
public class BracketSequence {
    public static void main(String[] args){
        int open = 0;
        String str;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '(')
                open++;
            else
                open--;
            if(open < 0) {
                System.out.println("NO");
                return;
            }

        }
        if(open !=  0)
            System.out.println("NO");
        else
            System.out.println("YES");
    }
}