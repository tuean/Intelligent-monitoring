package com.sys.supervision.web;

import com.sys.supervision.model.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping("/health")
    public BaseResponse healthCheck() {
        return BaseResponse.ok();
    }
}
