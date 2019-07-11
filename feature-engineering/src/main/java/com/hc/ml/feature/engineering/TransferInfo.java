package com.hc.ml.feature.engineering;

import java.lang.reflect.Method;
import java.util.List;

/**
 * TransferInfo
 *
 * @author han.congcong
 * @date 2019/7/11
 */

public class TransferInfo {
    private Method method;
    private List<ParameterInfo> parameterInfos;
    private Object object;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public List<ParameterInfo> getParameterInfos() {
        return parameterInfos;
    }

    public void setParameterInfos(List<ParameterInfo> parameterInfos) {
        this.parameterInfos = parameterInfos;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
