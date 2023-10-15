package com.proxy.AOP;

public interface AOP {
    default void before(){}
    default void after(){}
    default void exception(){}
}
