package com.hc.ml.feature.engineering;

import java.util.List;

/**
 * EngineeringInfo
 *
 * @author han.congcong
 * @date 2019/7/11
 */

public class EngineeringInfo {
    private List<TransferInfo> transferInfo;
    private String[] trainColumns;
    private String table;

    public EngineeringInfo(){}

    public List<TransferInfo> getTransferInfo() {
        return transferInfo;
    }

    public void setTransferInfo(List<TransferInfo> transferInfo) {
        this.transferInfo = transferInfo;
    }

    public String[] getTrainColumns() {
        return trainColumns;
    }

    public void setTrainColumns(String[] trainColumns) {
        this.trainColumns = trainColumns;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
