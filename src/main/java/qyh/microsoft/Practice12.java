package qyh.microsoft;

/**
 * 定义Fibonacci 数列如下：
 *
 * / 0 n=0
 *
 * f(n)= 1 n=1
 *
 * \ f(n-1)+f(n-2) n=2
 *
 * 输入n，用最快的方法求该数列的第n 项。
 */
public class Practice12 {
    public static void main(String[] args) {
        System.out.println(fibonacci(9));
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
