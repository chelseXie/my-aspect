package com.xiehao.aspect.proxy;

import com.xiehao.aspect.annotation.*;
import com.xiehao.aspect.bean.Mather;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by next on 2018/6/21.
 */
public class ProxyUtils {
    public static boolean mather(Object object,Mather mather){
        return mather.mather(object);

    }
    public static AspectDes convert(Class object) throws IllegalAccessException, InstantiationException {
        Map<String ,String> wovenMethods= new HashMap<String,String>();
        AspectDes aspectDes = new AspectDes();
        Method[] methods = object.getMethods();
        if(methods != null && methods.length>0 ){
            for(Method method : methods){
                Pointcut annotation= method.getAnnotation(Pointcut.class);
                if(annotation != null){
                    aspectDes.setMatchingExpression(annotation.value());
                }
                Before before = method.getAnnotation(Before.class);
                if(before != null){
                    aspectDes.setHasBefore(true);
                    wovenMethods.put(Before.class.getSimpleName(), method.getName());
                }
                AfterReturning afterReturning = method.getAnnotation(AfterReturning.class);
                if(afterReturning != null){
                    aspectDes.setHasAfterReturning(true);
                    wovenMethods.put(AfterReturning.class.getSimpleName(),method.getName());
                }
                After after = method.getAnnotation(After.class);
                if(after != null){
                    aspectDes.setHasAfter(true);
                    wovenMethods.put(After.class.getSimpleName(),method.getName());
                }
                Around around = method.getAnnotation(Around.class);
                if(around != null){
                    aspectDes.setHasAround(true);
                    wovenMethods.put(Around.class.getSimpleName(),method.getName());
                }

            }
            if(aspectDes.isHasAfter()||aspectDes.isHasAfterReturning()
                    ||aspectDes.isHasAround()||aspectDes.isHasBefore()){
                aspectDes.setAspectObj(object.newInstance());
                aspectDes.setWovenMethods(wovenMethods);
            }
        }
        return aspectDes;
    }

}
