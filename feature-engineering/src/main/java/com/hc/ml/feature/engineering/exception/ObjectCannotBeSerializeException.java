package com.hc.ml.feature.engineering.exception;

/**
 * ObjectCannotBeSerializeException
 *
 * @author han.congcong
 * @date 2019/7/5
 */

public class ObjectCannotBeSerializeException extends Exception {
    public ObjectCannotBeSerializeException(){
        super();
    }

    public ObjectCannotBeSerializeException(String msg){
        super(msg);
    }
}
