package qyh.microsoft;

/**
 * 在字符串中找出连续最长的数字串，并把这个串的长度返回
 */
public class Practice15 {
    public static void main(String[] args) {
        System.out.println(maxNumLength("abcd12345ed125ss123456789"));

    }

    public static int maxNumLength(String input) {
        int max = 0;
        int currMax = 0;
        for (char c : input.toCharArray()) {
            if ('0' <= c && c <= '9') {
                currMax++;
                if (max < currMax) {
                    max = currMax;
                }
            } else {
                currMax = 0;
            }
        }
        return max;
    }
}
