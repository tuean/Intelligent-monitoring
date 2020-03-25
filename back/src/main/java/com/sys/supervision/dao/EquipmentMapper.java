package com.sys.supervision.dao;

import com.sys.supervision.entity.db.Equipment;
import com.sys.supervision.model.enhance.EquipGroupByProject;
import com.sys.supervision.model.enhance.EquipmentEnhance;
import com.sys.supervision.model.request.EquipmentListRequest;
import com.sys.supervision.model.response.EquipmentListResponse;

import java.util.List;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    List<Equipment> getAll();

    int count(EquipmentListRequest request);
    List<EquipmentListResponse> query(EquipmentListRequest request);

    List<EquipGroupByProject> getAllGroupByProject();
}