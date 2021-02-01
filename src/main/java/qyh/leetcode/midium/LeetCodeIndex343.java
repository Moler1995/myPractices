package qyh.leetcode.midium;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex343 {
    public static void main(String[] args) {
        System.out.println(cuttingRopeDp(4));
    }

    public static int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1;
        }
        long max = 1;
        while (n > 4) {
            max *= 3;
            max %= 1000000007;
            n -= 3;
        }
        max = (max * n) % 1000000007;
        return (int) max;
    }

    public static int cuttingRopeDp(int n) {
        int[] dp = new int[n + 1];
        if (n >= 1) {
            dp[0] = 0;
            dp[1] = 0;
        }
        if (n >= 2) {
            dp[2] = 1;
        }
        if (n >= 3) {
            dp[3] = 2;
        }
        if (n >= 4) {
            dp[4] = 4;
        }
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * (i - j));
            }
        }
        return dp[n];
    }
}
