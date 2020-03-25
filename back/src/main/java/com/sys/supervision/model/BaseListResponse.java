package com.sys.supervision.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseListResponse<T> extends BaseResponse<T> {

    private boolean finished;

    private Integer total;

    private String hash;

    public static BaseListResponse ok() {
        BaseListResponse response = new BaseListResponse<>();
        response.setCode(0);
        response.setMessage("ok");
        return response;
    }

    public static BaseListResponse okWithList(List list, boolean finished, Integer total) {
        BaseListResponse<List> response = new BaseListResponse<>();
        response.setCode(0);
        response.setMessage("ok");
        response.setBody(list);
        response.setFinished(finished);
        response.setTotal(total);
        return response;
    }

    public static BaseListResponse okWithList(List list, String hash, Integer total) {
        BaseListResponse<List> response = new BaseListResponse<>();
        response.setCode(0);
        response.setMessage("ok");
        response.setBody(list);
        response.setFinished(false);
        response.setHash(hash);
        response.setTotal(total);
        return response;
    }


}
