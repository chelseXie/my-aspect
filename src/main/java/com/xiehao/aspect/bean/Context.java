package com.xiehao.aspect.bean;

import com.xiehao.aspect.exception.BeanDuplicationException;
import com.xiehao.aspect.exception.BeanNotFoundException;

/**
 * Created by next on 2018/6/19.
 */
public interface Context {
    /**
     * 根据bean名称查找bean
     * @param name
     * @return
     * @throws BeanDuplicationException 当没有找到bean时抛异常
     */
    <T> T getBean(String name) throws BeanNotFoundException;

    /**
     * 根据bean类型查找bean
     * @param className
     * @return
     * @throws @throws BeanDuplicationException 当找到多个相同类型的bean是抛出，BeanNotFoundException当没有找到对应类型bean时抛出
     */
    <T> T getType(String  className) throws BeanNotFoundException,BeanDuplicationException;

    /**
     * 添加bean 当有重名bean时抛异常
     * @param o
     * @param name
     * @throws BeanDuplicationException
     */
    void addBean(Object o,String name) throws BeanDuplicationException;
    /**
     * 添加bean 当有重名bean时抛异常
     * @param o
     * @throws BeanDuplicationException
     */
    void addBean(Object o) throws BeanDuplicationException;

    /**
     * 移除bean
     * @param name
     * @throws BeanNotFoundException
     */
    void removeBean(String name) ;




}
