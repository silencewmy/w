package com.jingtong.zhgj.ops.log.model;


import com.jingtong.zhgj.framework.security.LoginUser;
import com.jingtong.zhgj.framework.utils.IpUtils;
import com.jingtong.zhgj.framework.utils.ServletUtils;
import com.jingtong.zhgj.ops.log.anno.LogTag;
import com.jingtong.zhgj.ops.log.enums.AttributeName;
import com.jingtong.zhgj.ops.log.enums.LogActEnum;
import com.jingtong.zhgj.ops.log.enums.LogTypeEnum;
import com.jingtong.zhgj.ops.log.enums.SubLogTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel("操作行为的封装，换句话说就是日志记录的内容." +
        "记录日志的行为： 谁 + 操作 + 了什么 + 结果为")
@Data
public class BaseOperationModel {

    @ApiModelProperty("日志的主键，自增长 或者通过唯一性id生成")
    private Long id;

    @ApiModelProperty("记录发生变化的属性，即发生改变的额属性")
    private List<AttributeMode> AttrChangedList = new ArrayList<>();

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

    @ApiModelProperty("操作类型，这里是存的枚举类型，存取数据库时需要转换")
    private String logActType;

    @ApiModelProperty("操作对象类型")
    private String operand;

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



    public void addAttributeModeList ( List<AttributeMode> attributeModeList ) {

        for ( AttributeMode attributeMode : attributeModeList ) {
            addAttrChangeItemMode( attributeMode );
        }
    }

    public void addAttrChangeItemMode ( AttributeMode attributeMode ) {
        this.getAttrChangedList().add( attributeMode );
    }
}
