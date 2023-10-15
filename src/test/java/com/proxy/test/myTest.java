package com.proxy.test;

import com.proxy.AOP.LogAOP;
import com.proxy.AOP.TransactionAOP;
import com.proxy.Agent;
import com.proxy.ProxyFactory;
import com.proxy.Service.BookServiceImpl;
import com.proxy.Service.ProductServiceImpl;
import com.proxy.Service.Service;
import org.junit.jupiter.api.Test;

public class myTest {
    @Test
    void test1(){
        System.out.println("test1");
        Service agent = new Agent(new ProductServiceImpl(), new LogAOP());
        //嵌套，多个切面
        Service agent1 = new Agent(agent, new TransactionAOP());
        agent1.buy();
    }

    @Test
    void test2(){
        System.out.println("test2");
        Service agent = (Service) ProxyFactory.getAgent(new BookServiceImpl(), new TransactionAOP());
        agent.buy();
    }

    @Test
    void test3(){
        System.out.println("test3");
        Service agent = (Service) ProxyFactory.getAgent(new BookServiceImpl(), new LogAOP());
        String s = agent.show(22);
        System.out.println(s);
    }
}
