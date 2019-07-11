package com.hc.ml.feature.engineering.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AllValue
 * 所有的数据 暂时废弃
 * @author kq
 * @date 2019/7/5
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllValue {
}
