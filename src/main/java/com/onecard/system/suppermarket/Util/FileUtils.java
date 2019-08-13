package com.onecard.system.suppermarket.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * @param file 文件
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file,String path, String fileName){
        //使用原文件名
        String realPath = path + "\\" + fileName;
        File dest = new File(realPath+fileName);
        //判断文件父目录是否存在
        File dir= dest.getParentFile();
        if(!dest.getParentFile().exists()){
            dir.mkdirs();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
