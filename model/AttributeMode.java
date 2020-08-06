package com.jingtong.zhgj.ops.log.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("发生变化了的属性")
@Data
public class AttributeMode {

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("没改之前的值")
    private String oldValue;

    @ApiModelProperty("修改之后的值")
    private String newValue;

    private String differentPoint;
}
