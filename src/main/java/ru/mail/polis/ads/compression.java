package ru.mail.polis.ads;

import java.util.Scanner;

class compression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();
        String total = "";
        if (n < 4){
            System.exit(0);
        }

        for (int i = 0; i < n - 1; i++){
            int counter = 0;
            for (int j = i; j < n - 1; j++){
                if (s.charAt(j) == s.charAt(i)){
                    counter++;
                } else {
                    break;
                }
            }
        }
        System.out.println(total);
    }
}
