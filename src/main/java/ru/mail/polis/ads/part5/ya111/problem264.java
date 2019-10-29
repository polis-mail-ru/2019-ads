import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        long [] d = new long[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = sc.nextInt();
            d[i] = 1;
        }
        for (int i = 1; i < n; i++)
            for(int j = 0; j < i; j++)
                if(arr[i] % arr[j] == 0 && d[i] < d[j] + 1 && arr[j] != 0)
                    d[i] = d[j] + 1;
        long max = 1;
        for (int i = 0; i < n; ++i)
            if (max < d[i])
                max = d[i];


        System.out.println(max);


    }

}