package com.xiehao.aspect.annotation;

/**
 * Created by xieha on 2018/6/24.
 */
@Aspect
public class MyAspect {
    @Pointcut("com.xiehao.*.*.*.proxy*")
    public void pointCut(){

    }
    @Before("pointCut()")
    public void before(){
        System.out.println("before ");
    }
    @After("pointCut()")
    public void after(){
        System.out.println("after method invoke ");
    }

}
