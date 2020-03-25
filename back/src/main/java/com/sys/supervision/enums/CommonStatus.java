package com.sys.supervision.enums;

public enum CommonStatus {

    OK          (0,     "正常"),

    INVALID     (1,     "失效")

    ;

    private Integer code;

    private String value;

    CommonStatus(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValueByCode(Integer code) {
        if (code == null) return "";
        for (CommonStatus c : values()) {
            if (c.getCode().equals(code)) {
                return c.getValue();
            }
        }
        return "";
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

    public Integer getCode() {
        return code;
    }
}
