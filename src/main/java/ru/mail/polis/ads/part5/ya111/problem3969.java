import java.util.*;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class problem3969 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        int n = sc.nextInt();
        long left = max(w, h);
        long right = n * left;
        while (right > left) {
            long middle = (left + right) / 2;
            long v = (middle / w) * (middle / h);

            if (right <= v)
                right = middle;
            else
                left = middle + 1;
        }

        System.out.println(left);

    }

}