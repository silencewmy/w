package com.jingtong.zhgj.ops.log.handle;

import com.jingtong.zhgj.ops.log.model.AttributeMode;
import io.swagger.annotations.ApiModel;

@ApiModel("进行日志的针对性记录拓展接口，比如 " +
        "提供的是某个用户的id，但是我们需要更加清楚的记录 ，" +
        "因此记录的是id对应的用户名，在这个方法中去定义如何转换")
public interface BaseExtendedTypeHandler {
    AttributeMode handleAttributeChange(String attrType, String attrName, Object oldValue, Object newValue);


}
