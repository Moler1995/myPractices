package qyh.microsoft;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * n 支队伍比赛，分别编号为0，1，2。。。。n-1，已知它们之间的实力对比关系，
 *
 * 存储在一个二维数组w[n][n]中，w[i][j] 的值代表编号为i，j 的队伍中更强的一支。
 *
 * 所以w[i][j]=i 或者j，现在给出它们的出场顺序，并存储在数组order[n]中，
 *
 * 比如order[n] = {4,3,5,8,1......}，那么第一轮比赛就是4 对3， 5 对8。.......
 *
 * 胜者晋级，败者淘汰，同一轮淘汰的所有队伍排名不再细分，即可以随便排，
 *
 * 下一轮由上一轮的胜者按照顺序，再依次两两比，比如可能是4 对5,直至出现第一名
 *
 * 编程实现，给出二维数组w，一维数组order 和用于输出比赛名次的数组result[n]，
 *
 * 求出result。
 */
public class Practice21 {
    public static void main(String[] args) {
        int[][] w = {{0, 1, 2, 3, 0, 0}, {1, 1, 2, 1, 1, 1}, {2, 2, 2, 2, 2, 2}, {3, 1, 2, 3, 3, 3},
                {0, 1, 2, 3, 4, 5}, {0, 1, 2, 3, 5, 5}};
        int[] order = {1, 3, 4, 2, 0, 5};
        System.out.println(Arrays.toString(compete(w, order)));
    }

    public static int[] compete(int[][] w, int[] order) {
        int[] result = new int[w.length];
        int resIndex = w.length - 1;
        Queue<Integer> orderList = new LinkedList<>();
        for (int i = 0; i < order.length; i += 2) {
            if (i + 1 >= order.length) {
                orderList.add(order[i]);
                continue;
            }
            if (w[order[i]][order[i+1]] == order[i+1]) {
                result[resIndex] = order[i];
                orderList.add(order[i+1]);
            } else {
                result[resIndex] = order[i+1];
                orderList.add(order[i]);
            }
            resIndex--;
        }
        while (!orderList.isEmpty()) {
            Integer i = orderList.poll();
            Integer j = orderList.poll();
            if (j == null) {
                result[resIndex] = i;
            } else {
                if (w[i][j] == j) {
                    result[resIndex] = i;
                    orderList.add(j);
                } else {
                    result[resIndex] = j;
                    orderList.add(i);
                }
            }
            resIndex--;
        }
        return result;
    }
}