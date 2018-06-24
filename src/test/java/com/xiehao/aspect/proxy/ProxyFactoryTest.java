package com.xiehao.aspect.proxy;

import com.xiehao.aspect.proxy.impl.JavaDynamicProxyFactory;
import org.junit.Test;

/**
 * Created by xieha on 2018/6/24.
 */
public class ProxyFactoryTest {
    @Test
    public void testGetProxyBean() throws InstantiationException, IllegalAccessException {
        ProxyFactory proxyFactory = new JavaDynamicProxyFactory();
        ModelBeanInter modelBean = proxyFactory.getProxyBean(ModelBean.class);
        modelBean.proxyTest();

    }
}
