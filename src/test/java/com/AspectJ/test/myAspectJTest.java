package com.AspectJ.test;

import com.AspectJ.SomeService;
import com.AspectJ.SomeServiceImpl;
import com.AspectJ.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myAspectJTest {

    /**
     * 测试前置通知获取签名、后置通知改变String类返回值、环绕通知改变返回值
     */
    @Test
    public void test1(){
        // 启动容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("AspectJ/applicationContext.xml");
        // 取出代理对象
        SomeService someService = (SomeService) ac.getBean("someServiceImpl"); // 动态代理
        System.out.println(someService.getClass());
        /*
         * $ class com.sun.proxy.$Proxy15
         * JDK动态代理类型
         */
        String s = someService.doSome("Mark", 22);
        // 切面中的输出：MARK
        System.out.println("测试方法中目标方法的返回值:" + s);
        /*
        String类型不能在后置通知中更改返回值
        但是环绕通知可以更改
         */
    }

    /**
     * 测试不同种代理
     */
    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("AspectJ/applicationContext.xml");
        SomeService someService = (SomeService) ac.getBean("someServiceImpl"); // 动态代理
//        SomeServiceImpl someService = (SomeServiceImpl) ac.getBean("someServiceImpl"); // 子类代理
        System.out.println(someService.getClass());
        /*
         * $ class com.AspectJ.SomeServiceImpl$$EnhancerBySpringCGLIB$$714e0c00
         * 类的类型
         */
        someService.show();
    }

    /**
     * 测试后置通知改变引用类型返回值的功能
     */
    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("AspectJ/applicationContext.xml");
        SomeService someService = (SomeService) ac.getBean("someServiceImpl");
        Student stu = someService.change();
        // 切面中改成了Tom
        System.out.println("测试方法中目标方法的返回值:" + stu); // Tom
        /*
        引用类型能在后置通知中改变返回值
         */
    }
}
