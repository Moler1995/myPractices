package qyh.sortalgcollection;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        int[] tmparr = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmparr);
    }

    private static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, left, mid, tmp);
        sort(arr, mid + 1, right, tmp);
        merge(arr, left, mid, right, tmp);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int l = left;
        int r = mid + 1;
        int index  = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                tmp[index++] = arr[l++];
            } else {
                tmp[index++] = arr[r++];
            }
        }
        while (l <= mid) {
            tmp[index++] = arr[l++];
        }
        while (r <= right) {
            tmp[index++] = arr[r++];
        }
        index = 0;
        while (left <= right) {
            arr[left++] = tmp[index++];
        }
    }
}
