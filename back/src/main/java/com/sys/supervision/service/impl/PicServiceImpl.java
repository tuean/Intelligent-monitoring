package com.sys.supervision.service.impl;

import com.sys.supervision.dao.PictureMapper;
import com.sys.supervision.entity.db.Picture;
import com.sys.supervision.enums.PicStatusEnum;
import com.sys.supervision.model.request.PicListRequest;
import com.sys.supervision.model.request.UploadRequest;
import com.sys.supervision.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PicServiceImpl implements IPicService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> getList(PicListRequest request) {

        List<Picture> pictures = pictureMapper.selectByDevCode(request.getId());

        return pictures == null ? new ArrayList<>(0) : pictures;
    }

    @Override
    public void store(UploadRequest uploadRequest) {
        Picture picture = new Picture();
        picture.setCameraId(uploadRequest.getCamera_id());
        picture.setTaskId(uploadRequest.getTask_id());
        picture.setBase64(uploadRequest.getImage());
        picture.setPicStatus(PicStatusEnum.HANDLED.getCode());
        picture.setPicTime(new Date());
        picture.setCreateAt(new Date());
        picture.setModifyAt(new Date());
        picture.setCreater("sys");
        picture.setModifier("sys");
        pictureMapper.insert(picture);
    }
}
