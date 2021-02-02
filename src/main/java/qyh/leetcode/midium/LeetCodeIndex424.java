package qyh.leetcode.midium;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换k次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过104。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex424 {
    public static void main(String[] args) {
        System.out.println(characterReplacement("BAAAB", 2));
    }

    public static int characterReplacement(String s, int k) {
        int[] letterArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 1;
            int k1 = k;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    count++;
                } else {
                    if (k1 > 0) {
                        count++;
                        k1--;
                    } else {
                        break;
                    }
                }
            }
            if (k1 > 0) {
                count = Math.min(count + k1, s.length());
            }
            letterArr[c - 'A'] = Math.max(letterArr[c - 'A'], count);
        }
        int max = 0;
        for (int count : letterArr) {
            max = Math.max(max, count);
        }
        return max;
    }
}
