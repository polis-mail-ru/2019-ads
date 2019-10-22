import java.util.*;

import static java.lang.Math.sqrt;

public class Main {
    public static double function (double x) {
        return x*x+Math.sqrt(x);
    }
    public static double binarySearch (double c) {
        double right = 1e6; double left = 0; double middle = (left + right) / 2;
        while (Math.abs(right - left) > 1e-6) {
            middle = (left + right) / 2;
            if (function(middle) < c)
                left = middle;
            else
                right = middle;
        }
        return right;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double c = sc.nextDouble();
        System.out.printf("%.6f",binarySearch(c));

    }

}