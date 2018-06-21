package com.xiehao.aspect.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by next on 2018/6/19.
 */
public interface BeanScanner {
    /**
     * 获取这个包下所有bean
     * @param basePackage
     * @return
     */
    public List<Object> getBeanList(String basePackage);

    /**
     * 获取符合规则的bean
     * @param basePackage
     * @return
     */
    public List<Object> getMatherBeanList(String basePackage,Mather mather);
    /**
     * 获取符合规则的Class
     * @param basePackage
     * @return
     */
    public List<Class> getMatherClassList(String basePackage,Mather mather);
}
