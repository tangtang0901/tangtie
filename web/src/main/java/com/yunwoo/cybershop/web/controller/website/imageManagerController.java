package com.yunwoo.cybershop.web.controller.website;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
public class imageManagerController {
	
	/**  Logger for imageManagerController  */
	private static final Logger logger = LoggerFactory.getLogger(imageManagerController.class);


	/**
	 * 
	 * <p>管理器</br></p>
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/manager")
	@ResponseBody
	public void manager(HttpServletRequest request,HttpServletResponse response) throws Exception {
		 //仅做示例用，请自行修改
		try{
			int pictureLocation=Integer.valueOf(request.getParameter("pictureLocation"));
			String path = "／piclication";
			String imgStr ="";
			String realpath = getRealPath(request,path)+path;
			List<File> files = getFiles(realpath,new ArrayList<File>());
			
			//log.debug(realpath+"realpath");
			for(File file :files ){
				imgStr+=file.getPath().replace(getRealPath(request,path),"")+"ue_separate_ue";
			}
			if(imgStr!=""){
		        imgStr = imgStr.substring(0,imgStr.lastIndexOf("ue_separate_ue")).replace(File.separator, "/").trim();
		    }
			//log.debug(imgStr+"----------------");
			response.getWriter().print(imgStr);
		}
		catch(Exception e){
			logger.error("manager", e); 

			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <p>获取文件列表</br></p>
	 * @param realpath
	 * @param files
	 * @return
	 */
	public List<File> getFiles(String realpath, List<File> files) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for(File file :subfiles ){
				if(file.isDirectory()){
					getFiles(file.getAbsolutePath(),files);
				}else{
					if(!getFileType(file.getName()).equals("")) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}
	
	/**
	 * 
	 * <p>获取实际路径</br></p>
	 * @param request
	 * @param path
	 * @return
	 */
	public String getRealPath(HttpServletRequest request,String path){
		ServletContext application = request.getSession().getServletContext();
		//String str = application.getRealPath(request.getServletPath());
		String returnString = application.getRealPath("");
		return returnString;
		
		/*log.debug("++0"+application.getRealPath("/"));
		
		log.debug("++1"+str);
		
		log.debug("++2"+new File(str).getParent());
		
		return new File(str).getParent();*/
		
		/*ServletContext application = request.getSession().getServletContext();
		String str = application.getRealPath(request.getServletPath());
		log.debug("++1"+str);
		
		log.debug("++2"+new File(str).getParent());
		return new File(str).getParent();*/
	}
	
	/**
	 * 
	 * <p>获取文件类型</br></p>
	 * @param fileName
	 * @return
	 */
	public String getFileType(String fileName){
		String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
		Iterator<String> type = Arrays.asList(fileType).iterator();
		while(type.hasNext()){
			String t = type.next();
			if(fileName.toLowerCase().endsWith(t)){
				return t;
			}
		}
		return "";
	}
}
