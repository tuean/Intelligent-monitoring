package com.sys.supervision.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
public class FileUtils {

    private static String PROJECT_PATH = null;

    /**
     * get runtime path of the jar file
     *
     * @return
     */
    public static String getProjectPath() {


        if (PROJECT_PATH == null) {
            File f = new File(System.getProperty("java.class.path"));
            File dir = f.getAbsoluteFile().getParentFile();
            if (dir.toString().length() > 1000) {
                PROJECT_PATH = "/Users/tuean/Desktop/qiangsen/";
            } else {
                PROJECT_PATH = dir.toString();
            }

            File file = new File(PROJECT_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }

        }
        return PROJECT_PATH;
    }

    public static String getTmpPath() {
        return getProjectPath() + File.separator + "tmp";
    }

    public static File getAttachmentFile() {
        String basePath = getProjectPath() + File.separator + "attachment";
        File baseFile = new File(basePath);
        if (!baseFile.exists()) {
            baseFile.mkdir();
        }
        return baseFile;
    }

    public static File save(MultipartFile file) throws IOException {
        File attachmentFile = getAttachmentFile();
        String basePath = attachmentFile.getPath();

        Calendar calendar = Calendar.getInstance();
        String monthDir = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1);
        String dirPath = basePath + File.separator + monthDir;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        String oldFileName = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(oldFileName);
        String newFileName = UUID.randomUUID().toString().replace("-", "");
        String newFilePath = dirFile + File.separator + newFileName;
        File newFile = new File(newFilePath + "." + extension);
        file.transferTo(newFile);
        return newFile;
    }

    /**
     * 递归删除文件夹下所有的文件
     * @param file
     * @param ignoreFile
     */
    public static void deleteAllFilesInFolder(File file, String ignoreFile){
        try{
            if(file.isDirectory()){
                String[] children = file.list();
                for(String x : children){
                    deleteAllFilesInFolder(new File(file, x), ignoreFile);
                }
            }
            if(ignoreFile == null || !ignoreFile.equals(file.getName())){
                file.delete();
                log.info("删除文件 " + file.getName() + "成功");
            }

        }catch (Exception e){
            e.printStackTrace();
            log.info("删除文件 " + file.getName() + "失败");
        }
    }

    public static void deleteFileIfExist(String filePath) {
        if (org.springframework.util.StringUtils.isEmpty(filePath)) return;
        File old = new File(filePath);
        old.deleteOnExit();
    }

}
