package com.jingtong.zhgj.ops.log.vo;

import com.jingtong.zhgj.ops.log.enums.LogActEnum;
import com.jingtong.zhgj.ops.log.enums.LogTypeEnum;
import com.jingtong.zhgj.ops.log.enums.SubLogTypeEnum;
import com.jingtong.zhgj.ops.log.model.AttributeMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("日志相关实体，对应的是前端，与数据库之间隔着一个LogEntity转换")
public class LogVo implements Serializable {

    @ApiModelProperty("日志的主键，自增长 或者通过唯一性id生成")
    private Long id;

    @ApiModelProperty("对数据操作的时间")
    private LocalDateTime createTime;

    @ApiModelProperty("日志类型，枚举")
    private String logType;

    @ApiModelProperty("日志子类型，枚举")
    private String subLogType;

    @ApiModelProperty("线路名称")
    private String lineName;

    @ApiModelProperty("线路编号，用来找到该线路")
    private String lineCode;

    @ApiModelProperty("日志标题")
    private String logTitle;

    @ApiModelProperty("日志内容")
    private String comment;

    @ApiModelProperty("日志备注")
    private String remark;

    @ApiModelProperty("操作对象类型")
    private String operand;

    @ApiModelProperty("操作类型，这里是存的枚举类型，存取数据库时需要转换")
    private String logActType;

    @ApiModelProperty("操作对象标示")
    private String operationId;

    @ApiModelProperty("组织节点id，还不知道是干嘛的")
    private String organization;

    @ApiModelProperty("用户名称")
    private String actorName;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("操作ip")
    private String ipAddress;

    @ApiModelProperty("操作人的id，也就是记录谁操作的,根据该id进行人员的查询")
    private Long actorId;
}
