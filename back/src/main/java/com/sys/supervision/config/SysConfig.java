package com.sys.supervision.config;

import com.sys.supervision.utils.FileUtils;

import java.io.File;

public class SysConfig {

    public static String getFileStorePath() {
        return FileUtils.getProjectPath() + File.separator + "file" + File.separator;
    }

}
