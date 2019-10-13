package ru.mail.polis.ads.valer1435.part3;

// https://www.e-olymp.com/ru/submissions/5850946
import java.io.*;
import java.util.StringTokenizer;

public class Problem4037 {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        int cnt = in.nextInt();
        Robot[] arr = new Robot[cnt];
        for (int i = 0; i < cnt; i++) {
            int s1 = in.nextInt();
            int s2 = in.nextInt();
            arr[i] = new Robot(s1, s2);
        }
        mergeSort(arr,0, arr.length-1);

        for (int index = 0; index < arr.length; index++) {
            out.println(arr[index].firstNum+" "+arr[index].secondNum);
        }
        out.flush();
    }

    public static void merge(Robot[] a, int l, int mid, int r) {
        Robot[] buf = new Robot[r+1-l];
        for (int j = l; j < r+1; j++){
            buf[j-l] = a[j];
        }
        int cnt = l;
        int cnt1 = l;
        int cnt2 = mid+1;
        while (true) {
            if (buf[cnt1-l].firstNum <= buf[cnt2-l].firstNum) {
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

    private static void mergeSort(Robot[] arr,  int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static class Robot{
        int firstNum;
        int secondNum;

        Robot(int first, int second){
            firstNum = first;
            secondNum = second;
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