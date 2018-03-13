package com.scyypt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scyypt.entity.ProjectProgress;
import com.scyypt.service.ProjectProgressService;
import com.scyypt.util.Global;
/**
 * 项目进度控制器
 * @Description:  处理项目进度的一系列操作请求
 * @author ChengChuanPing
 * @Time   2018年1月5日下午12:56:43
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class projectProgressController extends BaseController {

	
	private static Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private ProjectProgressService projectProgressService;
	
	/**
	 * 查询全都项目进度
	 * @param response
	 */
	@RequestMapping("findAllProjectProgress")
	@ResponseBody
	public void a(HttpServletResponse response){
		
		List<ProjectProgress> result=projectProgressService.findAllProjectProgress();
		if (result == null) {
			String message = "项目进度获取失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("项目进度获取==========>"+json);
			jsonToPage(jsonResult, response);
		}
	}
	
	
}
