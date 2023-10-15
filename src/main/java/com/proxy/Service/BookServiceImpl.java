package com.proxy.Service;

import com.proxy.Service.Service;

public class BookServiceImpl implements Service {
    @Override
    public void buy() {
        System.out.println("图书购买业务实现............");
    }

    @Override
    public String show(int age) {
        return "Book Service " + age;
    }
}
