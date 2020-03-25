package com.sys.supervision.enums;

/**
 * 项目状态定义
 */
public enum ProjectStatusEnum {

    ON_LINE         (0,         "正常在线"),

    OFF_LINE        (1,         "离线"),

    WARNING         (2,         "预警")

    ;

    private Integer code;

    private String value;


    ProjectStatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static ProjectStatusEnum getByCode(Integer code) {
        if (code == null) return null;
        for (ProjectStatusEnum e : values()) {
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
