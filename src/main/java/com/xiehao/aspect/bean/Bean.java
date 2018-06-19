package com.xiehao.aspect.bean;

/**
 * Created by next on 2018/6/19.
 */
public class Bean {
    private String name;
    private String className;
    private Object target;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
