package qyh.leetcode.midium;

/**
 * 第79题
 *
 * @since 2020-09-13
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCodeIndex79 {
    private static boolean[][] choosed;
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        System.out.println(exist(board, "ABCB"));
    }

    private static boolean exist(char[][] board, String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                choosed = new boolean[board.length][board[0].length];
                if (search(board, i, j, letters, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] board, int x, int y, char[] letters, int index) {
        if (board[x][y] != letters[index]) {
            return false;
        } else if (index == letters.length - 1) {
            return true;
        }
        choosed[x][y] = true;
        boolean res = false;
        if (searchNext(index, x, y - 1, board, letters)
                || searchNext(index, x, y + 1, board, letters)
                || searchNext(index, x - 1, y, board, letters)
                || searchNext(index, x + 1, y, board, letters)) {
            res = true;
        }
        choosed[x][y] = false;
        return res;
    }

    private static boolean searchNext(int index, int x, int y, char[][] board, char[] letters) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length) {
            return false;
        }
        return !isSearched(x, y) && search(board, x, y, letters, ++index);
    }

    private static boolean isSearched(int x, int y) {
        return choosed[x][y];
    }
}
