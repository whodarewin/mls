package com.hc.ml.feature.engineering.utils;

import com.google.common.base.Preconditions;
import org.reflections.Reflections;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;


/**
 * ClassScanner
 * class 扫描器
 * @author han.congcong
 * @date 2019/7/3
 */

public class ClassScanner {

    /**
     * 扫描类
     *
     * @param packageScope 包范围
     * @param annotation   类所带的注解
     * @param clazz        类属于那个class
     * @return
     */
    public static Set<Class<?>> scanClasses(String packageScope, Class<? extends Annotation> annotation,
                                                Class clazz) {
        Preconditions.checkNotNull(annotation);
        Reflections reflections = new Reflections(packageScope);
        return scanClasses(reflections,annotation,clazz);
    }

    /**
     * 未测试，莫用
     * @param packageScope 包范围
     * @param annotation 注解
     * @param clazz 类
     * @param file 文件名称
     * @return
     */
    public static Set<Class<?>> scanClasses(String packageScope,Class<? extends Annotation> annotation,
                                                Class<?> clazz, File file){
        Preconditions.checkNotNull(annotation);
        Reflections reflections = new Reflections(packageScope);
        reflections = reflections.collect(file);
        return scanClasses(reflections,annotation,clazz);

    }

    private static Set<Class<?>> scanClasses(Reflections reflections,Class<? extends Annotation> annotation,
                                             Class<?> clazz){
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(annotation);
        if(clazz == null){
            return classes;
        }
        Set<Class<?>> ret = new HashSet<>();
        for (Class<?> cla : classes) {
            if (clazz.isAssignableFrom(cla)) {
                ret.add(cla);
            }
        }

        return ret;
    }
}