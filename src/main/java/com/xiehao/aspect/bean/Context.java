package com.xiehao.aspect.bean;

import com.xiehao.aspect.exception.BeanDuplicationException;
import com.xiehao.aspect.exception.BeanNotFoundException;

/**
 * Created by next on 2018/6/19.
 */
public interface Context<T> {
    /**
     * 根据bean名称查找bean
     * @param name
     * @return
     * @throws BeanDuplicationException 当没有找到bean时抛异常
     */
    T getBean(String name) throws BeanNotFoundException;

    /**
     * 根据bean类型查找bean
     * @param class0
     * @return
     * @throws BeanDuplicationException 当没有找到bean时抛异常
     */
    T getType(Class class0) throws BeanNotFoundException;

    /**
     * 添加bean 当有重名bean时抛异常
     * @param o
     * @throws BeanDuplicationException
     */
    void addBean(T o) throws BeanDuplicationException;

    /**
     * 移除bean
     * @param name
     * @throws BeanNotFoundException
     */
    void removeBean(String name) ;




}
