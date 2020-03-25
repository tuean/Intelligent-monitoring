package com.sys.supervision.web;

import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.service.IMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MonitorController {

    @Autowired
    private IMonitorService monitorService;

    @GetMapping("/monitor")
    public BaseResponse getMonitorInfo() {
        return new BaseResponse().okWithBody(monitorService.getMonitorInfo());
    }





}
