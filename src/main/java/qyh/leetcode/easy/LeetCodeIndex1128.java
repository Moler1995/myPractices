package qyh.leetcode.easy;

import java.util.*;

/**
 * 给你一个由一些多米诺骨牌组成的列表dominoes。
 *
 * 如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 *
 * 形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
 *
 * 在0 <= i < j < dominoes.length的前提下，找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。
 *
 * 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs
 */
public class LeetCodeIndex1128 {
    public static void main(String[] args) {
        int[][] dominoes = {{1,2},{2,1},{2, 1},{1, 2}};
        System.out.println(numEquivDominoPairs(dominoes));
    }

    public static int numEquivDominoPairs(int[][] dominoes) {
        Map<Domino, Integer> dominoMap = new HashMap<>();
        int totalCount = 0;
        for (int[] dominoe : dominoes) {
            Domino domino = new Domino(dominoe[0], dominoe[1]);
            if (dominoMap.containsKey(domino)) {
                int lastCount = dominoMap.get(domino);
                totalCount += lastCount;
                dominoMap.put(domino, lastCount + 1);
            } else {
                dominoMap.put(domino, 1);
            }
        }
        return totalCount;
    }

    private static class Domino {
        private int x;
        private int y;

        public Domino(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            String str = x + Integer.toString(y);
            String str1 = y + Integer.toString(x);
            return str.hashCode() + str1.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Domino) {
                return (((Domino) obj).x == this.x && ((Domino) obj).y == this.y)
                        || ((Domino) obj).x == this.y && ((Domino) obj).y == this.x;
            }
            return false;
        }
    }
}
