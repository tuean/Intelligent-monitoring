package com.sys.supervision.enums;

/**
 * 产品说明书上没有说明
 *
 * 先随意定义
 */
public enum EquipmentStatusEnum {

    OK          (0,         "正常"),

    EXCEPTION   (1,         "异常")

    ;


    private Integer code;

    private String value;

    EquipmentStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(Integer code) {
        if (code == null) return "";
        for (EquipmentStatusEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e.getValue();
            }
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
