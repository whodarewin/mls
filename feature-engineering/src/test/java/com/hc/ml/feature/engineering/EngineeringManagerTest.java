package com.hc.ml.feature.engineering;

import com.hc.ml.feature.engineering.annotation.FeatureConverter;
import com.hc.ml.feature.engineering.annotation.FieldValue;
import com.hc.ml.feature.engineering.annotation.Recommend;
import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * EngineeringManager
 *
 * @author han.congcong
 * @date 2019/7/11
 */

@Recommend(
        key = "recommend",
        table = "table",
        selectedColumn = {"a","b"}
)
public class EngineeringManagerTest implements Serializable {

    @FeatureConverter(toField = "a")
    public String test(@FieldValue("field") String test){
        return "test";
    }
    @Test
    public void testEngineeringManager() throws Exception {
        EngineeringManager manager = new EngineeringManager();
        EngineeringInfo info = manager.getEngineeringInfo("recommend");
        Assert.assertEquals(info.getTable(),"table");
        Assert.assertEquals(info.getTrainColumns().length,2);
        Assert.assertEquals(info.getTransferInfo().size(),1);
    }
}
