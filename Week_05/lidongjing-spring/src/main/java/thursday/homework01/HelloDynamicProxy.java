package thursday.homework01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author lidongjing
 * @Date: 2020/11/19 16:11
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HelloDynamicProxy implements InvocationHandler {
    /**
     * 代理类
     */
    private Object proxy;
    /**
     * 被代理类
     */
    private Object delegate;

    public Object bind(Object delegate, Object proxy) {
        this.proxy = proxy;
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(), this.delegate.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //反射得到操作者的实例
        Class clazz = this.proxy.getClass();
        //反射得到操作者的start方法
        Method start = clazz.getDeclaredMethod("start", new Class[]{Method.class});
        //反射执行要处理对象的原本方法
        start.invoke(this.proxy, new Object[]{method});
        //执行要处理对象的原本方法
        Object object = method.invoke(this.delegate, args);
        //反射得到操作者的end方法
        Method end = clazz.getDeclaredMethod("end", new Class[]{Method.class});
        //反射执行要处理对象的原本方法
        end.invoke(this.proxy, new Object[]{method});
        return object;
    }
}
