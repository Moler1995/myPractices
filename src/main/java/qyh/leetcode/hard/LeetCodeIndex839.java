package qyh.leetcode.hard;

/**
 * 如果交换字符串X 中的两个不同位置的字母，使得它和字符串Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 *
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)；"rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 *
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，
 * 即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 *
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/similar-string-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex839 {
    public static void main(String[] args) {
        String[] strs = {"kccomwcgcs", "socgcmcwkc", "sgckwcmcoc", "coswcmcgkc", "cowkccmsgc", "cosgmccwkc", "sgmkwcccoc", "coswmccgkc", "kowcccmsgc", "kgcomwcccs"};
        System.out.println(numSimilarGroups(strs));
    }

    public static int numSimilarGroups(String[] strs) {
        UFDS ufds = new UFDS(strs.length);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (strs[i].equals(strs[j]) || isSimilar(strs[i], strs[j])) {
                    ufds.union(i, j);
                }
            }
        }
        return ufds.count;
    }

    public static boolean isSimilar(String s1, String s2) {
        int diffCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 2) {
                return false;
            }
        }
        return true;
    }

    private static class UFDS {
        int[] parent;
        int[] rank;
        int count;

        UFDS(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.count = n;
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            } else {
                int rooti = find(parent[i]);
                this.parent[i] = rooti;
                return rooti;
            }
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) {
                return;
            }
            if (rank[rootx] >= rank[rooty]) {
                parent[rooty] = rootx;
            } else {
                parent[rootx] = rooty;
            }
            if (rank[rootx] == rank[rooty]) {
                rank[rootx]++;
            }
            count--;
        }
    }
}
