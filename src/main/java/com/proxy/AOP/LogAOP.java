package com.proxy.AOP;

public class LogAOP implements AOP{
    @Override
    public void before(){
        System.out.println("日志输出......");
    }
}
