package com.sys.supervision.enums;

/**
 * 账号状态
 */
public enum AccountStatus {

    Normal(0, "启用"),

    Abandon(1, "关闭")

    ;


    private Integer code;

    private String showValue;

    AccountStatus(Integer code, String showValue) {
        this.code = code;
        this.showValue = showValue;
    }

    public static String getShowValueByCode(Integer code) {
        if (code == null) return null;
        for (AccountStatus a : values()) {
            if (a.code == code) {
                return a.getShowValue();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getShowValue() {
        return showValue;
    }
}
