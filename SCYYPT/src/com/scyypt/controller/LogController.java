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
import com.scyypt.entity.LogEntity;
import com.scyypt.service.LogService;
import com.scyypt.util.Global;
import com.scyypt.util.PageBean;

/**
 * 日志控制器
 * 
 * @Description:
 * @author ChengChuanPing
 * @Time 2017年12月27日下午2:56:40
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LogController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private LogService logService;

	/**
	 * 删除日志
	 * 
	 * @param response
	 * @param logId
	 *            日志编号
	 */
	@RequestMapping("/delLog")
	@ResponseBody
	public void delLog(HttpServletResponse response, String logId) {

		if (Global.isNull(logId)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		int result = logService.deleteLog(logId);

		if (result > 0) {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("删除日志成功编号==========>" + logId);
			jsonToPage(jsonResult, response);
		} else {
			String message = "删除日志失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}

	}

	/**
	 * 分页查询我的日志
	 * 
	 * @param response
	 * @param userName
	 *            用户名称
	 * @param start
	 *            起始页
	 */
	@RequestMapping("/findLogByPage")
	@ResponseBody
	public void findLogByPage(HttpServletResponse response,HttpServletRequest request, String userName, String start) {

		
		if (Global.isNull(userName)||Global.isNull(start)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}
		
		PageBean<LogEntity> page = new PageBean<LogEntity>();
		
		page.setTotalRecord(logService.findAllCount(userName));// 设置总记录数
		page.getTotalPage(); //获取总页数
		page.setCurrentPage(Integer.parseInt(start));// 设置当前页
		List<LogEntity> result = logService.findLogByPage(userName,(page.getCurrentPage() - 1) * page.getPageSize(),
				page.getPageSize());
		page.setList(result);
		
		if (page.getList() == null) {
			String message = "没有找到您的项目信息";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		} else {
			String jsonResult = Global.getProtocol(JSON.toJSONString(page));
			jsonToPage(jsonResult, response);
		}
	}

}
