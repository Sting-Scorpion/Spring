package com.proxy;

import com.proxy.AOP.AOP;
import com.proxy.Service.Service;

/**
 * 静态代理实现了目标对象的灵活切换
 * 图书购买 & 商品购买
 */
public class Agent implements Service {
    Service target;
    AOP aop;

    public Agent(Service target, AOP aop){
        this.target = target;
        this.aop = aop;
    }

    @Override
    public void buy() {
        try {
            //切面功能
            aop.before();
            //业务功能
            target.buy();
            //切面功能
            aop.after();
        } catch (Exception e) {
            //事务回滚
            aop.exception();
        }
    }
}
