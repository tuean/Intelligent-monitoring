package com.sys.supervision.enums;

public enum RoleEnum {

    /**
     * 总管理员 可以看所有的
     */
    ADMIN               (0,         "管理员"),

    /**
     * 普通用户
     */
    REGULAR_USER        (1,         "普通用户"),

    ;

    private Integer code;

    private String value;

    RoleEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(Integer code) {
        RoleEnum roleEnum = getByCode(code);
        return roleEnum == null ? "" : roleEnum.getValue();
    }

    public static RoleEnum getByCode(Integer code) {
        if (code == null) return null;
        for (RoleEnum r : values()) {
            if (code.equals(r.getCode())) {
                return r;
            }
        }
        return null;
    }

    public static boolean check(Integer code, RoleEnum[] roleEnums) {
        if (code == null || roleEnums == null) return false;
        for (RoleEnum r : roleEnums) {
            if (code > r.getCode()) {
                return true;
            }
        }
        return false;
    }

    public static boolean check(Integer code) {
        if (code == null) {
            return false;
        }

        for (RoleEnum e : values()) {
            if (e.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }


    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
