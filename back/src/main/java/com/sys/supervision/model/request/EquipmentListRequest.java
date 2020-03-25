package com.sys.supervision.model.request;

import com.sys.supervision.model.BaseListRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentListRequest extends BaseListRequest {

    /**
     * 项目名称
     */
    private String name;



}
