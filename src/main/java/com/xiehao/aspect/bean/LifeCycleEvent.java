package com.xiehao.aspect.bean;

import java.util.EventObject;

/**
 * Created by next on 2018/6/19.
 */
public class LifeCycleEvent extends EventObject {

    public LifeCycleEvent(LifeCycle lifeCycle) {
        super(lifeCycle);
    }
    public LifeCycle getLifecycle() {
        return (LifeCycle) getSource();
    }
}
