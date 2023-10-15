package com.AspectJ;

import org.springframework.stereotype.Service;

/**
 * 2. 业务实现
 */
@Service
public class SomeServiceImpl implements SomeService{
    @Override
    public String doSome(String name, int age) {
        System.out.println("doSome的业务功能实现");
        return name;
    }

    @Override
    public void show(){
        System.out.println("show的业务方法被执行");
    }

    @Override
    public Student change() {
        System.out.println("change()方法被执行");
        return new Student("Jack");
    }
}
