package ru.mail.polis.ads;

import java.util.Scanner;

class DZ2_ThirdTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        check(s);
    }

    private static void check(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(') {
                counter++;
            } else {
                counter--;
            }
        }
        if (counter == 0){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
