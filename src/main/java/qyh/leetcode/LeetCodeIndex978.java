package qyh.leetcode;

/**
 * 最长湍流子数组
 */
public class LeetCodeIndex978 {
    public static void main(String[] args) {
        int[] arr = {4,8,12,16};
        System.out.println(maxTurbulenceSize(arr));
    }

    private static int maxTurbulenceSize(int[] arr) {
        if (arr.length == 0) {
            return 0;

        }
        if (arr.length == 1) {
            return 1;
        }
        int gMax = 0;
        int cMax = 0;
        boolean lastOver = true;
        boolean lastUnder = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                if (lastUnder) {
                    cMax++;
                } else {
                    cMax = 1;
                }
                lastUnder = false;
                lastOver = true;
            } else if (arr[i] < arr[i - 1]) {
                if (lastOver) {
                    cMax++;
                } else {
                    cMax = 1;
                }
                lastOver = false;
                lastUnder = true;
            } else {
                cMax = 0;
                lastOver = false;
                lastUnder = false;
            }
            gMax = Math.max(gMax, cMax);
        }
        return gMax + 1;
    }
}
