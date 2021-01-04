package qyh.microsoft;

/**
 * 输入一个整数n，求从1 到n 这n 个整数的十进制表示中1 出现的次数。
 *
 * 例如输入12，从1 到12 这些整数中包含1 的数字有1，10，11 和12，1 一共出现了5 次。
 */
public class Practice19 {
    public static void main(String[] args) {
        System.out.println(countOnesOptimized(514));
    }

    public static int countOnes(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += countEach(i);
        }
        return count;
    }

    public static int countEach(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 10 == 1) {
                count++;
            }
            n /= 10;
        }
        return count;
    }

    public static int countOnesOptimized(int n) {
        int count = 0;
        int round = 0;
        int weight = 0;
        int base = 1;
        while (n / base > 0) {
            round = (n % (base * 10)) / base;
            weight = n / (base * 10);
            count += weight * base;
            if (round > 1) {
                count += base;
            } else if (round == 1) {
                count += (base / 10) * ((n % base) + 1);
            }
            base *= 10;
        }
        return count;
    }
}
