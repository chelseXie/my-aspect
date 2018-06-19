package com.xiehao.aspect.bean.impl;

import com.xiehao.aspect.bean.Context;
import com.xiehao.aspect.bean.LifeCycleBase;
import com.xiehao.aspect.exception.BeanDuplicationException;
import com.xiehao.aspect.exception.BeanNotFoundException;

/**
 * Created by next on 2018/6/19.
 */
public class BeanContext extends LifeCycleBase implements Context {

    public Object getBean(String name) throws BeanNotFoundException {
        return null;
    }

    public Object getType(Class class0) throws BeanNotFoundException {
        return null;
    }

    public void addBean(Object o) throws BeanDuplicationException {

    }

    public void removeBean(String name) {

    }

    public void init() {

    }

    public void start() {

    }

    public void destroy() {

    }

    public void stop() {

    }

    public void addLifeCycleListener() {

    }

    public void removeLifeCycleListener() {

    }
}
