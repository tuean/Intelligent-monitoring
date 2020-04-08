package com.sys.supervision.web;


import com.sys.supervision.dao.EquipmentMapper;
import com.sys.supervision.entity.db.Equipment;
import com.sys.supervision.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LiveController {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @RequestMapping(value = "/url/flv", method = RequestMethod.GET)
    public BaseResponse getFlvUrl(@RequestParam("id") Integer id) {
        Equipment equipment = equipmentMapper.selectByPrimaryKey(id);
        if (equipment == null) throw new IllegalArgumentException();
        return BaseResponse.ok().okWithBody("http://122.227.179.90:23505/live/video.flv?devid=" + equipment.getDevCode() + "&channel=1&devline=2");
    }


}
