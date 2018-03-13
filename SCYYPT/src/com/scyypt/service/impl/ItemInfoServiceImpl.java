/**
 * 
 */
package com.scyypt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.scyypt.dao.ItemInfoDao;
import com.scyypt.dao.ProjectProgressDao;
import com.scyypt.entity.ItemInfoEntity;
import com.scyypt.entity.ProjectProgress;
import com.scyypt.service.ItemInfoService;

/**
 * ItemInfoService接口实现类
 * @author ChengChuanPing
 * @Time   2017年12月14日下午7:30:27
 */
@Service("ItemInfoService")
public class ItemInfoServiceImpl implements ItemInfoService {
	@Autowired
	private ItemInfoDao itemInfoDao;
	
	@Autowired
	private ProjectProgressDao projectProgressDao;
	
	
	@Override
	public List<ItemInfoEntity> list() {
		return null;
	}


	@Override
	public ItemInfoEntity info(String id) {
		return null;
	}

	@Override
	public Integer add(ItemInfoEntity itemInfoEntity) {
		
		Integer id = itemInfoEntity.getItemId();
		
		int result = 0;
		
		if(id == null || id == 0) {
		
			result = itemInfoDao.add(itemInfoEntity);
		}
		
		return result;
		 
	}

	@Override
	public Integer edit(ItemInfoEntity itemInfoEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer del(@Param("itemId") String itemId) {
		return itemInfoDao.del(itemId);
	}

	@Override
	public List<ItemInfoEntity> findAllByPage(String itemProgress, String context, int start, int pageSize,
			String accountUuid, String itemUnmarked) {
		return itemInfoDao.findAllByPage(itemProgress, context, start, pageSize, accountUuid, itemUnmarked);
	}


	@Override
	public List<ItemInfoEntity> findAllLike(String itemName) {
		return  itemInfoDao.findAllLike(itemName);
	}


	@Override
	public ItemInfoEntity countArea(String accountUuid, String itemUnmarked, String startDate, String terminalDate) {
		return itemInfoDao.countArea(accountUuid, itemUnmarked, startDate, terminalDate);
	}


	@Override
	public int findCount(String accountUuid, String itemProgress, String context, String itemUnmarked) {
		return itemInfoDao.findCount(accountUuid, itemProgress, context, itemUnmarked);
	}


	@Override
	public Integer updateReminder(ItemInfoEntity itemInfoEntity) {
		return itemInfoDao.updateReminder(itemInfoEntity);
	}

	@Override
	public String nextStatus(String accountUuid, String itemId, String itemProgressId) {
		
		String progressId = this.itemInfoDao.getNextProgressId(itemProgressId);

		
		Map<String, String> map = new HashMap<String, String>();
		map.put("itemId", itemId);
		map.put("oldProgressId", itemProgressId);
		map.put("progressId", progressId);
		
		if(progressId == null || progressId.trim().equals("")) {
			
			map.put("isDone", "true");

		} else {
			map.put("isDone", "false");
			
			int u = this.itemInfoDao.updateProgress(accountUuid, itemId, progressId);
			if(u == 1) {
				
				 System.out.println("nextStatus->updateProgress  更新状态成功!");
			} else  {
				 System.out.println("nextStatus->updateProgress  更新状态失败!");
			}
		}
		
		String resultString = JSON.toJSONString(map);
		
		return resultString;
	}
	
	
	@Override
	public String getStages() {
		
		List<ProjectProgress> stages =  this.projectProgressDao.findAllProjectProgress();
		
		String resultString = JSON.toJSONString(stages);
		
		return resultString;
	}
}
