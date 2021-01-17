package qyh.leetcode.hard;

/**
 * 给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
 *
 * 通过从 S 中删除 0 个或多个字符来获得子序列。
 *
 * 如果一个字符序列与它反转后的字符序列一致，那么它是回文字符序列。
 *
 * 如果对于某个i，A_i != B_i，那么A_1, A_2, ... 和B_1, B_2, ... 这两个字符序列是不同的。
 *
 * 输入：
 * S = 'bccb'
 * 输出：6
 * 解释：
 * 6 个不同的非空回文子字符序列分别为：'b', 'c', 'bb', 'cc', 'bcb', 'bccb'。
 * 注意：'bcb' 虽然出现两次但仅计数一次。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-different-palindromic-subsequences
 */
public class LeetCodeIndex730 {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("zchmliaqdgvwncfatcfivphddpzjkgyygueikthqzyeeiebczqbqhdytkoawkehkbizdmcnilcjjlpoeoqqoqpswtqdpvszfaksn"));
    }

    private static int countPalindromicSubsequences(String s) {
        return 0;
    }
}
