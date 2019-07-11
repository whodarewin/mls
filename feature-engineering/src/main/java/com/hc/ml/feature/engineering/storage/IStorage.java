package com.hc.ml.feature.engineering.storage;

import java.io.Serializable;
import java.util.List;

/**
 * IStorage
 *
 * @author kq
 * @date 2019/7/8
 */
public interface IStorage {
    /**
     * 线下保存对象，给线上序列化使用
     * @param toSave
     */
    void save(List<? extends Serializable> toSave);

    /**
     * 获得对象，给线上序列化使用
     * @return
     */
    List<? extends Serializable> get();
}
