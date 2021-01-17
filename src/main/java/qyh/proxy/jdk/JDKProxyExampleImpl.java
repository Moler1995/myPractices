package qyh.proxy.jdk;

/**
 * JDK
 */
public class JDKProxyExampleImpl implements IJDKProxyExample {
    @Override
    public int getVals(int val) {
        System.out.println("invoke val " + val);
        return 1;
    }
}
