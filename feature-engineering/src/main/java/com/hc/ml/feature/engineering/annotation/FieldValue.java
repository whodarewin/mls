package com.hc.ml.feature.engineering.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FieldValue
 * 需要转换的值
 * @author han.congcong
 * @date 2019/7/8
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValue {
    String value();
}
