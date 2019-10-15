package ru.mail.polis.ads;

import java.util.ArrayList;
import java.util.Scanner;

class DZ2_Five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int index;
        String expr;
        ArrayList<String> arr = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            expr = scanner.next();
            for (int i = 0; i < expr.length()+1; i++){
                arr.add("");
            }
            arr.add(expr.length(), "");
            index = expr.length() - 1;
            get_levels(expr, index, 0, arr);
            System.out.println(arr);
        }
    }

    private static void get_levels(String expr, int index, int i, ArrayList<String> arr) {
        arr.set(i, String.valueOf(expr.charAt(index)));
        index--;
        if (Character.isUpperCase(expr.charAt(index + 1))) {
            get_levels(expr, index, i + 1, arr);
            get_levels(expr, index, i + 1, arr);
        }
    }
}
