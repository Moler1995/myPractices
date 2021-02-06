package qyh.leetcode.midium;

/**
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 *
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 *
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 *
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex1423 {
    public static void main(String[] args) {
        int[] cardPoints = {1,2,3,4,5,6,1};
        System.out.println(maxScore(cardPoints, 3));

    }

    private static int maxScore(int[] cardPoints, int k) {
        int maxSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum += cardPoints[i];
        }
        int tmpSum = maxSum;
        for (int i = k - 1; i >= 0; i--) {
            tmpSum = tmpSum - cardPoints[i] + cardPoints[cardPoints.length - (k - i -1) - 1];
            maxSum = Math.max(tmpSum, maxSum);
        }
        return maxSum;
    }
}
