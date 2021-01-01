package qyh.microsoft;

/**
 * 输入n 个整数，输出其中最小的k 个
 */
public class Practice05 {
    public static void main(String[] args) {
        int[] vals = {7, 3, 8, 5, 1, 2};
        findMinK(vals, 1);
    }

    public static void findMinK(int[] vals, int k) {
        for (int i = vals.length - 1; i >= 0; i--) {
            if (k > 0) {
                buildHeap(vals, i);
                System.out.println(vals[0]);
                int tmp = vals[0];
                vals[0] = vals[i];
                vals[i] = tmp;
                k--;
            } else {
                break;
            }
        }
        System.out.println("a");
    }

    public static void buildHeap(int[] vals, int index) {
        for (int i = (index + 1) / 2 - 1; i >= 0; i--) {
            if (i * 2 + 1 <= index && vals[2 * i + 1] < vals[i]) {
                int tmp = vals[i];
                vals[i] = vals[2 * i + 1];
                vals[2 * i + 1] = tmp;
                if ((2 * (2 * i + 1) + 1 <= index && vals[2 * (2 * i + 1) + 1] < vals[2 * i + 1])
                        || (2 * (2 * i + 1) + 2 <= index && vals[2 * (2 * i + 1) + 2] < vals[2 * i + 1])) {
                    buildHeap(vals, index);
                }
            }
            if (i * 2 + 2 <= index && vals[2 * i + 2] < vals[i]) {
                int tmp = vals[i];
                vals[i] = vals[2 * i + 2];
                vals[2 * i + 2] = tmp;
                if ((2 * (2 * i + 2) + 1 <= index && vals[2 * (2 * i + 2) + 1] < vals[2 * i + 2])
                        || (2 * (2 * i + 2) + 2 <= index && vals[2 * (2 * i + 2) + 2] < vals[2 * i + 2])) {
                    buildHeap(vals, index);
                }
            }
        }
    }
}
