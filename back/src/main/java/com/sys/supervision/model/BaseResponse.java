package com.sys.supervision.model;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private int code;

    private String message;

    private T body;

    public static BaseResponse ok() {
        return BaseResponse.builder().code(0).message("ok").build();
    }

    public static BaseResponse ok(String info) {
        return BaseResponse.builder().code(0).message(info).build();
    }

    public BaseResponse<T> okWithBody(T body) {
        BaseResponse<T> res = ok();
        res.setBody(body);
        return res;
    }

    public static BaseResponse error() {
        return BaseResponse.builder().code(1).message("error").build();
    }

    public static BaseResponse error(String error) {
        return BaseResponse.builder().code(1).message(error).build();
    }

    public static BaseResponse error(Integer code, String error) {
        return BaseResponse.builder().code(code).message(error).build();
    }

}
