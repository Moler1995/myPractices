package qyh.proxy.cglib;

/**
 * test
 */
public class CglibExampleTest {
    public static void main(String[] args) {
        CglibExampleRawClass rawClass = new CglibExampleRawClass();
        CglibProxyExample example = new CglibProxyExample();
        CglibExampleRawClass rawClass1 = (CglibExampleRawClass) example.getTarget(rawClass);
        rawClass1.getVals(333);
    }
}
