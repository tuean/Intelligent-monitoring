package com.sys.supervision.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String store(MultipartFile multipartFile) throws IOException;


}
