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
import com.scyypt.entity.Operation;
import com.scyypt.service.OperationService;
import com.scyypt.util.Global;

/**
 * 菜单功能操作控制器
 * 
 * @Description:
 *
 * @Author 程传平
 *
 * @Time 2018-01-26 20:05
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OperationController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private OperationService operationService;

	/**
	 * 获取菜单功能操作
	 * @Author 程传平
	 * @Time 2018-01-26 20:13
	 *
	 * @param response
	 * @param request
	 */
	@RequestMapping("/findOperation")
	@ResponseBody
	private void findOperation(HttpServletResponse response, HttpServletRequest request) {

		List<Operation> result = operationService.findOperation();

		if (result == null) {

			String message = "操作失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			logger.info("---------菜单功能操作信息--------" + jsonResult);
			jsonToPage(jsonResult, response);
			
		}
	}
}
