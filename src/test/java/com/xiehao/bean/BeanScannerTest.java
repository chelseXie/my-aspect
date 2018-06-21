package com.xiehao.bean;

import com.xiehao.aspect.annotation.Aspect;
import com.xiehao.aspect.bean.BeanScanner;
import com.xiehao.aspect.bean.impl.BeanScannerImpl;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

/**
 * Created by next on 2018/6/21.
 */
public class BeanScannerTest {
    @Test
    public void testGetMatherBeanList(){
        BeanScanner beanScanner = new BeanScannerImpl();
        List<Object> list= beanScanner.getMatherBeanList("com.xiehao.bean", (x) -> {
            return ((Class) x).getAnnotation(Aspect.class) != null;
        });
        Assert.assertNotNull(list);

    }
}
