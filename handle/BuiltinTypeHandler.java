package com.jingtong.zhgj.ops.log.handle;


import com.jingtong.zhgj.ops.log.model.AttributeMode;
import com.jingtong.zhgj.ops.log.wrapper.FieldWrapper;

/**
 * 内置的属性处理方式，即向数据库中保存的值
 */
public enum BuiltinTypeHandler {

    NORMAL {

        @Override
        public AttributeMode handleAttributeChange( FieldWrapper fieldWrapper ) {

            /**
             * 直接toString赋值
             */
            AttributeMode attributeMode = new AttributeMode();
            attributeMode.setOldValue( fieldWrapper.getOldValueString() );
            attributeMode.setNewValue( fieldWrapper.getNewValueString() );
            attributeMode.setAttrName( fieldWrapper.getAttrName() );
            attributeMode.setDifferentPoint( fieldWrapper.getAttrName() + ":" + fieldWrapper.getOldValueString() + "--->" + fieldWrapper.getNewValueString() + ";");

            return attributeMode;
        }
    };

    public abstract AttributeMode handleAttributeChange ( FieldWrapper fieldWrapper );
}
