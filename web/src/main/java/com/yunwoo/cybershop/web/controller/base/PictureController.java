package com.yunwoo.cybershop.web.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.utils.PictureUtils;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.web.service.base.PictureService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Fox on 2017/5/29.
 */
@Controller
@RequestMapping("/picture")
public class PictureController extends WebBaseController {

    private static final Logger log = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile fileUpload, HttpServletRequest request,@RequestParam(value = "location",required = false) String location) throws Exception {
        JSONObject result = new JSONObject();

        if(StringUtils.isBlank(location)){
            location = "";
        }

        //webapp路径
        String webappRootPath = request.getRealPath("");
        //图片MD5,区别每张不同图片
        String digest = DigestUtils.md5Hex(fileUpload.getBytes());
        //文件拓展名
        String extension = PictureUtils.getExtension(fileUpload.getOriginalFilename());
        //图片保存位置
        String pictureLocation = PictureUtils.getPictureRootPath()+location;
        //图片在数据库保存的URL地址
        String picDBUrl = PictureUtils.SPRIT+location+PictureUtils.SPRIT+digest+extension;
        //图片全路径，去除domain
        String fullPathWithoutDomain = pictureLocation+PictureUtils.SPRIT+digest+extension;
        String pictureSavePath = webappRootPath+ fullPathWithoutDomain;
        File file = new File(pictureSavePath);
        //如果图片存在，无需再次保存
        boolean exists = file.exists();
        if(exists){
            PictureDTO pictureDTO = pictureService.getByUrl(picDBUrl);
            if(pictureDTO != null){
                result.put("id",pictureDTO.getId());
                return result;
            }
        }

        //如果不存在，先保存到对应路径
        if(!exists){
            File directory = new File(webappRootPath + pictureLocation);
            if(!directory.exists()) directory.mkdirs();
            fileUpload.transferTo(file);
        }
        //保存到数据库
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setUrl(picDBUrl);
        Integer pictureId = pictureService.save(pictureDTO);
        if(pictureId != null){
            result.put("id",pictureId);
            return result;
        }
        //如果能到这一步，直接异常使上传失败
        throw new RuntimeException();
    }
}
