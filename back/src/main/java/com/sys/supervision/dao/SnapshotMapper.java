package com.sys.supervision.dao;

import com.sys.supervision.entity.db.Snapshot;

public interface SnapshotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Snapshot record);

    int insertSelective(Snapshot record);

    Snapshot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Snapshot record);

    int updateByPrimaryKey(Snapshot record);

    int countByDevCode(String devCode);

    Snapshot selectByDevCode(String devCode);

    void updateByDevCode(Snapshot snapshot);

    void deleteByDevCode(String devCode);
}