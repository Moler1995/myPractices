package qyh.proxy.jdk;

/**
 * jdk
 */
public class JDKExampleTest {
    public static void main(String[] args) {
        IJDKProxyExample ijdkProxyExample = new JDKProxyExampleImpl();
        JDKProxyExample jdkProxyExample = new JDKProxyExample();
        IJDKProxyExample example = (IJDKProxyExample) jdkProxyExample.getProxy(ijdkProxyExample);
        System.out.println(example.getVals(3));
    }
}
