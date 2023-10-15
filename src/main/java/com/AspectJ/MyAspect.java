package com.AspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 3. 切面类
 * 包含各种方法
 */
@Aspect // 交给AspectJ的框架实现切面类
@Component // 交给Spring容器去创建对象
public class MyAspect {
    /**
     * 所有切面功能都是由切面方法实现的
     * 前置通知的切面方法的规范
     * 1)访问权限是public
     * 2)方法的返回值是void
     * 3)方法名称自定义
     * 4)方法没有参数,如果有也只能是JoinPoint类型
     * 5)必须使用@Before注解来声明切入的时机是前切功能和切入点
     *
     * 参数:
     *  value  指定切入点表达式
     */

    /**
     * ver 1，业务方法：
     * public String doSome(String name, int age)
     */
//    @Before(value = "execution(public String com.AspectJ.SomeServiceImpl.*(String,int))")
    /**
     * ver 3
     * AspectJ包下所有
     */
//    @Before(value = "execution(public * com.AspectJ..*(..))")
    /**
     * ver 4
     * 任意
     */
//    @Before(value = "execution(public * *(..))")
    /**
     * ver 2 （推荐）
     * 可以切入SomeServiceImpl类的其他签名的方法，不再局限于严格满足要求的
     */
    @Before(value = "execution(public * com.AspectJ.SomeServiceImpl.*(..))")
    public void myBefore(JoinPoint jp){
        System.out.println("切面方法中的前置通知功能实现............");
        System.out.println("目标方法的签名" + jp.getSignature());
        System.out.println("目标方法的参数" + Arrays.toString(jp.getArgs()));
    }

    /**
     * 后置通知的方法的规范
     * 1)访问权限是public
     * 2)方法没有返回值void
     * 3)方法名称自定义
     * 4)方法有参数(也可以没有参数,如果目标方法没有返回值,则可以写无参的方法,但一般会写有参,这样可以处理无参可以处理有参),这个切面方法的参数就是目标方法的返回值
     * 5)使用@AfterReturning注解表明是后置通知
     *
     * 参数:
     * 	value:指定切入点表达式
     * 	returning:指定目标方法的返回值的名称,则名称必须与切面方法的参数名称一致.
     */
    @AfterReturning(value = "execution(* com.AspectJ.*.*(..))",returning = "obj")
    public void myAfterReturning(Object obj){
        System.out.println("后置通知功能实现..............");
        // 确认有返回值
        if(obj != null){
            // 不能改变的类型
            if(obj instanceof String){
                obj = obj.toString().toUpperCase();
                System.out.println("在切面方法中目标方法的返回值:" + obj);
            }
            // 可以改变的类型
            if(obj instanceof Student){
                Student stu = (Student) obj;
                stu.setName("Tom");
                System.out.println("在切面方法中目标方法的返回值:" + stu);
            }
        }
    }

    /**
     * 环绕通知方法的规范
     * 1)访问权限是public
     * 2)切面方法有返回值,此返回值就是目标方法的返回值
     * 3)方法名称自定义
     * 4)方法有参数,此参数就是目标方法
     * 5)回避异常Throwable
     * 6)使用@Around注解声明是环绕通知
     *
     * 参数:
     * 	value:指定切入点表达式
     */
    @Around(value = "execution(* com.AspectJ.*.*(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        //前切功能实现
        System.out.println("环绕通知中的前置功能实现............");
        //目标方法调用
        Object obj = pjp.proceed(pjp.getArgs());
        //后切功能实现
        System.out.println("环绕通知中的后置功能实现............");
        return obj.toString().toUpperCase();  //改变了目标方法的返回值
    }

    /**
     * 最终通知方法的规范
     * 1)访问权限是public
     * 2)方法没有返回值
     * 3)方法名称自定义
     * 4)方法没有参数,如果有也只能是JoinPoint
     * 5)使用@After注解表明是最终通知
     * 参数:
     * 	value:指定切入点表达式
     */
    @After(value = "myCut()")
    public void myAfter(){
        System.out.println("最终通知的功能........");
    }

    @Before(value = "myCut()")
    public void myBefore(){
        System.out.println("前置通知的功能........新");
    }

    @AfterReturning(value = "myCut()",returning = "obj")
    public void myNewAfterReturning(Object obj){
        System.out.println("后置通知的功能........新");
    }
    @Around(value = "myCut()")
    public Object myNewAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知中的前置通知的功能........新");
        Object obj = pjp.proceed(pjp.getArgs());
        System.out.println("环绕通知中的后置通知的功能........新");
        return obj;
    }

    @Pointcut(value = "execution(* com.AspectJ.*.*(..))")
    public void myCut(){}
}
