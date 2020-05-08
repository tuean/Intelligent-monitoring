package com.sys.supervision.web;

import com.sys.supervision.entity.db.Snapshot;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.request.ShotRequest;
import com.sys.supervision.service.IShotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 抓拍设置
 */
@RestController
@Slf4j
public class ShotController {

    @Autowired
    private IShotService shotService;


    @RequestMapping(value = "/shot", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse save(@RequestBody ShotRequest shotRequest) {
        shotService.save(shotRequest);
        return BaseResponse.ok();
    }


}
