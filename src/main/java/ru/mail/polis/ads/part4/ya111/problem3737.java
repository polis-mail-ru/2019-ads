import java.util.*;

public class problem3737 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long [] Array = new long[n];
        for (int i = 0; i < n; ++i) {
            Array[i] = sc.nextLong();
        }
        boolean isHeap = true;
        for (int i = 1; 2 * i <= n; ++i) {
            if (Array[i - 1] > Array[2 * i - 1])
                isHeap = false;
            if ((2 * i + 1 <= n && Array[i - 1] > Array[2 * i]))
                isHeap = false;
        }
        System.out.println(isHeap ? "YES" : "NO");

    }

}