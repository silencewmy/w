package com.jingtong.zhgj.ops.log.enums;

public enum LogActEnum implements LogEnumInterface {
    ADD(0,"新增操作"),
    MODIFY(1,"修改操作"),
    DPUNCHIN(2,"调度员补打卡"),
    DRIRQUEST (3,"司机请求"),
    DELETE(4,"删除操作")
    ;

    private Integer type;
    private String name;

    private LogActEnum(Integer type, String name) {
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

        for ( LogActEnum logActEnum : LogActEnum.values() ) {
            if ( logActEnum.getType().equals(type) ) {
                return logActEnum.getName();
            }
        }
        return "";
    }

    public static Integer nameGetType ( String name ) {

        for ( LogActEnum logActEnum : LogActEnum.values() ) {

            if ( logActEnum.getName().equals(name) ) {
                return logActEnum.getType();
            }
        }

        return null;
    }

}
