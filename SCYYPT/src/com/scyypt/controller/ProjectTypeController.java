package com.scyypt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.scyypt.entity.ProjectType;
import com.scyypt.service.ProjectTypeService;
import com.scyypt.util.Global;

/**
 * 项目类型控制器
 * 
 * @ClassName: Project_TypeController
 * @Description: TODO(负责处理用户请求项目类型的一系列操作)
 * @author ChengChuanPing
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjectTypeController extends BaseController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());
	@Autowired
	private ProjectTypeService project_TypeService;

	/**
	 * 查询所有项目类型
	 * 
	 * @return Object
	 */
	@RequestMapping("/findAllProjectType")
	@ResponseBody
	public void findAll(HttpServletResponse response) {
		logger.info("开始执行查询项目类型");
		List<ProjectType> list = project_TypeService.findAll();

		if (list ==null) {
			String message = "查询项目类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(list);
			String jsonResult = Global.getProtocol(json);
			logger.info("查询项目类型==========>"+json);
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 增加项目类型
	 * 
	 * @param response
	 * @param project_Type
	 * 
	 */
	@RequestMapping("/addProjectType")
	public void addProject(HttpServletResponse response, ProjectType project_Type) {
		if(Global.isNull(project_Type)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = project_TypeService.addProject_Type(project_Type);
		if (result == 0) {
			String message = "添加项目类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("添加项目类型==========>"+json);
			jsonToPage(jsonResult, response);
		}
	}

	/**
	 * 修改项目类型
	 * 
	 * @param response
	 * @param project_Type
	 *            项目类型对象
	 * 
	 */
	@RequestMapping("/updateProjectType")
	public void updateProject_Type(HttpServletResponse response, ProjectType project_Type) {
		if(Global.isNull(project_Type)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		
		int result = project_TypeService.updateProject_Type(project_Type);
		if (result == 0) {
			String message = "修改项目类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			logger.info("修改"+json+"条项目类型===>"+"类型编号为:"+project_Type.getProjectType_id());
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);
		}

	}

	/**
	 * 根据项目类型Id删除项目类型
	 * @param response
	 * @param projectType_id 项目类型编号
	 * 
	 */
	@RequestMapping("/delProjectType")
	public void delProjectType(HttpServletResponse response, String projectType_id) {
	
		if(Global.isNull(projectType_id)){
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
			return;
		}
		int result = project_TypeService.del(projectType_id);
		if (result == 0) {
			String message = "删除项目类型失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message,true);
			jsonToPage(protocol, response);
		} else {
			String json = JSON.toJSONString(result);
			logger.info("删除"+json+"条项目类型===>"+"类型编号为:"+projectType_id);
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);
		}
	}
}
