package com.sys.supervision.task;

import com.sys.supervision.dao.EquipmentMapper;
import com.sys.supervision.dao.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务：
 *
 *  模拟获取项目数据
 */
@Component
public class ProjectTask {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    // 1000ms * 60 * 5 = 5分钟一次
    @Scheduled(initialDelay = 1000, fixedDelay = 1000 * 60 * 60 * 5)
    public void run() {

    }

}
