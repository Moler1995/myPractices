package qyh.leetcode.easy;

/**
 * 在一个XY 坐标系中有一些点，我们用数组coordinates来分别记录它们的坐标，其中coordinates[i] = [x, y]表示横坐标为 x、纵坐标为 y的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 *
 */
public class LeetCodeIndex1232 {
    public static void main(String[] args) {
        int[][] vals = {{0, 1}, {1, 3}, {-4, -7}, {5, 11}};
        System.out.println(checkStraightLine(vals));
    }

    public static boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length == 0 || coordinates.length == 1) {
            return false;
        }
        if (coordinates.length == 2) {
            return true;
        }
        int x = 0;
        int y = 1;
        int[] slop = {coordinates[1][y] - coordinates[0][y], coordinates[1][x] - coordinates[0][x]};
        simplifySlope(slop);
        for (int i = 2; i < coordinates.length; i++) {
            int[] currSlop = {coordinates[i][y] - coordinates[i - 1][y], coordinates[i][x] - coordinates[i - 1][x]};
            if (currSlop[0] == 0 && slop[0] == 0) {
                continue;
            }
            if (currSlop[1] == 0 && slop[1] == 0) {
                continue;
            }
            simplifySlope(currSlop);
            if (Math.abs(currSlop[0]) != Math.abs(slop[0]) || Math.abs(currSlop[1]) != Math.abs(slop[1])
                    || currSlop[0] * currSlop[1] != slop[0] * slop[1]) {
                return false;
            }
        }
        return true;
    }

    public static void simplifySlope(int[] coordiff) {
        int smaller = Math.min(Math.abs(coordiff[0]), Math.abs(coordiff[1]));
        for (int i = smaller; i >= 2; i--) {
            if (coordiff[0] % i == 0 && coordiff[1] % i == 0) {
                coordiff[0] /= i;
                coordiff[1] /= i;
                break;
            }
        }
    }
}
