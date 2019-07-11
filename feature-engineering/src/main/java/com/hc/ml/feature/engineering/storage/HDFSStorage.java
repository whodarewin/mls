package com.hc.ml.feature.engineering.storage;

import java.io.Serializable;
import java.util.List;

/**
 * HDFSStorage
 *
 * @author han.congcong
 * @date 2019/7/11
 */

public class HDFSStorage implements IStorage {

    @Override
    public void save(List<? extends Serializable> toSave) {

    }

    @Override
    public List<? extends Serializable> get() {
        return null;
    }
}
