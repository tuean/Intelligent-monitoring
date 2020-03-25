package com.sys.supervision.web;

import com.sys.supervision.entity.db.Picture;
import com.sys.supervision.model.BaseListResponse;
import com.sys.supervision.model.request.PicListRequest;
import com.sys.supervision.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PicController {

    @Autowired
    private IPicService picService;


    @RequestMapping(value = "/pic/list", method = RequestMethod.POST, consumes = "application/json")
    public BaseListResponse getList(@RequestBody PicListRequest picListRequest) {
        List<Picture> list = picService.getList(picListRequest);
        return BaseListResponse.okWithList(list, true, list.size());
    }



}
