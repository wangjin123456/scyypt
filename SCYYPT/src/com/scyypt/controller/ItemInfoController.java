/**
 * 
 */
package com.scyypt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scyypt.bean.ItemInfoBean;
import com.scyypt.entity.InfoProvider;
import com.scyypt.entity.ItemInfoEntity;
import com.scyypt.entity.ItemProvider;
import com.scyypt.entity.ProjectAttention;
import com.scyypt.entity.RelevanceProjects;
import com.scyypt.service.ICEInterfaceService;
import com.scyypt.service.InfoProviderService;
import com.scyypt.service.ItemInfoService;
import com.scyypt.service.ItemProviderService;
import com.scyypt.service.ProjectAttentionService;
import com.scyypt.service.RelevanceProjectsService;
import com.scyypt.util.Global;
import com.scyypt.util.PageBean;

/**
 * 项目控制器
 * 
 * @Description: TODO(负责处理用户请求项目的一系列操作)
 * @author ChengChuanPing
 * @Time 2017年12月14日下午7:40:06
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ItemInfoController extends BaseController {
	private static Logger logger = Logger.getLogger(LoginController.class.getName());

	@Autowired
	private ItemInfoService itemInfoService;

	@Autowired
	private InfoProviderService infoProviderService;

	@Autowired
	private ItemProviderService itemProviderService;

	@Autowired
	private ProjectAttentionService projectAttentionService;

	@Autowired
	private RelevanceProjectsService relevanceProjectsService;

	@Autowired
	private ICEInterfaceService iceInterfaceService;

	/**
	 * 新增项目信息
	 * 
	 * @param response
	 * @param itemInfo
	 */
	@RequestMapping("addItemInfo")
	@ResponseBody
	public void addItemInfo(HttpServletResponse response, ItemInfoBean itemInfo) {

		String result = "1"; // 标识执行结果成功

		if (itemInfo == null) {
			String message = "前端传输数据异常";
			result = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(result, response);
		}

		List<Integer> resultp = new ArrayList<>(); // 用于接收项目关注人添加结果

		List<Integer> attentionId = new ArrayList<>();// 用于保存项目关注编号

		String strA = itemInfo.getItemAttention();// 关注人编号

		String[] sourceStrArray = strA.split("\\$");// 以$符号拆分字符串

	
		String r_ProjectsId = itemInfo.getItemRelevancy();// 关联项目编号

		String[] strArray = r_ProjectsId.split("\\$");// 以$符号拆分字符串

		String r_ItemName = itemInfo.getRelevanceItemName();// 关联项目名称

		String[] s_Array = r_ItemName.split("\\$");// 以$符号拆分字符串

		List<Integer> rs = new ArrayList<>();// 接收新增关联项目的执行结果

		List<Integer> relevanceId = new ArrayList<>();// 接收获取新增项目关联编号
		System.out.println(strArray.length);
		if (!r_ProjectsId.equals("")) {
			for (int i = 0; i < strArray.length; i++) {
				RelevanceProjects rp = new RelevanceProjects();
				rp.setRelevanceProjectsId(Integer.parseInt(strArray[i]));
				rp.setItemName(s_Array[i]);
				rs.add(relevanceProjectsService.addRelevanceProjects(rp));
				if (rs.size() < 1) {
					String message = "关联项目添加失败";
					logger.info("关联项目添加失败");
					String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
					jsonToPage(protocol, response);
				} else {
					relevanceId.add(rp.getRelevanceId()); // 获取新增项目关联编号
					logger.info("新增加的关联项目编号为" + relevanceId);
				}
			}
		}
		ItemInfoEntity itemInfoE = new ItemInfoEntity(itemInfo.getUserId(), itemInfo.getUserName(),
				itemInfo.getItemCooperation(), itemInfo.getFlag(), itemInfo.getItemName(), itemInfo.getProvince(),
				itemInfo.getCity(), itemInfo.getDistrict(), itemInfo.getItemAddress(), itemInfo.getItemaArea(),
				itemInfo.getItemJoinArea(), itemInfo.getItemExplain(), itemInfo.getItemCreateDatetime(),
				Integer.parseInt(itemInfo.getItemProgress()), itemInfo.getItemFollowDatetime(),
				Integer.parseInt(itemInfo.getItemUnmarked()));

		int addResult = itemInfoService.add(itemInfoE);// 插入项目表数据

		if (addResult == 0) {

			String message = "新增项目失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);

		} else {

			String json = JSON.toJSONString(addResult);
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);

		}

		int itemId = itemInfoE.getItemId(); // 获取新增项目编号
		logger.info("新增加的项目编号为:" + itemId);
		
		String strp = itemInfo.getProjectPeoName();// 关注人姓名
		String[] stp = strp.split("\\$");
		System.out.println(sourceStrArray.length);
		if (!strA.equals("")) {
			for (int i = 0; i < sourceStrArray.length; i++) {
				ProjectAttention projectAttention = new ProjectAttention();
				projectAttention.setItemId(itemId);
				projectAttention.setAccountUuid(sourceStrArray[i]);
				projectAttention.setName(stp[i]);
				resultp.add(projectAttentionService.addProjectAttention(projectAttention));
				if (resultp.size() < 1) {
					String message = "关注人添加失败";
					logger.info("关注人添加失败");
					String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
					jsonToPage(protocol, response);
				} else {
					attentionId.add(projectAttention.getAttentionId()); // 获取新增项目关注编号
					logger.info("新增加的项目编号为" + attentionId);
				}
			}
		}
		
		
		List<Integer> providerId = new ArrayList<Integer>();

		List<Integer> result2 = new ArrayList<Integer>();

		List<Integer> result3 = new ArrayList<Integer>();

		String sjson = itemInfo.getSecretLairs().toString();

		logger.info(sjson);

		JSONArray jArr = JSONArray.parseArray(sjson);
		for (int i = 0; i < jArr.size(); i++) {
			JSONObject objc = jArr.getJSONObject(i);

			String providerName = objc.getString("informationPeople");
			String providerTel = objc.getString("informationPhone");

			InfoProvider infoProvider = new InfoProvider();
			infoProvider.setProviderName(providerName);
			infoProvider.setProviderTel(providerTel);
			result2.add(infoProviderService.addInfoProvider(infoProvider));// 插入信息提供表数据
			if (result2.size() == 0) {
				String message = "新增信息提供者失败";
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			} else {
				providerId.add(infoProvider.getProviderId()); // 获取新增信息提供者编号
				logger.info("新增加的项目编号为" + providerId);
			}
		}

		int num1 = providerId.size();
		int num2 = attentionId.size();
		int num3 = relevanceId.size();
		int list[] = { num1, num2, num3 };
		int max = 0;// 保存集合最大的一个作为循环条件
		for (int i = 0; i < max; i++) {
			if (max < list[i]) {
				max = list[i];
			}
		}

		for (int i = 0; i < max; i++) {
			ItemProvider itemProvider = new ItemProvider();
			itemProvider.setItemId(itemId);
			if (providerId.size() > 0) {

				itemProvider.setProviderid(providerId.get(i));
			}

			if (attentionId.size() > 0) {
				itemProvider.setAttentionId(attentionId.get(i));
			}
			if (relevanceId.size() > 0) {

				itemProvider.setRelevanceId(relevanceId.get(i));
			}

			result3.add(itemProviderService.addItemProvider(itemProvider));// 插入关系表数据
			if (result3.size() == 0) {
				String message = "新增项目关系失败";
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message);
				jsonToPage(protocol, response);
			}
		}
		// 判断成功还是失败
		if (addResult > 0 && result2.size() > 0 && result2.size() > 0) {
			String json = JSON.toJSONString(result);
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);
		} else {
			String message = "新增项目失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}

	}

	/**
	 * 说明 查询ICE用户，用于添加关注人
	 * 
	 * @author zhao
	 * 
	 * @param request
	 * @param response
	 * @param skey
	 * 
	 */

	@RequestMapping("/doSearchUser")
	@ResponseBody
	public void searchUser(HttpServletRequest request, HttpServletResponse response, String accountUuid, String skey) {

		if (Global.isNull(skey)) {

			String message = "";

			// 如果 skey 为空，不准进行查询操作，返回空包体
			String resultProtocol = Global.getProtocol(Global.ICE_OK, message, false);

			jsonToPage(resultProtocol, response);
		}

		String resultProtocol = this.iceInterfaceService.doUserSearchKey(accountUuid, skey);

		jsonToPage(resultProtocol, response);
	}

	/**
	 * 说明 查询ICE行政区， 返回省级列表，市级列表，区级列表
	 * 
	 * @author zhao
	 * 
	 * @param request
	 * @param response
	 * @param skey
	 * 
	 */

	@RequestMapping("/doRegioms")
	@ResponseBody
	public void doRegioms(HttpServletRequest request, HttpServletResponse response, String accountUuid, String pid) {

		String resultProtocol = this.iceInterfaceService.getRegioms(accountUuid, pid);

		jsonToPage(resultProtocol, response);
	}

	/**
	 * 分页查询我的项目信息
	 * 
	 * @param response
	 * @param itemProgress
	 *            项目进度
	 * @param context
	 *            搜索条件
	 * @param userId
	 *            用户编号
	 * @param start
	 *            分页起始页
	 * @param itemUnmarked
	 *            保存还是草稿 1保存,2草稿
	 */
	@RequestMapping("/findAllItemInfoByPage")
	@ResponseBody
	private void findAllItemInfoByPage(HttpServletResponse response, String itemProgress, String context, String userId,
			int start, String itemUnmarked) {
		String jindu = null;
		String text = null;
		String message = "没有找到您的项目信息";
		PageBean<ItemInfoEntity> page = new PageBean<ItemInfoEntity>();
		if (!itemProgress.equals("null") && !context.equals("null")) {
			page.setTotalRecord(itemInfoService.findCount(userId, itemProgress, context, itemUnmarked));// 设置总记录数
		} else if (!itemProgress.equals("null")) {
			page.setTotalRecord(itemInfoService.findCount(userId, itemProgress, text, itemUnmarked));// 设置总记录数
		} else if (!context.equals("null")) {
			page.setTotalRecord(itemInfoService.findCount(userId, jindu, context, itemUnmarked));// 设置总记录数
		} else {
			page.setTotalRecord(itemInfoService.findCount(userId, jindu, text, itemUnmarked));// 设置总记录数
		}

		page.getTotalPage();
		page.setCurrentPage(start);// 设置当前页
		if (!itemProgress.equals("null") && !context.equals("null")) {
			List<ItemInfoEntity> result = itemInfoService.findAllByPage(itemProgress, context,
					(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize(), userId, itemUnmarked);
			page.setList(result);
			if (page.getList() == null) {
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			} else {
				String jsonResult = Global.getProtocol(JSON.toJSONString(page));
				jsonToPage(jsonResult, response);
			}

		} else if (!itemProgress.equals("null")) {
			List<ItemInfoEntity> result = itemInfoService.findAllByPage(itemProgress, text,
					(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize(), userId, itemUnmarked);
			page.setList(result);
			if (page.getList() == null) {

				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			} else {
				String jsonResult = Global.getProtocol(JSON.toJSONString(page));
				jsonToPage(jsonResult, response);
			}
		} else if (!context.equals("null")) {
			List<ItemInfoEntity> result = itemInfoService.findAllByPage(jindu, context,
					(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize(), userId, itemUnmarked);
			page.setList(result);
			if (page.getList() == null) {
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			} else {
				String jsonResult = Global.getProtocol(JSON.toJSONString(page));
				jsonToPage(jsonResult, response);
			}
		} else {
			List<ItemInfoEntity> result = itemInfoService.findAllByPage(jindu, text,
					(page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize(), userId, itemUnmarked);
			page.setList(result);
			if (page.getList() == null) {
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			} else {
				String jsonResult = Global.getProtocol(JSON.toJSONString(page));
				jsonToPage(jsonResult, response);
			}

		}
	}

	/**
	 * 删除项目
	 * 
	 * @param response
	 * @param itemId
	 *            项目编号
	 */
	@RequestMapping("delItemInfo")
	@ResponseBody
	private void delItemInfo(HttpServletResponse response, String itemId, List<String> providerId, String userId) {
		if (Global.isNull(itemId) || Global.isNull(providerId)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		}

		Integer itemInfoResult = itemInfoService.del(itemId);

		if (itemInfoResult == 0) {

		} else {

			logger.info("删除我的项目成功编号为:====>" + itemId);

		}
		List<Integer> providerResult = new ArrayList<Integer>();
		for (int i = 0; i < providerId.size(); i++) {

			providerResult.add(infoProviderService.del(providerId.get(i)));
		}

		if (providerResult.size() == 0) {

		} else {
			logger.info("删除我的项目信息提供者成功编号为:====>" + providerId);

		}
		List<Integer> resuleP = new ArrayList<Integer>();
		for (int i = 0; i < providerId.size(); i++) {

			resuleP.add(itemProviderService.delItemProvider(itemId, providerId.get(i), userId));
		}

		if (resuleP.size() == 0) {
			// logger.info("删除我的项目关联关系成功编号为:====>"+providerId);
		} else {

		}

		if (itemInfoResult > 0 && providerResult.size() > 0 && resuleP.size() > 0) {
			String json = JSON.toJSONString(1);
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);
		} else {
			String message = "删除失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}
	}

	/**
	 * 根据项目名称模糊查询项目信息
	 * 
	 * @param itemName
	 *            项目名称
	 */
	@RequestMapping("findAllLikeItemInfo")
	@ResponseBody
	public void findAllLike(HttpServletResponse response, String itemName) {
		if (Global.isNull(itemName)) {
			String message = "前端传值为空!请核对";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
			return;
		} else {
			List<ItemInfoEntity> result = itemInfoService.findAllLike(itemName);
			if (result != null) {
				String json = JSON.toJSONString(result);
				String jsonResult = Global.getProtocol(json);
				jsonToPage(jsonResult, response);
			} else {
				String message = "查询项目失败";
				String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
				jsonToPage(protocol, response);
			}
		}
	}

	/**
	 * 统计我的项目面积
	 * 
	 * @param response
	 * @param userId
	 *            用户编号
	 * @param itemUnmarked
	 *            已保存的项目
	 * @param startDate
	 *            起始日期
	 * @param terminalDate
	 *            结束日期
	 */
	@RequestMapping("countItemArea")
	@ResponseBody
	public void countItemArea(HttpServletResponse response, String userId, String itemUnmarked, String startDate,
			String terminalDate) {
		Map<Object, Object> map = new HashMap<>();
		ItemInfoEntity i = itemInfoService.countArea(userId, itemUnmarked, startDate, terminalDate);
		double itemArea = Double.parseDouble(i.getItemaArea());
		double itemJoinArea = Double.parseDouble(i.getItemJoinArea());
		map.put("itemaArea", itemArea / 10000);
		map.put("itemJoinArea", itemJoinArea / 10000);
		if (map.size() != 0) {
			String json = JSON.toJSONString(map);
			String jsonResult = Global.getProtocol(json);
			jsonToPage(jsonResult, response);
		} else {
			String message = "获取失败";
			String protocol = Global.getProtocol(Global.ICE_UNKNOW, message, true);
			jsonToPage(protocol, response);
		}
	}
	
	
	/**
	 *  项目阶段切换
	 * 
	 * @param response
	 * @param accountUuid  用户id
	 * @param itemId  项目id
	 * @param itemProgressId  项目阶段id
	 */
	@RequestMapping("doNextStatus")
	@ResponseBody
	public void doNextStatus(HttpServletResponse response, String accountUuid, String itemId, String itemProgressId) {
		
		String resultString = itemInfoService.nextStatus(accountUuid, itemId, itemProgressId);
		
		logger.info(">>>>>>>>>>>>>>>doNextStatus:" + resultString);
		
		
		String jsonResult = Global.getProtocol(resultString);
		
		jsonToPage(jsonResult, response);
	}
	
	
	/**
	 *  获取项目阶段
	 * 
	 */
	@RequestMapping("doGetStages")
	@ResponseBody
	public void doGetStages(HttpServletResponse response) {
		
		String resultString = itemInfoService.getStages();
		
		logger.info(">>>>>>>>>>>>>>>doGetStages:" + resultString);
		
		String jsonResult = Global.getProtocol(resultString);
		
		jsonToPage(jsonResult, response);
	}
	
	
	/**
	 * 获取项目创段数据
	 */
	
	public void getItemFollowInfo(HttpServletResponse response, String itemId, String stage) {
		
	}
}
