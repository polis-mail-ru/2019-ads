import java.io.PrintWriter;
import java.util.Scanner;

public class Part2_2 {
    private static void solve(final Scanner in, final PrintWriter out) {
        int n = in.nextInt();
        int arr[] = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] d = new int[n + 2];
        int max;
        for (int i = 1; i < n + 2; i++) {
            max = Integer.MIN_VALUE;
            for (int j = (k > i) ? 0 : i - k; j < i; j++) {
                if (d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max + arr[i];
        }
        out.println(d[n + 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
