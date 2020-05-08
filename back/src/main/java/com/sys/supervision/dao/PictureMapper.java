package com.sys.supervision.dao;

import com.sys.supervision.entity.db.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKeyWithBLOBs(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> selectByDevCode(String id);
}