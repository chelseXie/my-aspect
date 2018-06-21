package com.xiehao.aspect.bean.impl;

import com.xiehao.aspect.bean.Bean;
import com.xiehao.aspect.bean.Context;
import com.xiehao.aspect.bean.LifeCycleBase;
import com.xiehao.aspect.exception.BeanDuplicationException;
import com.xiehao.aspect.exception.BeanNotFoundException;
import com.xiehao.aspect.exception.LifecycleException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by next on 2018/6/19.
 */
public class BeanContext extends LifeCycleBase implements Context {
    //用户存储生成的bean
    List<Bean> elementList = new CopyOnWriteArrayList();

    /** 在添加bean时添加锁，保证线程安全*/
    final transient ReentrantLock lock = new ReentrantLock();

    /**
     * 通过beanName获取bean对象
     * @param name
     * @return
     * @throws BeanNotFoundException
     */

    public <T> T getBean(String name) throws BeanNotFoundException {
        if(elementList.size()<1){
            throw new BeanNotFoundException("no bean named "+name);
        }
        Bean targetBean = null;

        for(Bean bean :  elementList){
            if(name.equals(bean.getName())){
                targetBean =  bean;
                break;
            }
        }

        if(targetBean!=null){
            return targetBean.getTarget();
        }else{
            throw new BeanNotFoundException("no bean named "+name);
        }
    }
    /**
     * 根据bean类型查找bean
     * @param className
     * @return
     * @throws BeanDuplicationException 当找到多个相同类型的bean是抛出，BeanNotFoundException当没有找到对应类型bean时抛出
     */
    public <T> T getType(String className) throws BeanNotFoundException,BeanDuplicationException {
        if(elementList.size()<1){
            throw new BeanNotFoundException("no bean typed "+className);
        }
        Bean targetBean = null;

        for(Bean bean :  elementList){
           if(bean.getClassName().equals(className)){
               if(targetBean!=null){
                   targetBean = bean;
               }else{
                   throw new BeanDuplicationException("multi bean typed "+className);
               }
           }
        }
        if(targetBean!=null){
            return targetBean.getTarget();
        }else{
            throw new BeanNotFoundException("no bean typed "+className);
        }
    }

    public void addBean(Object o,String name) throws BeanDuplicationException {
        Bean bean = new Bean(o,name);
        if(elementList.size()<1){
            elementList.add(bean);
        }else{
            for(Bean b :elementList){
                if(b.getName().equals(name)){
                    throw  new BeanDuplicationException("multi bean named "+name);
                }
            }
        }
    }
    public void addBean(Object o) throws BeanDuplicationException {
        addBean(o,null);
    }
    public void removeBean(String name) {
        if(elementList.size()<1){
            return;
        }
        elementList.removeIf((Bean bean)->bean.getName().equals(name));
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }
}
