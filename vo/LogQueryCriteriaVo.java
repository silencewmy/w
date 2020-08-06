package com.jingtong.zhgj.ops.log.vo;

import com.jingtong.zhgj.ops.log.anno.LogTag;
import com.jingtong.zhgj.ops.log.enums.AttributeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel("日志查询条件封装类")
public class LogQueryCriteriaVo implements Serializable {

    @ApiModelProperty("日志类型，枚举")
    private String logType;

    @ApiModelProperty("线路名称")
    private String lineName;

    @ApiModelProperty("开始时间")
    LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    @LogTag( attrType = AttributeName.IGNORE )
    LocalDateTime endTime;
}
