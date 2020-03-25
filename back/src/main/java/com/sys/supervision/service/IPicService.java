package com.sys.supervision.service;

import com.sys.supervision.entity.db.Picture;
import com.sys.supervision.model.request.PicListRequest;

import java.util.List;

public interface IPicService {

    List<Picture> getList(PicListRequest request);

}
