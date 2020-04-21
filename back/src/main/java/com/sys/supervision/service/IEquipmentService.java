package com.sys.supervision.service;

import com.sys.supervision.enums.EquipmentStatusEnum;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.request.EquipmentListRequest;
import com.sys.supervision.model.response.EquipmentListResponse;

public interface IEquipmentService {

    String create(String id);

    BaseListResponse<EquipmentListResponse> getList(EquipmentListRequest request);

    void delete(Integer id);

    BaseResponse getEquipGroupByProject();

    void updateStatus(String id, EquipmentStatusEnum equipmentStatusEnum);

}
