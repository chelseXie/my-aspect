package com.xiehao.aspect.bean;

import com.xiehao.aspect.exception.LifecycleException;

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

    public void start() throws LifecycleException{}
    public void init() throws LifecycleException {
        if (!state.equals(LifeCycleStatus.NEW)) {
            throw new LifecycleException("status error  in init()");
        }
        try {
            initInternal();
            setStateInternal(LifeCycleStatus.INITED);
        } catch (Throwable t) {

        }}
    public void stop() throws LifecycleException{}
    public void destroy()throws LifecycleException{};

    /**
     * 公共的状态更改行为
     */
    public void setStateInternal(LifeCycleStatus status)   throws LifecycleException{


        if (!((this.state == LifeCycleStatus.NEW &&
                        state == LifeCycleStatus.STARTING) ||
                (this.state == LifeCycleStatus.STARTED &&
                        state == LifeCycleStatus.STOPING) ||
                (this.state == LifeCycleStatus.STOPING &&
                        state == LifeCycleStatus.STOPED))) {

            throw new LifecycleException("status error");
        }
        this.state = state;
        LifeCycleEvent lifecycleEvent = new LifeCycleEvent(this,this.state.getValue());
        if (lifecycleEvent != null) {
            fireLifeCycleListener(lifecycleEvent);
        }

    }
    /**
     * 各个子类各自实现初始化个性化行为
     */
    protected abstract void initInternal() throws LifecycleException;
}
