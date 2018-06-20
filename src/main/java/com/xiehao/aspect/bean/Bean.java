package com.xiehao.aspect.bean;

/**
 * Created by next on 2018/6/19.
 */
public class Bean {
    private String name;
    private String className;
    private Object target;

    public Bean(Object o,String name){
        /**
         * 默认bean名称为类名首字母小写
         */
        if(name ==null ||"".equals(name)){
            //变换第一个字母为小写
            name = o.getClass().getSimpleName();
            String nameFirstChar = name .substring(0,1);
            String nameTalChar = name .substring(1);
            name = nameFirstChar.toLowerCase()+nameTalChar;
        }
        this.name = name;
        this.className = o.getClass().getName();
        this.target = o;

    }
    public Bean(){
    }
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

    public <T> T getTarget() {
        return (T)target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
