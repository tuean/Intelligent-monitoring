package com.sys.supervision.web;

import com.sys.supervision.annotation.NoAccess;
import com.sys.supervision.enums.EquipmentStatusEnum;
import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.model.request.OnlineRequest;
import com.sys.supervision.model.request.UploadRequest;
import com.sys.supervision.model.response.HeartBeatResponse;
import com.sys.supervision.model.response.ScheduledTask;
import com.sys.supervision.service.IEquipmentService;
import com.sys.supervision.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@Slf4j
public class CameraController {

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IPicService picService;

    public static Map<String, Integer> picMap = new HashMap<>();

    @NoAccess
    @RequestMapping(value = "/hw/online", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse online(@RequestBody OnlineRequest request) {
        log.info("{} is going to online", request.getCamera_id());
        equipmentService.create(request.getCamera_id());
        return BaseResponse.ok();
    }


    @NoAccess
    @RequestMapping(value = "/hw/heart_beat", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HeartBeatResponse heartBeat(@RequestBody OnlineRequest request) {
        equipmentService.updateStatus(request.getCamera_id(), EquipmentStatusEnum.OK);
        Integer x = picMap.get(request.getCamera_id());
        boolean flag =  x> 0;
        picMap.put(request.getCamera_id(), x > 0 ? 0 : --x);
        HeartBeatResponse response = new HeartBeatResponse();
        if (flag) {
            response.setShot("True");
            String fakeShotId = UUID.randomUUID().toString();
            log.info("fake shotid is {}", fakeShotId);
            response.setShot_id(fakeShotId);
        }
        ScheduledTask task = new ScheduledTask();
        String fakeTaskId = UUID.randomUUID().toString();
        log.info("fake taskId is {}", fakeTaskId);
        task.setTask_id(fakeTaskId);
        task.setEvery("1.h");
        List<ScheduledTask> taskList = new ArrayList<>();
        taskList.add(task);
        response.setScheduled_task(taskList);
        return response;
    }

    @NoAccess
    @RequestMapping(value = "/hw/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse upload(@RequestBody UploadRequest uploadRequest) {
        picService.store(uploadRequest);
        return BaseResponse.ok();
    }


}

