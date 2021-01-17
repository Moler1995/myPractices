package qyh.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib
 */
public class CglibProxyExample implements MethodInterceptor {

    public Object getTarget(Object obj) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        doBefore();
        Object result = methodProxy.invokeSuper(o, objects);
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
