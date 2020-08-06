package com.jingtong.zhgj.ops.log.anno;



import com.jingtong.zhgj.ops.log.enums.AttributeName;
import com.jingtong.zhgj.ops.log.handle.BuiltinTypeHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTag {

    AttributeName attrType() default AttributeName.NORMAL;

    String extendedType() default "";

    BuiltinTypeHandler builtinType() default BuiltinTypeHandler.NORMAL;
}
