package qyh.dp;

import java.util.Arrays;

/**
 * dp经典例题，最长公共子序列
 * 最长公共子串
 */
public class LCS {
    static class T {
        volatile int i = 0;

        void in(){
            i++;
        }
    }
    public static void main(String[] args) {
        T t = new T();
        for (int i =0 ; i<10;i++){
            new Thread(() -> {
                for (int j = 0;j<1000;j++){
                    t.in();
                }
            }).start();
        }
        while (Thread.activeCount() >1){
            Thread.yield();
        }
        System.out.println(t.i);
    }

    public static int lcs(char[] cs1, char[] cs2) {
        int[][] res = new int[cs1.length + 1][cs2.length + 1];
        for (int i = 1; i < cs1.length + 1; i++) {
            for (int j = 1; j < cs2.length + 1; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        return res[cs1.length][cs2.length];
    }

    /**
     * 最长公共子串
     *
     * @param cs1 cs1
     * @param cs2 cs2
     * @return length
     */
    public static int lcs1(char[] cs1, char[] cs2) {
        int[][] res = new int[cs1.length + 1][cs2.length + 1];
        int length = 0;
        for (int i = 1; i < cs1.length + 1; i++) {
            for (int j = 1; j < cs2.length + 1; j++) {
                if (cs1[i - 1] == cs2[j - 1]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = 0;
                }
                length = Math.max(res[i][j], length);
            }
        }
        Arrays.stream(res).forEach(arr -> System.out.println(Arrays.toString(arr)));
        return length;
    }
}
