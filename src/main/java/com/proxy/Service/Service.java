package com.proxy.Service;

public interface Service {
    void buy();

    default String show(int age) {return "Hello " + age;}
}
