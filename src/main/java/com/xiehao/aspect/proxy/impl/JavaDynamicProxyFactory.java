package com.xiehao.aspect.proxy.impl;import com.xiehao.aspect.annotation.Aspect;import com.xiehao.aspect.annotation.AspectDes;import com.xiehao.aspect.bean.BeanScanner;import com.xiehao.aspect.bean.LifeCycleStatus;import com.xiehao.aspect.bean.impl.BeanScannerImpl;import com.xiehao.aspect.proxy.ProxyFactory;import com.xiehao.aspect.proxy.ProxyUtils;import java.lang.reflect.InvocationHandler;import java.lang.reflect.Method;import java.lang.reflect.Proxy;import java.util.ArrayList;import java.util.List;import java.util.Objects;/** * java动态代理工厂 * Created by next on 2018/6/20. */public class JavaDynamicProxyFactory implements ProxyFactory{    private LifeCycleStatus status;    private DefaultInvocationHandler invocationHandler = null;    //扫描类    private BeanScanner scanner = null ;    //解析切面规则类    String packageBase = "" ;    private List<AspectDes> aspectDeslist = null;    public void  init(){        //初始化代理类        if(invocationHandler == null){           invocationHandler = new DefaultInvocationHandler();        }        //扫描器        if(scanner ==null){            scanner = new BeanScannerImpl();        }        List<Class> wovenClasss = scanner.getMatherClassList(packageBase, (x) -> {                    return ((Class) x).getAnnotation(Aspect.class) != null;                }        );        if(aspectDeslist ==null){            aspectDeslist = new ArrayList();        }        if(wovenClasss !=null && wovenClasss.size()>0){            for(Class wovenClass : wovenClasss){                aspectDeslist.add(ProxyUtils.convert(wovenClass));            }        }        this.status =LifeCycleStatus.INITED;    }    public <T> T getProxyBean(Class<T> clazz) {        if(LifeCycleStatus.INITED != this.status){            init();        }        Object object = invocationHandler.getInstance(oject,null,null,null);        return null;    }    /**     * 匹配是不是这个     */    private AspectDes matherAspect(Class c){        if(aspectDeslist !=null && aspectDeslist.size()>0){            for(AspectDes aspectDes :aspectDeslist){                aspectDes.getMatchingExpression()            }        }        return null;    }    private boolean mather(AspectDes aspectDes,Class clazz ){        if(aspectDes.getMatchingExpression()!=null && aspectDes.getMatchingExpression().length()>0){            String[] expres = aspectDes.getMatchingExpression().split(".");            String typeName = clazz.getTypeName();            String[] typeNames = typeName.split(".");            if(expres.length-1==typeNames.length){                for(int index = 0; index<expres.length-1;index++){                    if(expres[index].equals("*")){                       continue;                    }                    if(!expres[index].equalsIgnoreCase(typeNames[index])){                        return  false;                    }                }                if(expres[expres.length-1].endsWith("*")){                    return true;                }                Method[] methods = clazz.getMethods();                if(methods!=null && methods.length>0){                    for(Method method:methods){                        if(expres[expres.length-1].endsWith("*")){                            if(method.getName().startsWith(expres[expres.length-1].replace("*",""))){                                return true;                            }                        }                    }                }            }        }        return false;    }}