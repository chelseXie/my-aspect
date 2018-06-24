package com.xiehao.aspect.proxy;

/**
 * Created by next on 2018/6/20.
 */
public interface ProxyFactory {
    /**
     * 获取代理对象
     * @param oject
     * @param <T>
     * @return
     */
    <T> T getProxyBean(Class<T> oject) throws IllegalAccessException, InstantiationException ;
}
