package ru.mail.polis.ads.part5.KateMoreva;

import java.util.Scanner;
import static java.lang.Math.floor;
import static java.lang.Math.max;

//e-olymp problem 3969 "Дипломы"

public class Task2 {
    private Task2(){
    }
    public static void main(String[] args) {
        final Scanner input = new Scanner(System.in);
        final double w = input.nextLong();
        final double h = input.nextLong();
        final double n = input.nextLong();

        double left = 0;
        double right = n * max(w, h);
        do {
            double center = floor((left + right) / 2);
            final double counter = floor(center / w) * floor(center / h);
            if (counter > n) {
                right = center;
            } else if (counter == n) {
                double answer = center;
                while (floor(answer / w) * floor(answer / h) == n) {
                    answer -= 1;
                }
                System.out.println(String.format("%.0f", answer + 1));
                return;
            } else {
                left = center;
            }
        } while (right - left > 1);
        System.out.println(String.format("%.0f", right));
    }
}
