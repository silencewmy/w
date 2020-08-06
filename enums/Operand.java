package com.jingtong.zhgj.ops.log.enums;

public enum Operand implements LogEnumInterface {
    PEOPLE(0,"人员"),
    BUS(1,"车辆"),
    ORDER(2,"指令")
    ;

    Operand(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private Integer type;
    private String name;

    public static String typeGetName ( Integer type ) {

        for ( Operand operand : Operand.values() ) {
            if ( operand.getType().equals(type) ) {
                return operand.getName();
            }
        }
        return "";
    }

    public static Integer nameGetType ( String name ) {

        for ( Operand operand : Operand.values() ) {

            if ( operand.getName().equals(name) ) {
                return operand.getType();
            }
        }

        return null;
    }
}
