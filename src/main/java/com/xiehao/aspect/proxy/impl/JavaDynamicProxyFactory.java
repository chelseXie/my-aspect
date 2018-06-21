package com.xiehao.aspect.proxy.impl;

import com.xiehao.aspect.proxy.ProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * java动态代理工厂
 * Created by next on 2018/6/20.
 */
public class JavaDynamicProxyFactory implements ProxyFactory{
    private DefaultInvocationHandler invocationHandler = null;
    public void  init(){
       if(invocationHandler == null){
           invocationHandler = new DefaultInvocationHandler();
       }
    }

    @Override
    public <T> T getProxyBean(T oject) {

        invocationHandler.getInstance(oject,)
        T subject = (T)Proxy.newProxyInstance(invocationHandler.getClass().getClassLoader(), oject
                .getClass().getInterfaces(), invocationHandler);

        return null;
    }
    public
}
