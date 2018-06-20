package com.xiehao.aspect.bean;

import com.xiehao.aspect.exception.LifecycleException;

/**
 * Created by next on 2018/6/19.
 */
public interface LifeCycle {

    /**
     * lifeCycle 可提取
     */
    void init() throws LifecycleException;
    void start() throws LifecycleException;
    void destroy() throws LifecycleException;
    void stop() throws LifecycleException;

    void addLifeCycleListener(LifeCycleListener listener);
    void removeLifeCycleListener(LifeCycleListener listener);

}
