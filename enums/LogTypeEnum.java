package com.jingtong.zhgj.ops.log.enums;

public enum  LogTypeEnum implements LogEnumInterface {
    WEB(0,"web服务日志"),
    CAG(1,"cag服务日志"),
    PAG(2,"pag服务日志"),
    BUSDISPATCH(3,"busDispatch服务日志")
    ;

    private Integer type;
    private String name;

    private LogTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public static String typeGetName ( Integer type ) {

        for ( LogTypeEnum logTypeEnum : LogTypeEnum.values() ) {
            if ( logTypeEnum.getType().equals(type) ) {
                return logTypeEnum.getName();
            }
        }
        return "";
    }

    public static Integer nameGetType ( String name ) {

        for ( LogTypeEnum logTypeEnum : LogTypeEnum.values() ) {

            if ( logTypeEnum.getName().equals(name) ) {
                return logTypeEnum.getType();
            }
        }

        return null;
    }
}
