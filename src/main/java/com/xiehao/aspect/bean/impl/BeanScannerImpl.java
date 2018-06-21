package com.xiehao.aspect.bean.impl;
import com.xiehao.aspect.bean.BeanScanner;
import com.xiehao.aspect.bean.Mather;
import java.io.File;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by next on 2018/6/21.
 */
public class BeanScannerImpl implements BeanScanner {
    @Override
    public List<Object> getBeanList(String basePackage) {
        return null;
    }

    @Override
    public List<Object> getMatherBeanList(String basePackage, Mather mather) {
        List<Object> reusltObj = new ArrayList<>();
        String classPath = getClass().getResource("/").toString();
        if(classPath.startsWith("file:/")){
            classPath = classPath.replace("file:/","");
        }
        String basePath = basePackage.replaceAll("\\.", "/");
        String classRealPath = classPath+basePath;
        File file = new File(classRealPath);
        File[] childFiles = file.listFiles();
        if(childFiles!=null &&childFiles.length>0 ){
            for(File classFile : childFiles){
                if(classFile.isDirectory()){
                    //递归获取所有class Object
                    List<Object>  list= getMatherBeanList(basePackage+"."+classFile.getName(),mather);
                    if(list !=null && list.size()>=1){
                        reusltObj.addAll(list);
                    }
                }else{
                    String fileName = classFile.getName();
                    if(!fileName.endsWith(".class")){
                        continue;
                    }
                    String calssName = fileName.replace(".class","");
                    String fullClassName = basePackage+"."+calssName;
                    Class class0 = null;
                    try {
                        class0 =  Class.forName(fullClassName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    Object obj = null;
                    if(mather.mather(class0)){
                        try {
                            obj = class0.newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        if(obj != null){
                            reusltObj.add(obj);
                        }
                    }
                }
            }
        }
        return reusltObj;
    }
    @Override
    public List<Class> getMatherClassList(String basePackage, Mather mather) {
        List<Class> reusltObj = new ArrayList<>();
        String classPath = getClass().getResource("/").toString();
        if(classPath.startsWith("file:/")){
            classPath = classPath.replace("file:/","");
        }
        String basePath = basePackage.replaceAll("\\.", "/");
        String classRealPath = classPath+basePath;
        File file = new File(classRealPath);
        File[] childFiles = file.listFiles();
        if(childFiles!=null &&childFiles.length>0 ){
            for(File classFile : childFiles){
                if(classFile.isDirectory()){
                    //递归获取所有class Object
                    List<Class>  list= getMatherClassList(basePackage+"."+classFile.getName(),mather);
                    if(list !=null && list.size()>=1){
                        reusltObj.addAll(list);
                    }
                }else{
                    String fileName = classFile.getName();
                    if(!fileName.endsWith(".class")){
                        continue;
                    }
                    String calssName = fileName.replace(".class","");
                    String fullClassName = basePackage+"."+calssName;
                    Class class0 = null;
                    try {
                        class0 =  Class.forName(fullClassName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    if(mather.mather(class0)){
                        reusltObj.add(class0);
                    }
                }
            }
        }
        return reusltObj;
    }

}
