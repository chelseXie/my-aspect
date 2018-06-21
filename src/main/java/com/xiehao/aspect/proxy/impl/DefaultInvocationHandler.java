package com.xiehao.aspect.proxy.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/6/20.
 */
public class DefaultInvocationHandler implements InvocationHandler {
    private static  String BEFORE = "Before";
    private static  String AFTER = "After";
    private static  String AFTERRETURNING = "AfterReturning";
    private static  String AROUND = "Around";

    private Object target;
    private Object wovenObject;
    //要织入的方法
    private Map<String,String> wovenMethods;
    //目标对象符合织入条件的方法
    private List<String> targetMethods;
    //是否满足织入条件
    private boolean status = false;

    public Object getInstance(Object target,Object wovenObject,Map wovenMethods,List<String> targetMethods){
        this.target = target;
        this.wovenObject = wovenObject;
        this.wovenMethods = wovenMethods;
        this.targetMethods = targetMethods;
        if(target!=null&&wovenObject!=null&&wovenMethods!=null
                &&wovenMethods.size()>0&&targetMethods!=null&&targetMethods.size()>0){
            status = true;
        }
        Class clazz = target.getClass();
        Object obj = Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
        return obj;
    }
    @Override
    public Object  invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //before执行
        if(status&&targetMethods.contains(method.getName())){
            if(wovenMethods.containsKey(BEFORE)){
                methodHandler(wovenMethods.get(BEFORE));
            }
        }
        // 执行目标对象的方法
        Object result = method.invoke(target, args);
        //After执行
        if(status&&targetMethods.contains(method.getName())){
            if(wovenMethods.containsKey(AFTER)){
                methodHandler(wovenMethods.get(AFTER));
            }
        }
        return result;
    }
    public void methodHandler(String method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if(wovenObject != null){
            Class class0 = wovenObject.getClass();
            Method method0 = class0.getMethod(method);
            method0.invoke(wovenObject);
        }
    }
}
