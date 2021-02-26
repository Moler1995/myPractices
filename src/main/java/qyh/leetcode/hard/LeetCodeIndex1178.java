package qyh.leetcode.hard;

import java.util.*;

/**
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词word中包含谜面puzzle的第一个字母。
 * 单词word中的每一个字母都可以在谜面puzzle中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced",
 * "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）。
 * 返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words
 * 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1178 {
    public static void main(String[] args) {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        System.out.println(findNumOfValidWords1(words, puzzles));
    }

    private static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Set<Integer>[] wordIndex = new HashSet[26];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                if (wordIndex[c - 'a'] == null) {
                    wordIndex[c - 'a'] = new HashSet<>();
                }
                wordIndex[c - 'a'].add(i);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (String puzzle : puzzles) {
            Set<Integer> candidates = wordIndex[puzzle.charAt(0) - 'a'];
            if (candidates == null) {
                result.add(0);
            } else {
                int count = candidates.size();
                for (int candidate : candidates) {
                    for (char c : words[candidate].toCharArray()) {
                        if (puzzle.indexOf(c) == -1) {
                            count--;
                            break;
                        }
                    }
                }
                result.add(count);
            }
        }
        return result;
    }

    private static List<Integer> findNumOfValidWords1(String[] words, String[] puzzles) {
        Map<Integer, Integer> wordMap = new HashMap<>();
        for (String str : words) {
            int mask = 0;
            for (char c : str.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                wordMap.put(mask, wordMap.getOrDefault(mask, 0) + 1);
            }
        }
        List<Integer> result = new ArrayList<>();
        for (String puzzle : puzzles) {
            int count = 0;
            int mask = 0;
            for (int i = 1; i < puzzle.length(); i++) {
                char c = puzzle.charAt(i);
                mask |= (1 << (c - 'a'));
            }
            int subnet = mask;
            do {
                int s = subnet | (1 << (puzzle.charAt(0) - 'a'));
                count += wordMap.getOrDefault(s, 0);
                subnet = (subnet - 1) & mask;
            } while (subnet != mask);
            result.add(count);
        }
        return result;
    }
}
