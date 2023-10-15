package com.proxy;

import com.proxy.AOP.AOP;
import com.proxy.Service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理实现AOP框架
 */
public class ProxyFactory {
    public static Object getAgent(Service target, AOP aop){
        //返回动态代理对象
        return Proxy.newProxyInstance(
                //类加载器
                target.getClass().getClassLoader(),
                //目标对象实现的所有接口
                target.getClass().getInterfaces(),
                //代理功能实现
                new InvocationHandler() {
                    @Override
                    public Object invoke(
                            //生成的代理对象
                            Object proxy,
                            //正在被调用的目标方法 eg.buy(), show() ....
                            Method method,
                            //目标方法的参数
                            Object[] args) throws Throwable {
                        Object obj = null;
                        try {
                            //切面功能
                            aop.before();
                            //业务功能
                            obj = method.invoke(target, args);
                            //切面功能
                            aop.after();
                        } catch (Exception e) {
                            aop.exception();
                        }
                        return obj; //目标方法的返回值
                    }
                }
        );
    }
}
