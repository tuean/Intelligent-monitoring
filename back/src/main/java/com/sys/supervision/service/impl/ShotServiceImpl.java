package com.sys.supervision.service.impl;

import com.sys.supervision.config.Constant;
import com.sys.supervision.dao.SnapshotMapper;
import com.sys.supervision.entity.db.Snapshot;
import com.sys.supervision.helper.AccountHelper;
import com.sys.supervision.model.request.ShotRequest;
import com.sys.supervision.model.response.HeartBeatResponse;
import com.sys.supervision.model.response.ScheduledTask;
import com.sys.supervision.service.IShotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ShotServiceImpl implements IShotService {

    @Autowired
    private SnapshotMapper snapshotMapper;


    @Override
    public void save(ShotRequest shotRequest) {
        Assert.notNull(shotRequest.getDevCode(), Constant.ParamErrorMessage);
//        int count = snapshotMapper.countByDevCode(shotRequest.getDevCode());
//        if (count > 0) {
//            Snapshot old = snapshotMapper.selectByDevCode(shotRequest.getDevCode());
//            old.setEvery(shotRequest.getEvery());
//            old.setOn(shotRequest.getOn());
//            old.setModifier(AccountHelper.get().getAccount());
//            old.setModifyAt(new Date());
//            snapshotMapper.updateByPrimaryKeySelective(old);
//        } else {
//
//        }

        snapshotMapper.deleteByDevCode(shotRequest.getDevCode());

        Snapshot snapshot = new Snapshot();
        String taskId = UUID.randomUUID().toString();
        snapshot.setShotImmediately(shotRequest.isShotImmediately() ? "True" : null);
        snapshot.setTaskId(taskId);
        snapshot.setDevCode(shotRequest.getDevCode());
        snapshot.setEvery(shotRequest.getEvery());
        snapshot.setOn(shotRequest.getOn());
        snapshot.setCreater(AccountHelper.get().getAccount());
        snapshot.setCreateAt(new Date());
        snapshot.setModifier(AccountHelper.get().getAccount());
        snapshot.setModifyAt(new Date());
        snapshotMapper.insertSelective(snapshot);
    }

    @Override
    public HeartBeatResponse getByDevCode(String devCode) {
        HeartBeatResponse response = new HeartBeatResponse();
        Snapshot snapshot = snapshotMapper.selectByDevCode(devCode);
        if (snapshot == null) {
            return response;
        }

        response.setShot(snapshot.getShotImmediately());
        response.setShot_id(UUID.randomUUID().toString());
        List<ScheduledTask> scheduledTaskList = new ArrayList<>();
        ScheduledTask scheduledTask = ScheduledTask.builder()
                .task_id(UUID.randomUUID().toString())
                .every(snapshot.getEvery())
                .on(snapshot.getOn())
                .build();
        scheduledTaskList.add(scheduledTask);
        response.setScheduled_task(scheduledTaskList);
        return response;
    }
}
