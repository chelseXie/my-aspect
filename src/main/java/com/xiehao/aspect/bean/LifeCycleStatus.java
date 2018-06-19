package com.xiehao.aspect.bean;

/**
 * Created by next on 2018/6/19.
 */
public enum LifeCycleStatus {
    NEW(0, "新建"), STARTINT(1, "开始"), STARTED(2, "结束"),STOPING(3, "正在停止"),STOPED(4,"已停止");

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
