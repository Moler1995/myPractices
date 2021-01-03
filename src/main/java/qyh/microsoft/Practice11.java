package qyh.microsoft;

/**
 * n 个数字（0,1,…,n-1）形成一个圆圈，从数字0 开始，每次从这个圆圈中删除第m 个数字（第一个为当前数字本身，第二个为当前数字的下一个数
 *
 * 字）。
 *
 * 当一个数字删除后，从被删除数字的下一个继续删除第m 个数字。
 *
 * 求出在这个圆圈中剩下的最后一个数字。
 */
public class Practice11 {
    public static void main(String[] args) {
        System.out.println(findRemaining(10, 5));
    }

    public static int findRemaining(int n, int m) {
        if(n < 1 || m < 1)
            return -1;

        int last = 0;
        for (int i = 2; i <= n; i ++)
            last = (last + m) % i;
        return last;
    }
}
