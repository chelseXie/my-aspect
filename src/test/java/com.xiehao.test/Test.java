package com.xiehao.test;

import com.xiehao.aspect.bean.Bean;

/**
 * Created by next on 2018/6/20.
 */
public class Test {
    public static void main(String[] args){
        Bean bean = new Bean();
        System.out.println(bean.getClass().getSimpleName());
        System.out.println(bean.getClass().getName());
        System.out.println(bean.getClass().getTypeName());
    }
}
