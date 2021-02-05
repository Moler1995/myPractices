package qyh.leetcode.midium;

/**
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s中的第i个字符变到t中的第 i 个字符需要|s[i] - t[i]|的开销（开销可能为 0），
 * 也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1208 {
    public static void main(String[] args) {
        System.out.println(equalSubstring("fkfnkrfunni", "jyufzxzfbsa", 18));
    }

    public static int equalSubstring(String s, String t, int maxCost) {
        int maxLength = 0;
        int tmpCost = 0;
        for (int i = 0; i < s.length(); i++) {
            int cost = Math.abs(s.charAt(i) - t.charAt(i));
            if (tmpCost + cost > maxCost) {
                break;
            } else {
                tmpCost += cost;
                maxLength++;
            }
        }
        for (int i = maxLength; i < s.length(); i++) {
            int oldCost = Math.abs(s.charAt(i - maxLength) - t.charAt(i - maxLength));
            int newCost = Math.abs(s.charAt(i) - t.charAt(i));
            tmpCost = tmpCost - oldCost + newCost;
            if (tmpCost <= maxCost) {
                int tmpTmpCost = tmpCost;
                int j = i + 1;
                for (;j < s.length(); j++) {
                    int cost = Math.abs(s.charAt(j) - t.charAt(j));
                    if (tmpTmpCost + cost > maxCost) {
                        break;
                    } else {
                        tmpTmpCost += cost;
                        maxLength++;
                    }
                }
                tmpCost = tmpTmpCost;
                i = j - 1;
            }
        }
        return maxLength;
    }
}
