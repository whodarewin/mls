package com.hc.ml.feature.engineering.storage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * ISerializer
 * @author han.congcong
 * @date 2019/7/8
 */

public interface IObjSerializer {

    byte[] serialize(List list) throws IOException;

    List<Object> unSerialize(byte[] bytes) throws Exception;
}
