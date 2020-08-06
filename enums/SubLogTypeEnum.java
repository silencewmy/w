package com.jingtong.zhgj.ops.log.enums;

public enum SubLogTypeEnum implements LogEnumInterface {
    WEBSTART(0,"web启动完成"),
    WEBSTOP(1,"web停止"),
    CAGSTART(2,"cag启动服务"),
    CAGSTOP(3,"cag停止服务"),

    PAGSTART(4,"pag启动服务"),
    PAGSTOP(5,"pag停止服务"),
    DEVICEREGISORY(6,"设备注册"),
    DEVICEAUTHO(7,"设备鉴权"),
    BUSIREGISTORY(8,"业务登记"),
    DEPARTURENOTICE(9,"发车通知"),
    UPGRADNOTICE(10,"升级通知"),
    CANCELUPGRADE(11,"取消升级"),
    CHANGEDRIVERPLAN(12,"行车计划变更"),
    DIVICELOGOFF(13,"设备注销"),
    EQUIPMENTMANDATORY(14,"设备强制下线"),
    DRIVERATTENDANCE(15,"司机考勤"),
    TRAVELPLANREQUEST(16,"行车计划请求")

    //TODO:还有几个子类型没有定义，原型图中 系统运维-》服务日志中的页面右侧
    ;

    private Integer type;
    private String name;

    private SubLogTypeEnum(Integer type, String name) {
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

        for ( SubLogTypeEnum subLogTypeEnum : SubLogTypeEnum.values() ) {
            if ( subLogTypeEnum.getType().equals(type) ) {
                return subLogTypeEnum.getName();
            }
        }
        return "";
    }

    public static Integer nameGetType ( String name ) {

        for ( SubLogTypeEnum subLogTypeEnum : SubLogTypeEnum.values() ) {

            if ( subLogTypeEnum.getName().equals(name) ) {
                return subLogTypeEnum.getType();
            }
        }

        return null;
    }
    
}
