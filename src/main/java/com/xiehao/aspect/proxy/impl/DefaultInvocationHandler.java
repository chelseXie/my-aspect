package com.xiehao.aspect.proxy.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by next on 2018/6/20.
 */
public class DefaultInvocationHandler implements InvocationHandler {
    private static  String BEFORE = "before";
    private static  String AFTER = "after";
    private static  String AFTERRETURNING = "afterReturning";
    private static  String AROUND = "around";


    private Object target;
    private Object wovenObject;
    private String method;
    private String advice;

    public Object getInstance(Object target,Object wovenObject,String method){
        this.target = target;
        this.wovenObject = wovenObject;
        this.method = method;
        Class clazz = target.getClass();
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }
    @Override
    public Object  invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(BEFORE.equals(this.advice)||AROUND.equals(this.advice)){
            methodHandler();
        }
        // 执行目标对象的方法
        Object result = method.invoke(target, args);
        if(AFTER.equals(this.advice)||AROUND.equals(this.advice)){
            methodHandler();
        }
        return result;
    }
    public void methodHandler() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(wovenObject != null){
            Class class0 = wovenObject.getClass();
            Method method0 = class0.getMethod(this.method);
            method0.invoke(wovenObject);
        }
    }
}
