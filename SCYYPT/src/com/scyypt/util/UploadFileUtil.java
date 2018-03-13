package com.scyypt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class UploadFileUtil {
	private static Logger logger = Logger.getLogger(UploadFileUtil.class.getName());
//	public static Tip uploadM(MultipartFile file,String folder,HttpServletRequest httpRequest,HttpServletResponse response) {
//		
//		if(file!=null){
//			logger.info("开始上传图片！");
//			
//			// 将图片按日期分开存放，方便管理
//			final String path_segment = "/"+folder+"/" +Timestamp.getNowDateStr("yyyyMMdd");
//			
//			// 存放到web根目录下,如果日期目录不存在，则创建,
//			// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
//			final String path = httpRequest.getSession().getServletContext().getRealPath("/") + path_segment;
//			logger.info("图片保存路径："+path);
//			
//			File dir = new File(path);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}
//			// 以下是真正的上传部分
//			String isSucc = "";
//			
//			// 取得原文件名
//			String originName = file.getOriginalFilename();
//			
//			// 取得文件后缀
//			String fileExt = originName.substring(originName.lastIndexOf(".") + 1);
//			
//			// 按时间戳生成图片文件名
//			//String picture = Timestamp.getNowDateStr("yyyyMMddHHmmss") + "." + fileExt;
//			String picture = Timestamp.getNowDateStr("yyyyMMddHHmmss") + "." + fileExt;
//			
//			File uploadFile = new File(dir, picture);
//			try {
//				IOUtils.copy(file.getInputStream(), new FileOutputStream(uploadFile));
//			} catch (Exception e) {
//				isSucc = e.getMessage();
//			}
//			if(isSucc.equals("")){
//				
//				String baseUrl = "http://" + httpRequest.getServerName() // 服务器地址
//				+ ":" + httpRequest.getServerPort() // 端口号
//				+ httpRequest.getContextPath() // 项目名称
//				+ path_segment;
//				// 将图片路径按xheditor要求的json格式字符串返回
//				String url = baseUrl +"/" +  picture; // upload/images/2012/11_09/20121109223012.jpg
//				//如果是合同，转换成png
//				List<String> imgPdfUrl = new ArrayList<>();
//				if (folder.equals("contract")) {
//					Map<String, Object> map = PDF2PNGUtil.pdf2Image(uploadFile, path, 300);
//					List<String> list = (List<String>) map.get("imgPath");
//					for (String str : list) {
//						imgPdfUrl.add(baseUrl+str);
//					}
//				}
//				
//				return new Tip("上传成功!", Global.SUCCESS ,url,imgPdfUrl);
//			}
//			return new Tip("上传失败！",Global.FAILD);
//		}else{
//			return new Tip("文件格式不正确！",Global.FAILD);
//		}
//	}
	
	
	public static Tip uploadM(MultipartFile file,String folder,HttpServletRequest httpRequest,HttpServletResponse response) {
		
		if(file!=null){
			logger.info("开始上传图片！");
			
			// 将图片按日期分开存放，方便管理
			//final String path_segment = "/"+folder+"/" +Timestamp.getNowDateStr("yyyyMMdd");
			
			final String path_segment = "/"+folder+"/";
			
			// 存放到web根目录下,如果日期目录不存在，则创建,
			// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
			final String path = httpRequest.getSession().getServletContext().getRealPath("/") + path_segment;
			logger.info("图片保存路径："+path);
			
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 以下是真正的上传部分
			String isSucc = "";
			
			// 取得原文件名
			String originName = file.getOriginalFilename();
			
			// 取得文件后缀
			String fileExt = originName.substring(originName.lastIndexOf(".") + 1);
			
			// 按时间戳生成图片文件名
			//String picture = Timestamp.getNowDateStr("yyyyMMddHHmmss") + "." + fileExt;
			String timeName = Timestamp.getNowDateStr("yyyyMMddHHmmss") + "." + fileExt;
			
			logger.info("上传文件名称：" + timeName);
			
			File uploadFile = new File(dir, timeName);
			try {
				IOUtils.copy(file.getInputStream(), new FileOutputStream(uploadFile));
				
				return new Tip("上传成功!", Global.SUCCESS, timeName);
				
			} catch (Exception e) {
				isSucc = e.getMessage();
			}
			 
			return new Tip("上传失败！",Global.FAILD);
		}else{
			return new Tip("文件格式不正确！",Global.FAILD);
		}
	}
	
	
	
	public static Map<String, String> getFilePath(HttpServletRequest httpRequest,HttpServletResponse response){
		Map<String, String> map = new HashMap<>();
		final String path_segment = "/"+"sealPicture"+"/" +Timestamp.getNowDateStr("yyyyMMdd");
		// 存放到web根目录下,如果日期目录不存在，则创建,
		// 注意 request.getRealPath("/") 已经标记为不推荐使用了.
		String path = httpRequest.getSession().getServletContext().getRealPath("/") + path_segment;
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String picture = Timestamp.getNowDateStr("yyyyMMddHHmmss")+".jpg";
		String filePath = path+"/"+ picture;
		String url = "http://" + httpRequest.getServerName() // 服务器地址
		+ ":" + httpRequest.getServerPort() // 端口号
		+ httpRequest.getContextPath() // 项目名称
		+ path_segment + "/" + picture; // upload/images/2012/11_09/20121109223012.jpg
		map.put("filePath", filePath);
		map.put("url", url);
		return map;
	}

}
