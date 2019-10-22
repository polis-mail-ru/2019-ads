package part5;

//https://www.e-olymp.com/ru/submissions/5927770

import java.io.*;
import java.util.*;

public class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        if (n == 1){
            out.println(1);
            return;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        printPermutations(nums, out);
    }

    private static void swap(int[] nums, int i, int j) {
        int ch = nums[i];
        nums[i] = nums[j];
        nums[j] = ch;
    }

    private static void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    private static boolean next_permutation(int[] nums)
    {
        int i = nums.length - 1;
        while (nums[i - 1] >= nums[i])
        {
            if (--i == 0) {
                return false;
            }
        }

        int j = nums.length - 1;
        while (j > i && nums[j] <= nums[i - 1]) {
            j--;
        }

        swap(nums, i-1, j);

        reverse(nums, i);

        return true;
    }

    private static void printPermutations(int[] nums, PrintWriter out)
    {
        do {
            for (int num : nums) {
                out.print(num + " ");
            }
            out.println();

        } while (next_permutation(nums));
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

