package com.hc.ml.feature.engineering;

import com.hc.ml.feature.engineering.annotation.*;
import com.hc.ml.feature.engineering.exception.ObjectCannotBeSerializeException;
import com.hc.ml.feature.engineering.utils.ClassScanner;


import org.dmg.pmml.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * TransferManager
 * 所有的transfer的manager
 * @author han.congcong
 * @date 2019/7/1
 */
public class EngineeringManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EngineeringManager.class);

    private Map<String, EngineeringInfo> key2TrainInfo = new HashMap<>();

    public EngineeringManager() throws Exception {
        loadAll();
    }

    private void loadAll() throws IllegalAccessException, InstantiationException, ObjectCannotBeSerializeException {
        Set<Class<?>> result = ClassScanner.scanClasses("", Recommend.class,null);

        for(Class<?> objectClass : result){
            if(!Serializable.class.isAssignableFrom(objectClass)){
                throw new ObjectCannotBeSerializeException(objectClass.getName());
            }
            Recommend recommend = objectClass.getAnnotation(Recommend.class);

            EngineeringInfo trainInfo = new EngineeringInfo();

            trainInfo.setTable(recommend.table());
            trainInfo.setTrainColumns(recommend.selectedColumn());

            Method[] methods = objectClass.getDeclaredMethods();
            Object object = objectClass.newInstance();
            List<TransferInfo> transferInfos = new ArrayList<>();
            for (Method method : methods) {
                TransferInfo transferInfo = collectFieldConverter(object,method);
                if(transferInfo != null){
                    transferInfos.add(transferInfo);
                }
            }
            trainInfo.setTransferInfo(transferInfos);
            key2TrainInfo.put(recommend.key(),trainInfo);
        }
    }

    private TransferInfo collectFieldConverter(Object object,Method method){
        FeatureConverter converter = method.getAnnotation(FeatureConverter.class);
        if(converter == null){
            return null;
        }

        ConverterTemplete templete = converter.templete();
        switch (templete) {
            case METHOD:
                TransferInfo info = createMethodTransferInfo(object, method);
                return info;
            default:
                throw new RuntimeException("no function found, name : " + templete.name());
        }
    }

    private TransferInfo createMethodTransferInfo(Object object,Method method){
        Annotation[][] types = method.getParameterAnnotations();
        Annotation[] paramUsefulAnnotations = new Annotation[types.length];
        for (int i = 0; i < types.length; i++) {
            Annotation[] paramTypes = types[i];
            if(paramTypes == null){
                paramUsefulAnnotations[i] = null;
                continue;
            }
            for (int i1 = 0; i1 < paramTypes.length; i1++) {
                Annotation annotation = paramTypes[i1];
                if(isProcessingAnnotation(annotation)){
                    paramUsefulAnnotations[i] = annotation;
                }
            }
        }
        TransferInfo info = new TransferInfo();
        info.setMethod(method);
        info.setObject(object);
        info.setParameterInfos(packageParameterInfos(paramUsefulAnnotations));
        return info;
    }

    private List<ParameterInfo> packageParameterInfos(Annotation[] paramUsefulAnnotations) {
        List<ParameterInfo> infos = new ArrayList<>();
        for (Annotation paramUsefulAnnotation : paramUsefulAnnotations) {

            if(paramUsefulAnnotation.annotationType().equals(AllValue.class)){
                infos.add(new ParameterInfo(ParameterType.ALL_VALUE,null));
            }else if(paramUsefulAnnotation.annotationType().equals(ColumnValue.class)){
                infos.add(new ParameterInfo(ParameterType.COLUMN,((ColumnValue)paramUsefulAnnotation).value()));
            }else if(paramUsefulAnnotation.annotationType().equals(FieldValue.class)){
                infos.add(new ParameterInfo(ParameterType.FIELD,((FieldValue)paramUsefulAnnotation).value()));
            }else if(paramUsefulAnnotation.annotationType().equals(RowValue.class)){
                infos.add(new ParameterInfo(ParameterType.ROW,null));
            }else{
                throw new RuntimeException("should not be here");
            }
        }
        return infos;
    }

    private boolean isProcessingAnnotation(Annotation annotation) {
        if(annotation.annotationType().equals(AllValue.class)
                || annotation.annotationType().equals(ColumnValue.class)
                || annotation.annotationType().equals(FieldValue.class)
                || annotation.annotationType().equals(RowValue.class)
        ){
            return true;
        }
        return false;
    }


    public EngineeringInfo getEngineeringInfo(String recommendKey){
        return key2TrainInfo.get(recommendKey);
    }

}
