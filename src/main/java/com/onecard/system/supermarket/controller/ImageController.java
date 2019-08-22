package com.onecard.system.supermarket.controller;

import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonErrorResponse;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.Util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/apis")
public class ImageController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private String path="D:\\file";

    /**
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("/upload")
    public BaseResponse upload(@RequestParam("file") MultipartFile file,Map<String, Object> map){
        String filename = file.getOriginalFilename();
        String prefix=filename.substring(filename.lastIndexOf("."));
        String ImageName=StringUtils.randomUUID()+prefix;
        if (FileUtils.upload(file,path, ImageName)){
            BaseJsonResponse response = new BaseJsonResponse();
            response.setMessage("上传成功！");
            response.setDataResult(ImageName);
            return response;
        }else {
            return new CommonErrorResponse("上传失败！");
        }
    }
}
