package ru.mail.polis.ads.valer1435.part4;
//https://www.e-olymp.com/ru/submissions/5877878
import java.io.*;
import java.util.StringTokenizer;

public class Problem4261 {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        int[] arr = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = in.nextInt();
        }
        int res = mergeSort(arr, 0, arr.length-1);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        System.out.print(res);

        bufferedWriter.flush();
    }

    public static int merge(int[] a, int l, int mid, int r) {
        int inv = 0;
        int[] buf = new int[r+1-l];
        for (int j = l; j < r+1; j++){
            buf[j-l] = a[j];
        }
        int cnt = l;
        int cnt1 = l;
        int cnt2 = mid+1;
        while (true) {
            if (buf[cnt1-l] <= buf[cnt2-l]) {
                a[cnt] = buf[cnt1-l];
                cnt++;
                cnt1++;
            } else {
                inv+=(mid-cnt1+1);
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
        return inv;
    }

    private static int mergeSort(int[] arr, int left, int right) {
        int inv = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inv += mergeSort(arr, left, mid);
            inv += mergeSort(arr, mid + 1, right);
            inv += merge(arr, left, mid, right);
        }
        return inv;
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
