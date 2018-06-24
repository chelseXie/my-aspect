package com.xiehao.aspect.proxy.impl;import com.xiehao.aspect.annotation.Aspect;import com.xiehao.aspect.annotation.AspectDes;import com.xiehao.aspect.bean.BeanScanner;import com.xiehao.aspect.bean.LifeCycleStatus;import com.xiehao.aspect.bean.impl.BeanScannerImpl;import com.xiehao.aspect.proxy.ProxyFactory;import com.xiehao.aspect.proxy.ProxyUtils;import java.lang.reflect.InvocationHandler;import java.lang.reflect.Method;import java.lang.reflect.Proxy;import java.util.ArrayList;import java.util.List;import java.util.Map;import java.util.Objects;/** * java动态代理工厂 * Created by next on 2018/6/20. */public class JavaDynamicProxyFactory implements ProxyFactory{    private LifeCycleStatus status;    private DefaultInvocationHandler invocationHandler = null;    //扫描类    private BeanScanner scanner = null ;    //解析切面规则类    String packageBase = "com.xiehao" ;    private List<AspectDes> aspectDeslist = null;    /**     * 初始化对象     */    public void  init() throws InstantiationException, IllegalAccessException {        //初始化代理类        if(invocationHandler == null){           invocationHandler = new DefaultInvocationHandler();        }        //扫描器        if(scanner ==null){            scanner = new BeanScannerImpl();        }        List<Class> wovenClasss = scanner.getMatherClassList(packageBase, (x) -> {                    return ((Class) x).getAnnotation(Aspect.class) != null;                }        );        if(aspectDeslist ==null){            aspectDeslist = new ArrayList();        }        if(wovenClasss !=null && wovenClasss.size()>0){            for(Class wovenClass : wovenClasss){                aspectDeslist.add(ProxyUtils.convert(wovenClass));            }        }        this.status =LifeCycleStatus.INITED;    }    /**     * 获取代理对象     * @param clazz     * @param <T>     * @return     * @throws IllegalAccessException     * @throws InstantiationException     */    public <T> T getProxyBean(Class<T> clazz) throws IllegalAccessException, InstantiationException {        if(LifeCycleStatus.INITED != this.status){            init();        }        AspectDes aspectDes = null;        List<String> matherTargetMethods = null;        if(aspectDeslist != null && aspectDeslist.size()>0)            for (AspectDes aspectDes1 : aspectDeslist) {                matherTargetMethods = getMatherTargetMethods(aspectDes1, clazz);                if (matherTargetMethods != null && matherTargetMethods.size() > 0) {                    aspectDes = aspectDes1;                    break;                }        }        Object object = invocationHandler.getInstance(clazz.newInstance(),                aspectDes==null?null:aspectDes.getAspectObj(), aspectDes==null?null:aspectDes.getWovenMethods(),matherTargetMethods);        return (T)object;    }    /**     *目前只支持包名的某一级为通配符*，方法名的后半部分或去不为通配符*的匹配     * 如com.xiehao.proxy.*.     * @param aspectDes     * @param clazz     * @return     */    private List<String> getMatherTargetMethods(AspectDes aspectDes, Class clazz ){        List<String> matherTargetMethods = new ArrayList();        if(aspectDes.getMatchingExpression()!=null                && aspectDes.getMatchingExpression().length()>0){            System.out.println(aspectDes.getMatchingExpression());            String[] expres = aspectDes.getMatchingExpression().split("\\.");            String typeName = clazz.getTypeName();            String[] typeNames = typeName.split("\\.");            if(expres.length-1==typeNames.length){                for(int index = 0; index<expres.length-1;index++){                    if(expres[index].equals("*")){                       continue;                    }                    if(!expres[index].equalsIgnoreCase(typeNames[index])){                        return  matherTargetMethods;                    }                }                Method[] methods = clazz.getMethods();                if(expres[expres.length-1].equals("*")) {                    for (Method method : methods) {                        matherTargetMethods.add(method.getName());                    }                    return matherTargetMethods;                }                if(methods!=null && methods.length>0){                    for(Method method:methods){                        if(expres[expres.length-1].endsWith("*")){                            if(method.getName().startsWith(expres[expres.length-1].replace("*",""))){                                matherTargetMethods.add(method.getName());                            }                        }                    }                    return matherTargetMethods;                }            }        }        return matherTargetMethods;    }}