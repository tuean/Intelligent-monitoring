package com.sys.supervision.service.impl;

import com.sys.supervision.dao.PictureMapper;
import com.sys.supervision.entity.db.Picture;
import com.sys.supervision.model.request.PicListRequest;
import com.sys.supervision.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PicServiceImpl implements IPicService {

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public List<Picture> getList(PicListRequest request) {

        List<Picture> pictures = pictureMapper.selectByEquipId(request.getId());

        return pictures == null ? new ArrayList<>(0) : pictures;
    }
}
