package qyh.sortalgcollection;

import java.util.Arrays;

/**
 * 冒泡
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 从小到大
     * @param arr arr
     */
    public static void sort(int[] arr) {
        for (int i = arr.length - 1; i >=0; i--) {
            for (int j = i; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
