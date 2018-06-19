package com.xiehao.aspect.bean;

/**
 * Created by next on 2018/6/19.
 */
public interface LifeCycle {

    /**
     * lifeCycle 可提取
     */
    void init();
    void start();
    void destroy();
    void stop();

    void addLifeCycleListener();
    void removeLifeCycleListener();

}
