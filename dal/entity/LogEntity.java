package com.jingtong.zhgj.ops.log.dal.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ApiModel("日志实体，对应的是数据库")
public class LogEntity implements Serializable {
    @ApiModelProperty("日志的主键，自增长 或者通过唯一性id生成")
    private Long id;

    @ApiModelProperty("操作类型，这里是存的枚举类型，存取数据库时需要转换")
    private Integer logActType;

    @ApiModelProperty("日志类型，枚举")
    private Integer logType;

    @ApiModelProperty("日志子类型，枚举")
    private Integer subLogType;

    @ApiModelProperty("组织节点id")
    private String organization;

    @ApiModelProperty("线路编号，用来找到该线路")
    private String lineCode;

    @ApiModelProperty("线路名称")
    private String lineName;

    @ApiModelProperty("操作对象类型")
    private Integer operand;

    @ApiModelProperty("操作对象标示")
    private String operationId;

    @ApiModelProperty("用户名称")
    private String actorName;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("操作人的id，也就是记录谁操作的,根据该id进行人员的查询")
    private Long actorId;

    @ApiModelProperty("操作ip")
    private String ipAddress;

    @ApiModelProperty("对数据操作的时间")
    private LocalDateTime createTime;

    @ApiModelProperty("日志标题")
    private String logTitle;

    @ApiModelProperty("日志备注")
    private String remark;

    @ApiModelProperty("日志内容")
    private String comment;





}
