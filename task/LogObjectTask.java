package com.jingtong.zhgj.ops.log.task;


import com.jingtong.zhgj.framework.utils.DateUtil;
import com.jingtong.zhgj.ops.log.config.LogConfig;
import com.jingtong.zhgj.ops.log.enums.*;
import com.jingtong.zhgj.ops.log.handle.BaseExtendedTypeHandler;
import com.jingtong.zhgj.ops.log.handle.BuiltinTypeHandler;
import com.jingtong.zhgj.ops.log.model.AttributeMode;
import com.jingtong.zhgj.ops.log.model.BaseOperationModel;
import com.jingtong.zhgj.ops.log.model.LogArgActorModel;
import com.jingtong.zhgj.ops.log.service.LogService;
import com.jingtong.zhgj.ops.log.wrapper.ClazzWrapper;
import com.jingtong.zhgj.ops.log.wrapper.FieldWrapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@ApiModel("记录日志的行为： 谁 + 操作 + 了什么 + 结果为")
public class LogObjectTask implements Runnable {

    @ApiModelProperty("操作的参数封装类")
    private LogArgActorModel argActorModel;

    @ApiModelProperty("日志备注")
    private String remark;

    @ApiModelProperty("操作对象所属类型，这是告知日志该如何记录")
    private LogEnumInterface operand;

    @ApiModelProperty("操作对象类型，存到数据库中的类型")
    private LogEnumInterface logActEnum;

    @ApiModelProperty("操作类型")
    private LogEnumInterface logTypeEnum;

    @ApiModelProperty("操作行为的类型")
    private LogEnumInterface subLogTypeEnum;

    @ApiModelProperty("操作对象的旧值")
    private Object oldObject;

    @ApiModelProperty("操作对象的新值")
    private Object newObject;

    @ApiModelProperty("日志内容")
    private String logComment;

    //用于功能扩展
    private BaseExtendedTypeHandler baseExtendedTypeHandler;

    private LogService logService;

    public LogObjectTask(LogArgActorModel argActorModel, LogEnumInterface operand, LogEnumInterface logTypeEnum,
                         LogEnumInterface subLogTypeEnum, LogEnumInterface logActEnum,
                         Object oldObject, Object newObject, BaseExtendedTypeHandler baseExtendedTypeHandler, LogService logService) {
        this.argActorModel = argActorModel;
        this.remark = argActorModel.getRemark();
        this.operand = operand;
        this.logTypeEnum = logTypeEnum;
        this.subLogTypeEnum = subLogTypeEnum;
        this.logActEnum = logActEnum;
        this.oldObject = oldObject;
        this.newObject = newObject;
        this.baseExtendedTypeHandler = baseExtendedTypeHandler;
        this.logService = logService;

        this.logComment = argActorModel.getLoginUser().getRealName() +
                "对" + operand.getName() +
                "进行了" + subLogTypeEnum.getName() +
                "操作" + "改动为";
    }

    @Override
    public void run() {
        try {


            BaseOperationModel baseOperationModel = new BaseOperationModel();

            /**
             * 创建时间，这里没有数据库自动生成
             * 日志标题暂时设置为操作类型
             */
            baseOperationModel.setActorId( argActorModel.getLoginUser().getId() );
            baseOperationModel.setActorName( argActorModel.getLoginUser().getUsername() );
            baseOperationModel.setRealName( argActorModel.getLoginUser().getRealName() );
            baseOperationModel.setIpAddress( argActorModel.getIpAddress() );
            baseOperationModel.setRemark( remark );
            baseOperationModel.setOperand( operand.getName() );
            baseOperationModel.setLogActType( logActEnum.getName() );
            baseOperationModel.setLogType( logTypeEnum.getName() );
            baseOperationModel.setSubLogType( subLogTypeEnum.getName() );
            baseOperationModel.setCreateTime( DateUtil.fromDateTime( new Date() ));
            baseOperationModel.setLogTitle( logActEnum.getName() );

            /**
             * 进行属性比较
             */
            Class clazzNew = newObject.getClass();
            Class clazzOld = oldObject.getClass();

            if ( clazzOld.equals(clazzNew) ) {

                ClazzWrapper clazzWrapper = new ClazzWrapper( clazzNew );

                /**
                 * 得到传入对象的所有属性名
                 */
                List<Field> fieldList = clazzWrapper.getFidldList();


                for ( Field field : fieldList ) {
                    //设置属性可以反射访问
                    field.setAccessible( true );

                    FieldWrapper fieldWrapper = new FieldWrapper( field, field.get(oldObject), field.get(newObject) );

                    /**
                     * 如果被@LogTag标注，或者开启了自动记录
                     * fieldWrapper.isWithLogTag() || "true".equals(logConfig.getAutoLogAttributes())
                     */

                    int index;
                    boolean flag = true;

                    if ( fieldWrapper.isWithLogTag() ) {
                        index = fieldWrapper.getLogTag().attrType().getTag();
                    } else {
                        index = 0;
                    }

                    /**
                     * 考虑到每个实体中的线路名称不同 组织结构不同，因此这几个字段使用注解来标注
                     * 因为各个实体的线路id、线路名都不一样，因此通过注解标记
                     */
                    switch ( index ) {

                        case 1:{
                            baseOperationModel.setLineCode( fieldWrapper.getOldValueString() );
                            break;
                        }

                        case 2:{
                            baseOperationModel.setLineName( fieldWrapper.getOldValueString() );
                            break;
                        }

                        case 3:{
                            baseOperationModel.setOrganization( fieldWrapper.getOldValueString() );
                            break;
                        }

                        case 4:{
                            baseOperationModel.setOperationId( fieldWrapper.getOldValueString() );
                            break;
                        }

                        case 5:{
                            flag = false;
                        }

                    }

                    /**
                     * 如果新旧的值不一样，此时就需要进行日志的记录
                     */

                    if ( flag && !nullableEquals( fieldWrapper.getOldValue(), fieldWrapper.getNewValue() ) ) {
                        AttributeMode attributeMode;

                        /**
                         * 对需要日志记录的属性的处理，属性不同的地方构成日志内容在BuiltinTypeHandler类中定义的方法
                         */
                        if ( fieldWrapper.isWithExtendedType() ) {
                            attributeMode = handleExtendedTypeItem( fieldWrapper );
                        } else {
                            attributeMode = handleBuiltinTypeItem( fieldWrapper );
                        }

                        logComment = logComment +  attributeMode.getDifferentPoint() ;

                        if ( attributeMode != null ) {
                            baseOperationModel.addAttrChangeItemMode( attributeMode );
                        }
                    }
                }

                baseOperationModel.setComment( logComment );



                /**
                 * TODO:
                 * 测试使用，待删除
                 */

                {
                    System.out.println( logComment );
                    if ( !baseOperationModel.getAttrChangedList().isEmpty() ) {
                        System.out.println( baseOperationModel.toString() );
                    }
                }

                /**
                 * TODO:缺少返回判断，也就是判断是否入库成功
                 */
                logService.saveLog( baseOperationModel );


            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认的处理方式
     * @param fieldWrapper
     * @return
     */
    private AttributeMode handleBuiltinTypeItem ( FieldWrapper fieldWrapper ) {
        BuiltinTypeHandler builtinTypeHandler = BuiltinTypeHandler.NORMAL;

        if ( fieldWrapper.getLogTag() != null ) {
            builtinTypeHandler = fieldWrapper.getLogTag().builtinType();
        }
        AttributeMode attributeMode = builtinTypeHandler.handleAttributeChange( fieldWrapper );

        return attributeMode;
    }

    private AttributeMode handleExtendedTypeItem ( FieldWrapper fieldWrapper ) {
        /**
         * 定义的接口中的实现方式，普通的TypeHandler.NORMAL为将新旧值对应的字符串进行置换
         */

        AttributeMode attributeMode = baseExtendedTypeHandler.handleAttributeChange(
                fieldWrapper.getAttrName(),
                fieldWrapper.getAttrName(),
                fieldWrapper.getOldValue(),
                fieldWrapper.getNewValue()
        );
        return attributeMode;
    }

    private boolean nullableEquals (Object a, Object b ) {
        return ( a == null & b == null ) || ( a != null && a.equals(b) );
    }
}
