package ru.mail.polis.ads.valer1435.part3;

//https://www.e-olymp.com/ru/submissions/5825664
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem3738 {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        int[] arr = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = in.nextInt();
        }
        mergeSort(arr, 0, arr.length-1);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int index = 0; index < arr.length; index++) {
            System.out.print(arr[index]);
            if (index != arr.length-1){
                System.out.print(" ");
            }
        }
        bufferedWriter.flush();
    }

    public static void merge(int[] a, int l, int mid, int r) {
        int[] buf = new int[r+1-l];
        for (int j = l; j < r+1; j++){
            buf[j-l] = a[j];
        }
        int cnt = l;
        int cnt1 = l;
        int cnt2 = mid+1;
        while (true) {
            if (buf[cnt1-l] < buf[cnt2-l]) {
                a[cnt] = buf[cnt1-l];
                cnt++;
                cnt1++;
            } else {
                a[cnt] = buf[cnt2-l];
                cnt++;
                cnt2++;
            }
            if (cnt1 > mid) {
                break;
            } else if (cnt2 > r) {
                for (int j = cnt1; j <= mid; j++) {
                    a[cnt] = buf[j-l];
                    cnt++;
                }
                break;
            }
        }
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }
}