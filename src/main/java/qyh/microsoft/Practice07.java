package qyh.microsoft;

/**
 * 输入一个整数数组，判断该数组是不是某二元查找树的后序遍历的结果。
 *
 * 如果是返回true，否则返回false。
 */
public class Practice07 {
    public static void main(String[] args) {
        int[] vals = {7, 4, 6, 5};
        System.out.println(isPostOrder(vals, 0 , 3));
    }

    public static boolean isPostOrder(int[] vals, int begin, int end) {
        int mid = begin;
        boolean flag = false;
        for (int i = begin; i < end; i++) {
            if (vals[i] > vals[end] && !flag) {
                mid = i;
                flag = true;
            }
            if (flag && vals[i] < vals[end]) {
                return false;
            }
            if (i == end - 1) {
                return true;
            }
        }
        return isPostOrder(vals, begin, mid) && isPostOrder(vals, mid + 1, end);
    }
}
