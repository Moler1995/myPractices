package qyh.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK
 */
public class JDKProxyExample implements InvocationHandler {
    Object obj;
    public Object getProxy(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object result = method.invoke(obj, args);
        doAfter();
        return result;
    }

    private void doBefore() {
        System.out.println("doBefore");
    }

    private void doAfter() {
        System.out.println("doAfter");
    }
}
