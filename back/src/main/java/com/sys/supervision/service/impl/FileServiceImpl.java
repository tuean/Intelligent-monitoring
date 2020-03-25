package com.sys.supervision.service.impl;

import com.sys.supervision.config.SysConfig;
import com.sys.supervision.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements IFileService {

    @Override
    public String store(MultipartFile multipartFile) throws IOException {
        String filePath = SysConfig.getFileStorePath();
        String fileName;
        File file;
        String oldFileName = multipartFile.getOriginalFilename();
        String extension = FilenameUtils.getExtension(oldFileName);

        while (true) {
            fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
            file = new File(filePath + fileName);
            if (file.exists()) continue;
            multipartFile.transferTo(file);
            break;
        }

        return fileName;
    }
}
