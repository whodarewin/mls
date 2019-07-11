package com.hc.ml.feature.engineering.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是哪个推荐服务
 * @author hancong
 * @date 2019/7/1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Recommend {
    /**
     * 推荐的系统key
     * @return
     */
    String key();

    /**
     * 输入的table名称
     * @return
     */
    String table();

    /**
     * 训练需要的列（转换后）
     * @return
     */
    String[] selectedColumn();

}
