package com.xiehao.aspect.bean;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by next on 2018/6/19.
 */
public abstract class LifeCycleBase implements LifeCycle {
    List<LifeCycleListener> lifeCycleListeners = new CopyOnWriteArrayList<LifeCycleListener>();
    private volatile LifeCycleStatus state = LifeCycleStatus.NEW;

    public void addLifeCycleListener(LifeCycleListener listener) {
        lifeCycleListeners.add(listener);
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        lifeCycleListeners.add(listener);
    }
    public void fireLifeCycleListener(LifeCycleEvent e){
        for (LifeCycleListener listener : lifeCycleListeners) {
            listener.lifecycleEvent(e);
        }

    }



}
