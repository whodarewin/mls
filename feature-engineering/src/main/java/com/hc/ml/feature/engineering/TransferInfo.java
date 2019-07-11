package com.hc.ml.feature.engineering;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * TransferInfo
 *
 * @author han.congcong
 * @date 2019/7/11
 */

public class TransferInfo {
    private Method method;
    private Annotation[] parameterTypes;
    private Object object;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Annotation[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Annotation[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
