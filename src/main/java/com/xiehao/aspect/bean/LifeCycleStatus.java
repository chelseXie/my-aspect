package com.xiehao.aspect.bean;

/**
 * Created by next on 2018/6/19.
 */
public enum LifeCycleStatus {
    NEW(0, "新建"), INITING(1,"初始化"),INITED(2,"初始化完成"),STARTING(3, "启动"),
    STARTED(4, "已启动"),STOPING(5, "正在停止"),STOPED(6,"已停止");

    int value;
    String name;

    LifeCycleStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
