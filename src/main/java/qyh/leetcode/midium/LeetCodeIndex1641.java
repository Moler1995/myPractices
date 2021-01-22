package qyh.leetcode.midium;

/**
 * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
 *
 * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-sorted-vowel-strings
 */
public class LeetCodeIndex1641 {
    public static void main(String[] args) {
        System.out.println(countVowelStrings(33));
    }

    public static int countVowelStrings(int n) {
        int[][] divided = new int[n][5];
        divided[0][0] = 1;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0 || j == 0) {
                    divided[i][j] = 1;
                } else {
                    divided[i][j] = divided[i - 1][j] + divided[i][j - 1];
                }
                if (i == n - 1) {
                    sum += divided[i][j];
                }
            }
        }
        return sum;
    }
}
