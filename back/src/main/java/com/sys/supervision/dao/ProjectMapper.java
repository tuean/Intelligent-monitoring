package com.sys.supervision.dao;

import com.sys.supervision.entity.db.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> getAll();

    int countByUnitId(String unitId);

    void updateNumberByProjectCode(@Param("projectId") Integer projectId,
                                   @Param("count") Integer count);
}