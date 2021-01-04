package qyh.microsoft;

/**
 * 输入一个整数，求该整数的二进制表达中有多少个1。
 *
 * 例如输入10，由于其二进制表示为1010，有两个1，因此输出2
 */
public class Practice17 {
    public static void main(String[] args) {
        System.out.println(countOnes(10));
    }

    public static int countOnes(int input) {
        int count = 0;
        while (input != 0) {
            if (input % 2 != 0) {
                count++;
            }
            input = input / 2;
        }
        return count;
    }
}
