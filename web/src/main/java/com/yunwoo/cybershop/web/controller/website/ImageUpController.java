package com.yunwoo.cybershop.web.controller.website;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.yunwoo.cybershop.dto.PictureDTO;
import com.yunwoo.cybershop.dubbo.PictureQueryDubboService;
import com.yunwoo.cybershop.utils.PictureUtils;
import com.yunwoo.cybershop.utils.StringUtils;
import com.yunwoo.cybershop.utils.Uploader;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 
 * <p>ueditor编辑器</br>ueditor编辑器控制类</p>
 * @author shenliang Email:shenliang@co-mall.com
 * @company 北京科码先锋软件技术有限公司@版权所有
 * @version  
 * @since 2015年6月16日下午6:38:54
 */
@Controller
@RequestMapping("/ueditor")
public class ImageUpController {
	
	/**  Logger for this class */
	private static final Logger logger = LoggerFactory.getLogger(ImageUpController.class);

	@Reference(version = "1.0.0",check = true)
	private PictureQueryDubboService pictureQueryDubboService;


	/**
	 * 
	 * <p>上传图片</br></p>
	 * <p>1 获取图片存储目录</br></p>
	 * <p>2 生成图片摘要:</br></p>
	 * <p>按照MD5(32位16进制字符，128位MD5)摘要生成文件名，确保没有重复文件:</br></p>
	 * <p>对于10万条数据，冲突概率是约为10的28次方分之一 3 保存原图，生成水印图和缩略图</br></p>
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request,
			HttpServletResponse response,@RequestParam(value = "pictureLocation",required = false) String location,@RequestParam("upfile") MultipartFile fileUpload) {
		// /----------------
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(location)){
			location = "";
		}

		try {

			//webapp路径
			String webappRootPath = request.getRealPath("");
			//图片MD5,区别每张不同图片
			String digest = DigestUtils.md5Hex(fileUpload.getBytes());
			//文件拓展名
			String extension = PictureUtils.getExtension(fileUpload.getOriginalFilename());
			//图片保存位置
			String pictureLocation = PictureUtils.getPictureRootPath() + location;
			//图片在数据库保存的URL地址
			String picDBUrl = PictureUtils.SPRIT + location + PictureUtils.SPRIT + digest + extension;
			//图片全路径，去除domain
			String fullPathWithoutDomain = pictureLocation + PictureUtils.SPRIT + digest + extension;
			String pictureSavePath = webappRootPath + fullPathWithoutDomain;
			File file = new File(pictureSavePath);
			//如果图片存在，无需再次保存
			boolean exists = file.exists();
			if (exists) {
				PictureDTO pictureDTO = pictureQueryDubboService.getByUrl(picDBUrl);
				if (pictureDTO != null) {
					result.put("title", fileUpload.getName());
					result.put("original", fileUpload.getOriginalFilename());
					result.put("state", "SUCCESS");
					result.put("url", pictureDTO.getUrl());
					return result.toJSONString();
				}
			}
			//如果不存在，先保存到对应路径
			if (!exists) {
				File directory = new File(webappRootPath + pictureLocation);
				if (!directory.exists()) directory.mkdirs();
				fileUpload.transferTo(file);
			}
			//保存到数据库
			PictureDTO pictureDTO = new PictureDTO();
			pictureDTO.setUrl(picDBUrl);
			Integer pictureId = pictureQueryDubboService.save(pictureDTO);
			if (pictureId != null) {
				result.put("title", fileUpload.getName());
				result.put("original", fileUpload.getOriginalFilename());
				result.put("state", "SUCCESS");
				result.put("url", pictureDTO.getUrl());
				return result.toJSONString();
			}
			//如果能到这一步，直接返回失败
			result.put("title", fileUpload.getName());
			result.put("original", fileUpload.getOriginalFilename());
			result.put("state", "ERROR");
			return result.toJSONString();
		}
		catch (Exception e){
			e.printStackTrace();
			result.put("title", fileUpload.getName());
			result.put("original", fileUpload.getOriginalFilename());
			result.put("state", "ERROR");
			return result.toJSONString();
		}
	}

	/**
	 * 
	 * <p>更新</br></p>
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/imageUp")
	@ResponseBody
	public void imageUp(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding(Uploader.ENCODEING);
		response.setCharacterEncoding(Uploader.ENCODEING);

		// 加载配置文件
		// String propertiesPath = ClassLoader.getSystemResource("");
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream(
					"/ueditor.config.properties"));
		} catch (Exception e) {
			logger.error("imageUp", e); 

			// 加载失败的处理
			e.printStackTrace();
		}

		List<String> savePath = Arrays.asList(properties
				.getProperty("savePath").split(","));

		// 获取存储目录结构
		if (request.getParameter("fetch") != null) {

			response.setHeader("Content-Type", "text/javascript");

			// 构造json数据
			Iterator<String> iterator = savePath.iterator();

			String dirs = "[";
			while (iterator.hasNext()) {

				dirs += "'" + iterator.next() + "'";

				if (iterator.hasNext()) {
					dirs += ",";
				}

			}
			dirs += "]";
			response.getWriter().print("updateSavePath( " + dirs + " );");
			return;

		}

		Uploader up = new Uploader(request);

		// 获取前端提交的path路径
		String dir = request.getParameter("dir");

		// 普通请求中拿不到参数， 则从上传表单中拿
		if (dir == null) {
			dir = up.getParameter("dir");
		}

		if (dir == null || "".equals(dir)) {

			// 赋予默认值
			dir = savePath.get(0);

			// 安全验证
		} else if (!savePath.contains(dir)) {

			response.getWriter().print(
					"{'state':'\\u975e\\u6cd5\\u4e0a\\u4f20\\u76ee\\u5f55'}");
			return;

		}
		up.setSavePath(dir);
		String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		up.setAllowFiles(fileType);
		up.setMaxSize(500 * 1024); // 单位KB
		up.upload();
		response.getWriter().print(
				"{'original':'" + up.getOriginalName() + "','url':'"
						+ up.getUrl() + "','title':'" + up.getTitle()
						+ "','state':'" + up.getState() + "'}");
	}
}
