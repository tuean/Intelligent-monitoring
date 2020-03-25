package com.sys.supervision.web;

import com.sys.supervision.model.BaseResponse;
import com.sys.supervision.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.sys.supervision.config.Constant.FILESAVEERROR;
import static com.sys.supervision.config.Constant.FILEUPLOADERROR;

@RestController
public class FileController {

    @Autowired
    private IFileService fileService;


    public BaseResponse<String> storeFile(@RequestBody MultipartFile file) throws Exception {
        String fileId = null;
        try {
            fileId = fileService.store(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(FILESAVEERROR);
        }
        if (fileId == null) {
            return BaseResponse.error(FILEUPLOADERROR);
        }

        return BaseResponse.ok();
    }

}
