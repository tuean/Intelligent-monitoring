package com.sys.supervision.web;

import com.sys.supervision.annotation.AuthLevel;
import com.sys.supervision.enums.RoleEnum;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.request.EquipmentListRequest;
import com.sys.supervision.model.response.EquipmentListResponse;
import com.sys.supervision.service.IEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @AuthLevel(RoleEnum.ADMIN)
    @RequestMapping(value = "/equip/list", method = RequestMethod.POST, consumes = "application/json")
    public BaseListResponse<EquipmentListResponse> getList(@RequestBody EquipmentListRequest request) {
        return equipmentService.getList(request);
    }

    @AuthLevel(RoleEnum.ADMIN)
    @RequestMapping(value = "/equip", method = RequestMethod.DELETE)
    public BaseResponse delete(@RequestParam("id") Integer id) {
        equipmentService.delete(id);
        return BaseResponse.ok();
    }

    @AuthLevel(RoleEnum.ADMIN)
    @RequestMapping(value = "/equip/group", method = RequestMethod.GET)
    public BaseResponse getEquipByMap() {
        return equipmentService.getEquipGroupByProject();
    }

}
