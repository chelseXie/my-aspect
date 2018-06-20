package com.xiehao.aspect.bean;

import java.util.EventObject;

/**
 * Created by next on 2018/6/19.
 */
public class LifeCycleEvent extends EventObject {
    private int  type;
    public LifeCycleEvent(LifeCycle lifeCycle,Integer type) {
        super(lifeCycle);
        this.type = type;
    }
    public LifeCycle getLifecycle() {
        return (LifeCycle) getSource();
    }
}
