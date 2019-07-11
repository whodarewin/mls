package com.hc.ml.feature.engineering.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RowValue
 * 这一行的所有数据
 * @author han.congcong
 * @date 2019/7/5
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RowValue {

}
