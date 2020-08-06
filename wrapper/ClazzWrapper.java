package com.jingtong.zhgj.ops.log.wrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 通过反射获得类型的属性列表
 * 构造的时候需要传进Class类型（新或者旧的对象）
 */
public class ClazzWrapper {

    private List<Field> fieldList;

    public ClazzWrapper( Class clazz ) {
        this.fieldList = getFields( clazz );
    }


    public List<Field> getFidldList() {
        return fieldList;
    }

    private List<Field> getFields (Class clazz ) {
        List<Field> fieldList = new ArrayList<>();
        return getFields( fieldList, clazz );
    }

    private List<Field> getFields (List<Field> fieldList, Class clazz ) {
        fieldList.addAll( Arrays.asList(clazz.getDeclaredFields() ) );

        /**
         * 递归调用 注意点
         */

        Class superClazz = clazz.getSuperclass();

        if ( superClazz != null ) {
            getFields( fieldList, superClazz );
        }

        return fieldList;
    }
}
