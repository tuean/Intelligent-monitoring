package com.sys.supervision.service;

import com.sys.supervision.entity.db.Snapshot;
import com.sys.supervision.model.request.ShotRequest;
import com.sys.supervision.model.response.HeartBeatResponse;

public interface IShotService {

    void save(ShotRequest shotRequest);

    HeartBeatResponse getByDevCode(String devCode);



}
