package com.jingtong.zhgj.ops.log.enums;

public enum AttributeName {

    NORMAL(0),
    PLANID(1),
    PLANNAME(2),
    ORGANIZATION(3),
    ID(4),
    IGNORE(5);

    private int tag;

    public int getTag() {
        return tag;
    }

    AttributeName(int tag) {
        this.tag = tag;
    }

}
