/**
 * 
 */
package com.scyypt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.scyypt.entity.ItemInfoEntity;

/**
 * @author ChengChuanPing
 * @Time 2017年12月14日下午7:27:31
 */
public interface ItemInfoService {

	/**
	 * 根据项目名称模糊查询项目信息
	 * 
	 * @param itemName
	 *            项目名称
	 * @return
	 */
	public List<ItemInfoEntity> findAllLike(@Param("itemName") String itemName);

	/**
	 * 分页加动态条件查询我的项目信息
	 * 
	 * @param itemProgress
	 *            项目进度
	 * @param context
	 *            搜索内容
	 * @param start
	 *            起始页
	 * @param pageSize
	 *            每页显示的大小
	 * @param accountUuid
	 *            用户唯一标识
	 * @param itemUnmarked
	 *            保存还是草稿
	 * @return
	 */
	public List<ItemInfoEntity> findAllByPage(@Param("itemProgress") String itemProgress,
			@Param("context") String context, @Param("start") int start, @Param("pageSize") int pageSize,
			@Param("accountUuid") String accountUuid, @Param("itemUnmarked") String itemUnmarked);

	/**
	 * 获取总记录数
	 * 
	 * @param accountUuid
	 *            用户Id
	 * @param itemProgress
	 *            项目进度条件
	 * @param context
	 *            搜索条件
	 * @param itemUnmarked
	 *            保存还是草稿
	 * @return
	 */
	public int findCount(@Param("accountUuid") String accountUuid, @Param("itemProgress") String itemProgress,
			@Param("context") String context, @Param("itemUnmarked") String itemUnmarked);

	/**
	 * 查询所有项目信息
	 * 
	 * @return
	 */
	public List<ItemInfoEntity> list();

	public ItemInfoEntity info(@Param("id") String id);

	/**
	 * 添加项目
	 * 
	 * @param itemInfoEntity
	 * @return
	 */
	public Integer add(ItemInfoEntity itemInfoEntity);

	public Integer edit(ItemInfoEntity itemInfoEntity);

	/**
	 * 删除我的项目
	 * 
	 * @param itemId
	 *            项目编号
	 * @param providerId
	 *            信息提供者编号
	 * @return
	 */
	public Integer del(@Param("itemId") String itemId);

	/**
	 * 统计我的项目面积
	 * 
	 * @param userId
	 *            用户编号
	 * @param itemUnmarked
	 *            标识草稿还是保存
	 * @param startDate
	 *            开始日期
	 * @param terminalDate
	 *            结束日期
	 * @return
	 */
	public ItemInfoEntity countArea(@Param("accountUuid") String accountUuid,
			@Param("itemUnmarked") String itemUnmarked, @Param("startDate") String startDate,
			@Param("terminalDate") String terminalDate);


    
	
	/**
     * 设置时间提醒
     * @param itemInfoEntity
     * @return
     */
    public Integer updateReminder(ItemInfoEntity itemInfoEntity);
    
    public String getStages();

    public String nextStatus(String accountUuid, String itemId, String itemProgressId);
}
