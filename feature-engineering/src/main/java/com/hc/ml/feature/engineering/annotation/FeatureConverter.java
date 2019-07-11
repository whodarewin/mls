package com.hc.ml.feature.engineering.annotation;

import com.hc.ml.feature.engineering.ConverterTemplete;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FeatureConverter
 *
 * @author han.congcong
 * @date 2019/7/11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureConverter {
    String toField();
    /**
     * 使用哪种处理方式
     * @return
     */
    ConverterTemplete templete() default ConverterTemplete.METHOD;
}
