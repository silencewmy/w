package com.jingtong.zhgj.ops.log.handle;

import com.jingtong.zhgj.ops.log.model.BaseOperationModel;


public interface BasePackagingOperation {

    BaseOperationModel packagingOperation(String attrType, String attrName, Object oldValue, Object newValue);
}
