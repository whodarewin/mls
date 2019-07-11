package com.hc.ml.feature.engineering.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ColumnValue
 * 单列的所有数据
 * @author han.cong
 * @date 2019/7/5
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnValue {
    /**
     * 标志哪一列
     * @return
     */
    String value();
}
