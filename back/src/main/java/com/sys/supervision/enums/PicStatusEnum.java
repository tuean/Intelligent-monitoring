package com.sys.supervision.enums;

/**
 * 图像状态定义
 */
public enum PicStatusEnum {

    HANDLED         (0,         "已处理"),

    UNHANDLED       (1,         "未处理"),

    INVALID         (99,        "无效")


    ;


    private Integer code;

    private String value;

    PicStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }


    public static PicStatusEnum getByCode(Integer code) {
        if (code == null) return null;
        for (PicStatusEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
