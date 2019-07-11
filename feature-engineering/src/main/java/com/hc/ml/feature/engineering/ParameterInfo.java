package com.hc.ml.feature.engineering;

/**
 * ParameterInfo
 *
 * @author han.congcong
 * @date 2019/7/11
 */

public class ParameterInfo {
    private ParameterType parameterType;
    private String value;

    public ParameterInfo(ParameterType parameterType, String value) {
        this.parameterType = parameterType;
        this.value = value;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
