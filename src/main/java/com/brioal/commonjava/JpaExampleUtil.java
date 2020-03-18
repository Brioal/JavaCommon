package com.brioal.commonjava;


import org.springframework.data.domain.Example;

/**
 * 用于Jpa Example查询的工具
 */
public class JpaExampleUtil {


    /**
     * 返回简单的Example匹配,默认策略是只匹配非空的字段
     *
     * @param t
     * @return
     */
    public static <T> Example<T> getSimpleExample(T t) {
        Example<T> example = Example.of(t);
        return example;
    }
}
