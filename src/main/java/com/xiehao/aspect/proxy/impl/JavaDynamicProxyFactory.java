package com.xiehao.aspect.proxy.impl;

import com.xiehao.aspect.annotation.Aspect;
import com.xiehao.aspect.annotation.AspectDes;
import com.xiehao.aspect.bean.BeanScanner;
import com.xiehao.aspect.bean.impl.BeanScannerImpl;
import com.xiehao.aspect.proxy.ProxyFactory;
import com.xiehao.aspect.proxy.ProxyUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * java动态代理工厂
 * Created by next on 2018/6/20.
 */
public class JavaDynamicProxyFactory implements ProxyFactory{
    private DefaultInvocationHandler invocationHandler = null;
    //扫描类
    private BeanScanner scanner = null ;
    //解析切面规则类

    String packageBase = "" ;

    private List<AspectDes> aspectDeslist = null;



    public void  init(){
        //初始化代理类
        if(invocationHandler == null){

           invocationHandler = new DefaultInvocationHandler();
        }
        //扫描器
        if(scanner ==null){
            scanner = new BeanScannerImpl();
        }
        List<Class> wovenClasss = scanner.getMatherClassList(packageBase, (x) -> {
                    return ((Class) x).getAnnotation(Aspect.class) != null;
                }
        );
        if(aspectDeslist ==null){
            aspectDeslist = new ArrayList();
        }
        if(wovenClasss !=null && wovenClasss.size()>0){
            for(Class wovenClass : wovenClasss){
                aspectDeslist.add(ProxyUtils.convert(wovenClass));
            }
        }
    }


    public <T> T getProxyBean(T oject) {

        invocationHandler.getInstance(oject,null,null,null);
        Class<?> stuProxyClass = Proxy.getProxyClass(oject.getClass().getClassLoader(), new Class<?>[]{oject.getClass()});

        return null;
    }
    private void initAspect(){

    }

}
