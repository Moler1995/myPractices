package qyh.proxy.cglib;

/**
 * 被代理类
 */
public class CglibExampleRawClass {

    public int getVals(int val) {
        System.out.println("invoke" + val);
        return val + 1;
    }
}
