package com.jingtong.zhgj.ops.log.wrapper;


import com.jingtong.zhgj.ops.log.anno.LogTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * 该类是为了对对象中的属性进行对比
 */

@Data
@ApiModel("用于对象属性间比较的封装")
public class FieldWrapper {

    @ApiModelProperty("属性名称")
    private String attrName;

    @ApiModelProperty("旧的 属性值")
    private Object oldValue;

    @ApiModelProperty("新的属性值")
    private Object newValue;

    @ApiModelProperty("属性旧值的字符串")
    private String oldValueString;

    @ApiModelProperty("属性新值的字符串")
    private String newValueString;

    @ApiModelProperty("用来判断是否有注解 @LogTag")
    private boolean withLogTag;

    private LogTag logTag;

    private boolean withExtendedType;

    private String extendedType;

    public FieldWrapper() {
    }

    public FieldWrapper(Field field, Object oldValue, Object newValue ) {
        this.attrName = field.getName();
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.oldValueString = oldValue == null ? "" : oldValue.toString();
        this.newValueString = newValue == null ? "" : newValue.toString();
        //测试
        this.logTag = field.getAnnotation( LogTag.class );
//        this.logTag = field.getAnnotation( LogTag.class ) == null ? null : field.getAnnotation( LogTag.class );
        this.withLogTag = field.getAnnotation( LogTag.class ) != null ;
        this.withExtendedType = withLogTag && logTag.extendedType().length() != 0;
        this.extendedType = withExtendedType ? logTag.extendedType() : null;

    }
}
